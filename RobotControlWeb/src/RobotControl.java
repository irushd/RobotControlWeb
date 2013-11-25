import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RobotControl
 */
public class RobotControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Socket serverSocket;
	DataOutputStream Output;
	String input = "f";
	String error = "Error Status: ";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RobotControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			serverSocket = new Socket("192.168.1.1", 8989);
			//serverSocket = new Socket("10.0.3.34", 8989);
			// serverSocket = new Socket("127.0.0.1", 8919);
			Output = new DataOutputStream(serverSocket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			error += e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			error += e.getMessage();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (serverSocket == null) {
			error = "";
			try {
			//	serverSocket = new Socket("192.168.1.11", 8989);
				serverSocket = new Socket("192.168.1.1", 8989);
				
				// serverSocket = new Socket("127.0.0.1", 8919);
				Output = new DataOutputStream(serverSocket.getOutputStream());
			} catch (UnknownHostException e) {
				e.printStackTrace();
				error += e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				error += e.getMessage();
			}
		}

		String direction = "Stopped";
		if (request.getParameter("Left") != null) {
			Output.writeUTF("l");
			direction = "Going Left";
		} else if (request.getParameter("Right") != null) {
			Output.writeUTF("r");
			direction = "Going Right";
		} else if (request.getParameter("Forward") != null) {
			Output.writeUTF("f");
			direction = "Going Left";
		} else if (request.getParameter("Back") != null) {
			Output.writeUTF("b");
			direction = "Going Left";
		}

		PrintWriter out = response.getWriter();
		out.write("<html>"
				+ "<head>"
				+ "	<title></title>"
				+ "</head>"
				+ "<body>"
				+ "<p style=\"text-align: center;\"> Robot: "
				+ direction
				+ "<br></p>"
				+ "<form action=\"RobotControl\" method=\"POST\">"
				+ "	<p style=\"text-align: center;\">"
				+ "		<input name=\"Forward\" type=\"submit\" value=\"Forward\" /></p>"
				+ "	<p style=\"text-align: center;\">"
				+ "		<input name=\"Left\" type=\"submit\" value=\"Left\" />"
				+ "<input name=\"Right\" type=\"submit\" value=\"Right\" /></p>"
				+ "	<p style=\"text-align: center;\">"
				+ "		<input name=\"b\" type=\"submit\" value=\"Back\" /></p>"
				+ "</form>" + "<br>" + error + "</body>" + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
