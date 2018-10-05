package elsys_servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_Map
 */
@WebServlet("/Servlet_Map")
public class Servlet_Map extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Enumeration enumeration;
	 HashMap<String, String> hmap = new HashMap<String, String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Map() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletOutputStream out = response.getOutputStream();
		Iterator iterator = hmap.keySet().iterator();
//	       out.println("<html>");
//	       out.println("<head><title>Hello Servlet</title></head>");
//	        
//	       out.println("<body>");
//	       out.println("<h3>Hello World</h3>");
//	       out.println("This is my first Servlet");
//	       out.println("<form  action = >");
//	       out.println("New key:<br>");
//	       out.println("<input type=\"text\">");
//	       out.println("<br>");
//	       out.println("New value:<br>");
//	       out.println("<input name= type=\"text\">");
//	       out.println("<br><br>");
//	       out.println("<input type=\"submit\" value=\"Submit\">");
//	       out.println("</form>");
	      
	     
//		  out.println("<br>");
		  while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   String value = hmap.get(key).toString();
//		   out.println("<br>");
		   out.println(key + " " + value);
//		   out.print("<br>");
		}

//		 out.println("</body>");
//	       out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 enumeration = request.getParameterNames();
		 while (enumeration.hasMoreElements()) {
			String currentENUM = enumeration.nextElement().toString();
			String value = request.getParameter(currentENUM);
			hmap.put(currentENUM,value);
	      }
	    
	}

}
