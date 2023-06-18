

import jakarta.servlet.ServletException;
/**
 * Servlet implementation class ServletMedication
 */
@WebServlet("/ServletMedication")
public class ServletMedication extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletMedication() {
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
	            String query = "SELECT * FROM medication;";
	            BBDD bd = new BBDD();
	            bd.conectar();
	            ResultSet rs = bd.loadSelect(query);

	            
	                while (rs.next()) {
	                	Medication m = new Medication();
	                	m.setName(rs.getString("name"));
	                	m.setId(rs.getInt("id"));
	                    JSONObject mjson = new JSONObject(m);
	                    ja.put(mjson);
	                }
	                jsonString = ja.toString();
	            } catch (SQLException e) {
	               System.out.println("Error en el ServletPatients.doget: " + e.getMessage());
	            }
	            response.getWriter().append(jsonString);

	           
	        } else {
	            System.out.println("El usuario no está loggeado");
	        }

	}

	

}
