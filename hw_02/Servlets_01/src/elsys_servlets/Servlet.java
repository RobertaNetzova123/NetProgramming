package elsys_servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String value;

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  ServletOutputStream out = response.getOutputStream();
	        
//	       out.println("<html>");
//	       out.println("<head><title>Hello Servlet</title></head>");
//	        
//	       out.println("<body>");
//	       out.println("<h3>Hello World</h3>");
//	       out.println("This is my first Servlet");
//	       out.println("</body>");
//	       out.println("</html>");
		  out.println();
		  	out.println(value);
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		value = request.getParameter("name");
		ServletOutputStream out = response.getOutputStream();
		out.println();
		out.println("Succes!!!");
		
	}

}
