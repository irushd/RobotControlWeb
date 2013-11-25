package webservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RobotMovement.RobotMovement;

/**
 * Servlet implementation class RobotController
 */
public class RobotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static volatile String movement;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RobotController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.println("Hello Rushdaaaayyyyyyyyy!");
		out.println(RobotMovement.getMovement());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
