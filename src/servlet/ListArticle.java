package servlet;

import java.io.IOException;
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
import dao.impl.ArticleDao;
import entity.Article;

/**
 * Servlet implementation class GetTitleList
 */
@WebServlet("/ListArticle")
public class ListArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IArticleDao articleDao = new ArticleDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListArticle() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			String type = request.getParameter("type");
			List<Article> result = articleDao.selectArticles(type);

			JSONObject res = Constants.createJSONResult(result);

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
