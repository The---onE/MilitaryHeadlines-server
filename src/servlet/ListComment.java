package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.ICommentDao;
import dao.impl.CommentDao;
import entity.Comment;

/**
 * Servlet implementation class ListComment
 */
@WebServlet("/ListComment")
public class ListComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ICommentDao commentDao = new CommentDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html"); // 设置文本形式和字符编码
			response.setCharacterEncoding("UTF-8");

			JSONObject res = new JSONObject();
			PrintWriter writer = response.getWriter();
			
			String id = request.getParameter("id");
			if (id != null && !id.equals("")) {
				int i = Integer.parseInt(id);
				List<Comment> result = commentDao.selectCommentsByArticleId(i);

				res = Constants.createJSONResult(result);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
