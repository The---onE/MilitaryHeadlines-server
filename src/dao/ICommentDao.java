package dao;

import java.util.List;

import entity.Comment;

public interface ICommentDao {
	// 通过文章ID查找评论
	List<Comment> selectCommentsByArticleId(int id);
	
	// 发表评论
	int addComment(int articleId, int userId, String content);
}
