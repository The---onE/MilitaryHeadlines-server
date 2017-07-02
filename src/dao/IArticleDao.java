package dao;

import java.util.List;

import entity.Article;

public interface IArticleDao {
	// 通过类型查找文章列表
	List<Article> selectArticles(String type);
	
	// 通过文章ID获取文章
	Article getArticleById(int id);
	
	// 文章被点赞
	int likeArticle(int id);
	
	// 文章被取消赞
	int dislikeArticle(int id, int delta);
	
	// 添加文章
	int addArticle(String title, String author, String category);
}
