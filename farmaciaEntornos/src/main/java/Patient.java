import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Person {
	
	public Patient() {
		super();
	}
	
	public Patient (String name, String mail) {
		super(name, mail);
	}
	
	

	@Override
	public void load(String id) {
		String query = "SELECT * FROM patient WHERE mail='" + id + "';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while (rs.next()) {
				this.setName(rs.getString("name"));
				this.setMail(rs.getString("mail"));
			}
		} catch (SQLException e) {
			System.out.println("Error en Patient.load: " + e.getMessage());
		}
		
	}

}
