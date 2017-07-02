package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by The_onE on 2017/5/16.
 */

public class Comment extends BaseEntity {
	public int id;
	public Date timeCreated;
	public Date timeModified;
	public String content; // 评论内容
	public int articleId; // 文章ID
	public int userId; // 用户ID
	
	public Comment() {
		timeCreated = new Date();
		timeModified = new Date();
	}

	@Override
	public Comment convert(ResultSet rs) throws SQLException {
		Comment article = new Comment();
		article.id = rs.getInt("id");
		article.timeCreated = rs.getTimestamp("time_created");
		article.timeModified = rs.getTimestamp("time_modified");
		article.content = rs.getString("content");
		article.articleId = rs.getInt("article_id");
		article.userId = rs.getInt("user_id");

		return article;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, timeCreated, timeModified, content, articleId, userId };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("timeCreated", timeCreated);
		object.put("timeModified", timeModified);
		object.put("content", content);
		object.put("articleId", articleId);
		object.put("userId", userId);
		return object;
	}
}
