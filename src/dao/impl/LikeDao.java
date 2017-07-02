package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.ILikeDao;
import entity.Like;

public class LikeDao extends BaseDao<Like> implements ILikeDao {
	public LikeDao() {
		super();
		template = new Like();
		tablename = "like_article";
	}

	@Override
	public boolean isLike(int articleId, int userId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("article_id", articleId);
		condition.put("user_id", userId);
		List<Like> likes = selectByCondition(condition);
		if (likes.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int like(int articleId, int userId) {
		Like like = new Like();
		like.articleId = articleId;
		like.userId = userId;
		return insertEntity(like, true);
	}

	@Override
	public int dislike(int articleId, int userId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("article_id", articleId);
		condition.put("user_id", userId);
		List<Like> likes = selectByCondition(condition);
		for (Like like : likes) {
			deleteEntity("id", like.id);
		}
		return likes.size();
	}
}
