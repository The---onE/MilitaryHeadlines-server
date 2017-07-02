package dao.impl;

import dao.BaseDao;
import dao.IPushDao;
import entity.Push;

public class PushDao extends BaseDao<Push> implements IPushDao {
	public PushDao() {
		super();
		template = new Push();
		tablename = "push";
	}

}
