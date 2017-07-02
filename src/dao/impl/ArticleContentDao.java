package dao.impl;

import dao.BaseDao;
import dao.IArticleContentDao;
import entity.ArticleContent;

public class ArticleContentDao extends BaseDao<ArticleContent> implements IArticleContentDao {
	public ArticleContentDao() {
		super();
		template = new ArticleContent();
		tablename = "article_content";
	}

	@Override
	public ArticleContent getArticleContentById(int id) {
		return selectById("article_id", id);
	}

	@Override
	public int addArticleContent(int articleId, String content) {
		ArticleContent articleContent = new ArticleContent();
		articleContent.articleId = articleId;
		articleContent.content = content;
		return insertEntity(articleContent, true);
	}

}
