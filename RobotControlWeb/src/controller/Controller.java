package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.AccessDb;
import Database.User;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		AccessDb.InitializeDatabase();
		//Initialize the Session Hashtable
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessDb.InitializeDatabase();
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		
		
		if(AccessDb.loginCheck(user, pass)){
			HttpSession session = request.getSession(true);
			if(session.isNew()){
			session.setMaxInactiveInterval(600);
			User userObj  = AccessDb.getUser(user, pass);
			session.setAttribute("userObj", userObj);
			}
			ServletContext context = getServletContext();
			RequestDispatcher dispatch = context.getRequestDispatcher("/Control.jsp"); 
			dispatch.forward(request, response);
		}
		else{
			ServletContext context = getServletContext();
			RequestDispatcher dispatch = context.getRequestDispatcher("/signup.html"); 
			dispatch.forward(request, response);		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
