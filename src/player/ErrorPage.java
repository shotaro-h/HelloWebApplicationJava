package player;

import java.io.PrintWriter;

public class ErrorPage {
	public static void createErrorPage(PrintWriter pw, String message) {
		//ヘッダ
		pw.println("<header><title>エラー画面</title></header>");
		//ボディ
		pw.println("<body>");
		pw.println("<h1 style=\"color:red\">エラー！</h1><br>");
		pw.println(message);
		pw.println("<br><input type=\"button\" value=\"戻る\" onclick=\"history.back()\" style=\"margin-left: 200px\"");
		pw.println("<body>");
		pw.close();
	}
}
