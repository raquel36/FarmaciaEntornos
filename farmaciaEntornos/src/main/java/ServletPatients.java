
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPatients
 */
@WebServlet("/ServletPatients")
public class ServletPatients extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletPatients() {
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

	        if (isLogged) {
	            doctor.load(mail);
	            
	            String jsonString = null;
	            JSONArray ja = new JSONArray();
	            try {
	            String query = "SELECT * FROM patient;";
	            BBDD bd = new BBDD();
	            bd.conectar();
	            ResultSet rs = bd.loadSelect(query);

	            
	                while (rs.next()) {
	                    String patientMail = (rs.getString("mail"));
	                    ja.put(patientMail);
	                }
	                jsonString = ja.toString();
	            } catch (SQLException e) {
	               System.out.println("Error en el ServePatients.doget: " + e.getMessage());
	            }
	            response.getWriter().append(jsonString);

	           
	        } else {
	            System.out.println("El usuario no está loggeado");
	        }
	    }

}
