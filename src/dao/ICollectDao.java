package dao;

import java.util.List;

import entity.Collect;

public interface ICollectDao {
	// 查找用户收藏文章列表
	List<Collect> selectArticles(int userId);
	
	// 检验用户是否对文章点赞
	boolean isCollect(int articleId, int userId);
	
	// 添加收藏
	int collect(int articleId, int userId);
	
	// 取消收藏
	int discollect(int articleId, int userId);
}
