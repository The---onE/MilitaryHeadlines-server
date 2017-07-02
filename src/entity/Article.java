package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by The_onE on 2017/5/16.
 */

public class Article extends BaseEntity {
	public int id;
	public Date timeCreated;
	public Date timeModified;
	public String author; // 作者
	public String category; // 类别
	public int shareCount; // 分享数
	public int likeCount; // 点赞数
	public String title; // 标题
	public String image1; // 图片
	public String image2; // 图片
	public String image3; // 图片
	
	public Article() {
		timeCreated = new Date();
		timeModified = new Date();
		shareCount = 0;
		likeCount = 0;
	}

	@Override
	public Article convert(ResultSet rs) throws SQLException {
		Article article = new Article();
		article.id = rs.getInt("id");
		article.timeCreated = rs.getTimestamp("time_created");
		article.timeModified = rs.getTimestamp("time_modified");
		article.author = rs.getString("author");
		article.category = rs.getString("category");
		article.shareCount = rs.getInt("share_count");
		article.likeCount = rs.getInt("like_count");
		article.title = rs.getString("title");
		article.image1 = rs.getString("image1");
		article.image2 = rs.getString("image2");
		article.image3 = rs.getString("image3");

		return article;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, timeCreated, timeModified, author, category, shareCount, likeCount, title, image1,
				image2, image3 };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("timeCreated", timeCreated.toString());
		object.put("timeModified", timeModified.toString());
		object.put("author", author);
		object.put("category", category);
		object.put("shareCount", shareCount);
		object.put("likeCount", likeCount);
		object.put("title", title);
		object.put("image1", image1);
		object.put("image2", image2);
		object.put("image3", image3);
		return object;
	}
}
