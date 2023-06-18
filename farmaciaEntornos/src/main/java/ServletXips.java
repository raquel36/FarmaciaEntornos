

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletXips
 */
@WebServlet("/ServletXips")
public class ServletXips extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletXips() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(mail, session);
		
		if(isLogged) {
			doctor.load(mail);
			doctor.loadReleaseList();
			String html = doctor.getTable();
			response.getWriter().append(String.valueOf(html));
		} else {
			System.out.println("El usuario no está loggeado");
		}
	}

	

}
