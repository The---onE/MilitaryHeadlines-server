package dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.IArticleDao;
import entity.Article;

public class ArticleDao extends BaseDao<Article> implements IArticleDao {
	public ArticleDao() {
		super();
		template = new Article();
		tablename = "article";
	}

	@Override
	public List<Article> selectArticles(String type) {
		List<Article> result;
		if (type != null && !type.equals("")) {
			Map<String, Object> con = new HashMap<String, Object>();
			con.put("category", type);
			result = selectByCondition(con);
		} else {
			result = selectByCondition(null);
		}
		return result;
	}

	@Override
	public Article getArticleById(int id) {
		return selectById("id", id);
	}

	@Override
	public int likeArticle(int id) {
		Article article = selectById("id", id);
		if (article != null) {
			int count = article.likeCount;
			String sql = "UPDATE " + tablename + " SET like_count = ? WHERE id = ?";
			Object[] params = { count + 1, id };
			int result = 0;
			try {
				result = util.executeUpdate(sql, params, false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		return 0;
	}

	@Override
	public int dislikeArticle(int id, int delta) {
		Article article = selectById("id", id);
		if (article != null) {
			int count = article.likeCount;
			String sql = "UPDATE " + tablename + " SET like_count = ? WHERE id = ?";
			Object[] params = { count - delta, id };
			int result = 0;
			try {
				result = util.executeUpdate(sql, params, false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		return 0;
	}

	@Override
	public int addArticle(String title, String author, String category) {
		Article article = new Article();
		article.title = title;
		article.author = author;
		article.category = category;
		return insertEntity(article, true);
	}
}
