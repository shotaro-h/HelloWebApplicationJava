package player;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.CourseInfo;
import flayer.ViewCourseInfoFunction;

/**
 * Servlet implementation class ViewCourceInfoServlet
 */
public class ViewCourceInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String TABLENAME = "COURSE";
	private String tableFilePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewCourceInfoServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String tablePath = config.getInitParameter(TABLENAME);
		tableFilePath = this.getServletContext().getRealPath(tablePath);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username"); //ユーザ名
		List<CourseInfo> courseInfoList = new ArrayList<>(); //受講データリスト
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();

		if(userName==null||userName.isEmpty()) {
			ErrorPage.createErrorPage(pw, "ユーザ名を入力してください");
			return;
		}

		try {
			//デモ用データ追加
			//			Course data = new Course();
			//			data.setNo(3);
			//			data.setCourseName("course3");
			//			data.setCategory("IT");
			//			data.setScore(90);
			//			CourseDAO testCourseDAO = new CourseDAO(tableFilePath);
			//			testCourseDAO.insert(data);

			//受講データ取得
			courseInfoList = new ViewCourseInfoFunction(tableFilePath).getCourseInfoList();
		} catch (Exception e) {
			//システムエラー
			ErrorPage.createErrorPage(pw, e.getMessage());
			e.printStackTrace();
			return;
		}

		//ログイン情報保持
		HttpSession session = request.getSession();
		session.setAttribute("username", userName);

		//html作成
		//ヘッダ
		pw.println("<header>"
				+ "<title>受講データ照会</title>"
				+ "<link rel=\"stylesheet\" href=" + "css/common.css" + ">"
				+ "</header>");
		//ボディ
		pw.println("<body>");
		pw.println("<h1>" + userName + "さんの受講データ一覧</h1><br>");
		pw.println("<div style=\"margin-left:380\">"
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "時点</div><br>");
		//受講データ一覧テーブル
		pw.println("<table class=\"type06\" width=\"500px\">");
		pw.println("<th>#</th><th>講義名</th><th>分類</th><th>点数</th><th>成績評価</th>");
		for (int i = 0; i < courseInfoList.size(); i++) {
			CourseInfo courseInfo = courseInfoList.get(i);
			pw.println("<tr>");
			pw.println("<td style=\"text-align:center\">" + (i + 1) + "</td>");
			pw.println("<td style=\"text-align:center\">" + courseInfo.getCourseName() + "</td>");
			pw.println("<td style=\"text-align:center\">" + courseInfo.getCategory() + "</td>");
			pw.println("<td style=\"text-align:right\">" + courseInfo.getScore() + "</td>");
			//			pw.println("<td>" + courseInfo.getGrade() + "</td>");
			pw.println("<td>　</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("</body>");
		pw.close();
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
