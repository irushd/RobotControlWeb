

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GPS
 */
public class GPS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String first;
    public static String second;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GPS() {
        super();
        // TODO Auto-generated constructor stub
    first=null;
    second=null;
    
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	first = (String) request.getParameter("first");
	second = (String) request.getParameter("second");
	
	Connection con = null;
	Statement statement=null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","5615676");
		statement = con.createStatement();
		int rs = statement.executeUpdate("Update test.Robot set Latitude='"+first+"' , Longitude='"+second +"';");
	
//	
//	File f = new File("gps.txt");
//	FileWriter fw = null;
//	if(!f.exists()){
//		f.createNewFile();
//	}
//	fw = new FileWriter(f);
//	fw.write(first+"\n");
////	fw.write(second);
	
	PrintWriter out = response.getWriter();
	out.write("<html>" +
			"<body>" +
			"<h1>" +
			"Got the data!: " + first + "   "+second + 
			"</h1>" +
			"</body>" +
			"</html>");}
	catch(Exception e){
		System.out.println(e);
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
