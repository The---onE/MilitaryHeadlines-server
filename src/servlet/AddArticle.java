package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.IArticleContentDao;
import dao.IArticleDao;
import dao.impl.ArticleContentDao;
import dao.impl.ArticleDao;

/**
 * Servlet implementation class AddArticle
 */
@WebServlet("/AddArticle")
public class AddArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IArticleDao articleDao = new ArticleDao();
	IArticleContentDao articleContentDao = new ArticleContentDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddArticle() {
		super();
		// TODO Auto-generated constructor stub
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
			HttpSession session = request.getSession();
			Object userId = session.getAttribute("userId");

			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String category = request.getParameter("category");
			String content = request.getParameter("content");

			JSONObject res = new JSONObject();
			if (userId != null) {
				if (!Constants.isEmpty(title) && !Constants.isEmpty(author) && !Constants.isEmpty(category)
						&& !Constants.isEmpty(content)) {
					int aId = articleDao.addArticle(title, author, category);
					if (aId > 0) {
						int id = articleContentDao.addArticleContent(aId, content);
						if (id > 0) {
							res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
							res.put(Constants.RESPONSE_PROMPT, "添加成功");
						} else {
							res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
							res.put(Constants.RESPONSE_PROMPT, "添加失败");
						}
						res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
						res.put(Constants.RESPONSE_PROMPT, "添加成功");
					} else {
						res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
						res.put(Constants.RESPONSE_PROMPT, "添加失败");
					}
				} else {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
					res.put(Constants.RESPONSE_PROMPT, "缺少参数");
				}
			} else {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
				res.put(Constants.RESPONSE_PROMPT, "请先登录");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
