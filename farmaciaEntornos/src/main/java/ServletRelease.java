

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRelease
 */
@WebServlet("/ServletRelease")
public class ServletRelease extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletRelease() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String mail = request.getParameter("mail");
	        String session = request.getParameter("session");
	        System.out.println("Rebent: "+mail+session);
	        int chipId = Integer.parseInt(request.getParameter("chipId"));
	        String mailP = request.getParameter("mailP");
	        int id_medicine = Integer.parseInt(request.getParameter("id_medicine"));
	        String date = request.getParameter("date");

	        Doctor doctor = new Doctor();
	        boolean isLogged = doctor.isLogged(mail, session);

	        if (isLogged) {
	            doctor.load(mail);
	            String query = String.format("INSERT INTO xip (id, doctor_mail, id_medicine, id_patient,date) VALUES ('%s', '%s', '%s', '%s', '%s');", chipId, mail, id_medicine, mailP, date);
	            BBDD bd = new BBDD();
	            bd.conectar();
	            bd.updateDoctor(query);
	        } else {
	            System.out.println("El usuario no está loggeado");
	        }
	}

}
