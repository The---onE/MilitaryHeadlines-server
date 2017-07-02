package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.ICollectDao;
import entity.Collect;

public class CollectDao extends BaseDao<Collect> implements ICollectDao {
	public CollectDao() {
		super();
		template = new Collect();
		tablename = "collect";
	}

	@Override
	public List<Collect> selectArticles(int userId) {
		List<Collect> result;
		Map<String, Object> con = new HashMap<String, Object>();
		con.put("user_id", userId);
		result = selectByCondition(con);
		return result;
	}

	@Override
	public boolean isCollect(int articleId, int userId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("article_id", articleId);
		condition.put("user_id", userId);
		List<Collect> collects = selectByCondition(condition);
		if (collects.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int collect(int articleId, int userId) {
		Collect collect = new Collect();
		collect.articleId = articleId;
		collect.userId = userId;
		return insertEntity(collect, true);
	}

	@Override
	public int discollect(int articleId, int userId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("article_id", articleId);
		condition.put("user_id", userId);
		List<Collect> collects = selectByCondition(condition);
		for (Collect collect : collects) {
			deleteEntity("id", collect.id);
		}
		return collects.size();
	}

}
