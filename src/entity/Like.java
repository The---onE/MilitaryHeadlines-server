package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by The_onE on 2017/5/16.
 */

public class Like extends BaseEntity {
	public int id;
	public Date timeCreated;
	public Date timeModified;
	public int userId; // 用户ID
	public int articleId; // 文章ID
	
	public Like() {
		timeCreated = new Date();
		timeModified = new Date();
	}

	@Override
	public Like convert(ResultSet rs) throws SQLException {
		Like article = new Like();
		article.id = rs.getInt("id");
		article.timeCreated = rs.getTimestamp("time_created");
		article.timeModified = rs.getTimestamp("time_modified");
		article.userId = rs.getInt("user_id");
		article.articleId = rs.getInt("article_id");

		return article;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, timeCreated, timeModified, userId, articleId };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("timeCreated", timeCreated);
		object.put("timeModified", timeModified);
		object.put("userId", userId);
		object.put("articleId", articleId);
		return object;
	}
}
