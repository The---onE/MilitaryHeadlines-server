package dao;

public interface ILikeDao {
	// 检验用户是否对文章点赞
	boolean isLike(int articleId, int userId);
	
	// 用户对文章点赞
	int like(int articleId, int userId);
	
	// 用户对文章取消点赞
	int dislike(int articleId, int userId);
}
