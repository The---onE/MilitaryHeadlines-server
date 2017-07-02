package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by The_onE on 2017/5/16.
 */

public class Recommend extends BaseEntity {
	public int id;
	public Date timeCreated;
	public Date timeModified;
	public int fromArticleId; // 源文章ID
	public int toArticleId; // 关联文章ID
	
	public Recommend() {
		timeCreated = new Date();
		timeModified = new Date();
	}

	@Override
	public Recommend convert(ResultSet rs) throws SQLException {
		Recommend article = new Recommend();
		article.id = rs.getInt("id");
		article.timeCreated = rs.getTimestamp("time_created");
		article.timeModified = rs.getTimestamp("time_modified");
		article.fromArticleId = rs.getInt("from_article_id");
		article.toArticleId = rs.getInt("to_article_id");

		return article;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, timeCreated, timeModified, fromArticleId, toArticleId };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("timeCreated", timeCreated);
		object.put("timeModified", timeModified);
		object.put("fromArticleId", fromArticleId);
		object.put("toArticleId", toArticleId);
		return object;
	}
}
