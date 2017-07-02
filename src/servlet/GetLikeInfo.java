package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.ILikeDao;
import dao.impl.LikeDao;

/**
 * Servlet implementation class GetLikeInfo
 */
@WebServlet("/GetLikeInfo")
public class GetLikeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ILikeDao likeDao = new LikeDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLikeInfo() {
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
			
			String articleId = request.getParameter("article_id");
			String userId = request.getParameter("user_id");
			JSONObject res = new JSONObject();
			if (!Constants.isEmpty(articleId) && !Constants.isEmpty(userId)) {
				int aId = Integer.parseInt(articleId);
				int uId = Integer.parseInt(userId);
				boolean flag = likeDao.isLike(aId, uId);
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
				res.put(Constants.RESPONSE_PROMPT, "获取成功");
				res.put("isLike", flag);
			} else {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
				res.put(Constants.RESPONSE_PROMPT, "缺少参数");
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
