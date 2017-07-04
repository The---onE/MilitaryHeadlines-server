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
import dao.IAdminDao;
import dao.impl.AdminDao;
import entity.Admin;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IAdminDao adminDao = new AdminDao();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
			String password = request.getParameter("password");
			JSONObject res = new JSONObject();
			if (!Constants.isEmpty(username) && !Constants.isEmpty(password)) {
				Admin user = adminDao.login(username, password);
				if (user != null) {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
					res.put(Constants.RESPONSE_PROMPT, "登录成功");
					res.put("user_id", user.id);
					res.put("nickname", user.nickname);
					res.put("checksum", user.checksum);
					// 保存Session
					HttpSession session=request.getSession();
					session.setAttribute("userId", user.id);
				} else {
					res.put(Constants.RESPONSE_STATUS, Constants.STATUS_ERROR);
					res.put(Constants.RESPONSE_PROMPT, "用户名或密码不匹配");
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
