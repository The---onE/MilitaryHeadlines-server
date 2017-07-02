package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.ICommentDao;
import entity.Comment;

public class CommentDao extends BaseDao<Comment> implements ICommentDao {
	public CommentDao() {
		super();
		template = new Comment();
		tablename = "comment";
	}

	@Override
	public List<Comment> selectCommentsByArticleId(int id) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("article_id", id);
		return selectByCondition(condition);
	}

	@Override
	public int addComment(int articleId, int userId, String content) {
		Comment comment = new Comment();
		comment.articleId = articleId;
		comment.userId = userId;
		comment.content = content;
		return insertEntity(comment, true);
	}
}
