package dao.impl;

import dao.BaseDao;
import dao.IRecommendDao;
import entity.Recommend;

public class RecommentDao extends BaseDao<Recommend> implements IRecommendDao {
	public RecommentDao() {
		super();
		template = new Recommend();
		tablename = "recommend";
	}
}
