package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by The_onE on 2017/5/16.
 */

public class Push extends BaseEntity {
	public int id;
	public Date timeCreated;
	public Date timeModified;
	public String title; // 推送标题
	public String content; // 推送内容
	
	public Push() {
		timeCreated = new Date();
		timeModified = new Date();
	}

	@Override
	public Push convert(ResultSet rs) throws SQLException {
		Push article = new Push();
		article.id = rs.getInt("id");
		article.timeCreated = rs.getTimestamp("time_created");
		article.timeModified = rs.getTimestamp("time_modified");
		article.title = rs.getString("title");
		article.content = rs.getString("content");

		return article;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, timeCreated, timeModified, title, content };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("timeCreated", timeCreated);
		object.put("timeModified", timeModified);
		object.put("title", title);
		object.put("content", content);
		return object;
	}
}
