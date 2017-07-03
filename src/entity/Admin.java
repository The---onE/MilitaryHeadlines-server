package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by The_onE on 2017/5/16.
 */

public class Admin extends BaseEntity {
	public int id;
	public Date timeCreated;
	public Date timeModified;
	public String username; // 用户名
	public String password; // 密码
	public String checksum; // 校验码
	public String nickname; // 昵称
	
	public Admin() {
		timeCreated = new Date();
		timeModified = new Date();
	}

	@Override
	public Admin convert(ResultSet rs) throws SQLException {
		Admin article = new Admin();
		article.id = rs.getInt("id");
		article.timeCreated = rs.getDate("time_created");
		article.timeModified = rs.getDate("time_modified");
		article.username = rs.getString("username");
		article.password = rs.getString("password");
		article.checksum = rs.getString("checksum");
		article.nickname = rs.getString("nickname");

		return article;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, timeCreated, timeModified, username, password, checksum, nickname };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("timeCreated", timeCreated);
		object.put("timeModified", timeModified);
		object.put("username", username);
		object.put("password", password);
		object.put("checksum", checksum);
		object.put("nickname", nickname);
		return object;
	}
}
