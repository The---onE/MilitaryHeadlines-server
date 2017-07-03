package dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.BaseDao;
import dao.IAdminDao;
import entity.Admin;

public class AdminDao extends BaseDao<Admin> implements IAdminDao {
	public AdminDao() {
		super();
		template = new Admin();
		tablename = "admin";
	}

	@Override
	public Admin login(String username, String password) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		try {
			String sePassword = makePassword(password);
			condition.put("password", sePassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		List<Admin> users = selectByCondition(condition);
		if (users.size() > 0) {
			Admin user =  users.get(0);
			String checksum;
			try {
				checksum = makeChecksum();
				if (updateChecksum(checksum, user.id)) {
					user.checksum = checksum;
					return user;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Admin register(String username, String password, String nickname) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		List<Admin> users = selectByCondition(condition);
		if (users.size() > 0) {
			return null;
		} else {
			Admin user = new Admin();
			user.username = username;
			try {
				user.password = makePassword(password);
				user.nickname = nickname;
				user.checksum = makeChecksum();
				int id = insertEntity(user, true);
				user.id = id;
				
				return user;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private String makePassword(String source) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(source.getBytes());
		byte[] sePassword = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<sePassword.length; ++i) {
			String s = Integer.toHexString(sePassword[i] & 0xFF);
			if (s.length() < 2) {
				sb.append(0);
			}
			sb.append(s);
		}
		return sb.toString();
	}
	
	private String makeChecksum() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		Random random = new Random();
		md.update((random.nextDouble() + "").getBytes());
		byte[] sePassword = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<sePassword.length; ++i) {
			String s = Integer.toHexString(sePassword[i] & 0xFF);
			if (s.length() < 2) {
				sb.append(0);
			}
			sb.append(s);
		}
		return sb.toString();
	}
	
	private boolean updateChecksum(String checksum, int id) {
		String sql = "UPDATE " + tablename + " SET checksum = ? WHERE id = ?";
		Object[] params = { checksum, id };
		int result = 0;
		try {
			result = util.executeUpdate(sql, params, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}
}
