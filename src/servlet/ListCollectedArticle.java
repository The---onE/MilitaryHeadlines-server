package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.IArticleDao;
import dao.ICollectDao;
import dao.impl.ArticleDao;
import dao.impl.CollectDao;
import entity.Article;
import entity.Collect;

/**
 * Servlet implementation class ListCollectedArticle
 */
@WebServlet("/ListCollectedArticle")
public class ListCollectedArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IArticleDao articleDao = new ArticleDao();
	ICollectDao collectDao = new CollectDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCollectedArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			String userId = request.getParameter("user_id");
			JSONObject res = new JSONObject();
			if (!Constants.isEmpty(userId)) {
				int uId = Integer.parseInt(userId);
				List<Collect> collects = collectDao.selectArticles(uId);
				if (collects.size() > 0) {
					List<Article> articles = new ArrayList<Article>();
					for (Collect collect : collects) {
						Article article = articleDao.getArticleById(collect.articleId);
						articles.add(article);
						res = Constants.createJSONResult(articles);
					}
				} else {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
					res.put(Constants.RESPONSE_PROMPT, "暂无数据");
				}
			} else {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
				res.put(Constants.RESPONSE_PROMPT, "用户ID不能为空");
			}

			response.getWriter().append(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JSONObject res = new JSONObject();
				res.put(Constants.RESPONSE_STATUS, 0);
				res.put(Constants.RESPONSE_PROMPT, "查询失败");
				response.getWriter().append(res.toString());
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
