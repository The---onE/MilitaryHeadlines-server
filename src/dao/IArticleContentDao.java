package dao;

import entity.ArticleContent;

public interface IArticleContentDao {
	// 通过文章ID查找文章内容
	ArticleContent getArticleContentById(int id);
	
	// 添加文章内容
	int addArticleContent(int articleId, String content);
}
