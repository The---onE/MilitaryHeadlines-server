package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.IArticleContentDao;
import dao.impl.ArticleContentDao;
import entity.ArticleContent;

/**
 * Servlet implementation class GetArticleDetail
 */
@WebServlet("/GetArticleContent")
public class GetArticleContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IArticleContentDao articleContentDao = new ArticleContentDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetArticleContent() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html"); // 设置文本形式和字符编码
			response.setCharacterEncoding("UTF-8");

			JSONObject res = new JSONObject();
			PrintWriter writer = response.getWriter();

			String id = request.getParameter("id");

			if (id != null && !id.equals("")) {
				int i = Integer.parseInt(id);
				ArticleContent content = articleContentDao.getArticleContentById(i);

				if (content != null) {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_QUERY_SUCCESS);
					res.put(Constants.RESPONSE_PROMPT, "获取成功");
					JSONArray entityList = new JSONArray();
					JSONObject object = content.convertToJSON();
					entityList.put(object);
					res.put(Constants.RESPONSE_ENTITIES, entityList);
				} else {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
					res.put(Constants.RESPONSE_PROMPT, "查询失败");
				}
			} else {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
				res.put(Constants.RESPONSE_PROMPT, "查询ID不能为空");
			}

			writer.append(res.toString());
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
