import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Xip {

	private int id;
	private Medication medication;
	private Patient patient;
	private Date date;
	
	public Xip() {
		
	}
	
	public Xip(int id, Medication medication, Patient patient, Date date) {
		this.id = id;
		this.medication = medication;
		this.patient = patient;
		this.date=date;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void load(int id) {
		String query = "SELECT * FROM xip WHERE id = '" +id+"';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while(rs.next()) {
				this.id = rs.getInt("id");
				this.patient = new Patient();
				this.patient.load(rs.getString("id_patient"));
				tmehis.medication = new Medication();
				this.medication.load(rs.getInt("id_medication"));
				this.date = rs.getDate("date");
				
			}
		} catch (SQLException e) {
			System.out.println("Error a Xip.load: " + e.getMessage());
		}
	}
}
