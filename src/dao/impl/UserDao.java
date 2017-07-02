package dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.BaseDao;
import dao.IUserDao;
import entity.User;

public class UserDao extends BaseDao<User> implements IUserDao {
	public UserDao() {
		super();
		template = new User();
		tablename = "user";
	}

	@Override
	public User login(String username, String password) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		try {
			String sePassword = makePassword(password);
			condition.put("password", sePassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		List<User> users = selectByCondition(condition);
		if (users.size() > 0) {
			User user =  users.get(0);
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
	public User register(String username, String password, String nickname) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		List<User> users = selectByCondition(condition);
		if (users.size() > 0) {
			return null;
		} else {
			User user = new User();
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

	@Override
	public User autoLogin(String username, String checksum) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		condition.put("checksum", checksum);
		List<User> users = selectByCondition(condition);
		if (users.size() > 0) {
			User user =  users.get(0);
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
	public User checkLogin(String username, String checksum) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		condition.put("checksum", checksum);
		List<User> users = selectByCondition(condition);
		if (users.size() > 0) {
			User user =  users.get(0);
			return user;
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
