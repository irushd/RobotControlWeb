import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Robot
 */
public class Robot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Robot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		File f = new File("gps.txt");
//		FileReader fr = null;
//		if(!f.exists()){
//			f.createNewFile();
//		}
//		fr = new FileReader(f);
		ResultSet rs = null;
		Connection con = null;
		Statement statement=null;
		Writer out = response.getWriter();

		try {
			
			System.out.println("doGET STARTS STARTS STARTS");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","5615676");
			statement = con.createStatement();
			rs = statement.executeQuery("SELECT * FROM robot;");			
		//	out.write("<html>sadfsad</html>"); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		String a;
		String b;
		try {
			rs.next();
			a = rs.getString("Longitude");
			b = rs.getString("Latitude");
			System.out.println(a+b);
			out.write("<html>" +
					"<body>" +
					"<h1>" +
					"Robot Location" +
					"</h1>" +
					"Longitude: " +a  +
					"<br/>" +
					"Latitude: " + b+
					"</body>" +
					"</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
