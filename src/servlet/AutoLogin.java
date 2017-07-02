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
import dao.IUserDao;
import dao.impl.UserDao;
import entity.User;

/**
 * Servlet implementation class AutoLogin
 */
@WebServlet("/AutoLogin")
public class AutoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserDao userDao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoLogin() {
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
			
			String username = request.getParameter("username");
			String checksum = request.getParameter("checksum");
			JSONObject res = new JSONObject();
			if (!Constants.isEmpty(username) && !Constants.isEmpty(checksum)) {
				User user = userDao.autoLogin(username, checksum);
				if (user != null) {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
					res.put(Constants.RESPONSE_PROMPT, "登录成功");
					res.put("user_id", user.id);
					res.put("nickname", user.nickname);
					res.put("checksum", user.checksum);
				} else {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
					res.put(Constants.RESPONSE_PROMPT, "登录失效，请重新登录");
				}
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
