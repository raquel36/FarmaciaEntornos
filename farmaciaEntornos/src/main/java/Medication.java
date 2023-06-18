import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Medication implements Serializable {

	private int id;
	private String name;
	private float tmax;
	private float tmin;
	
	public Medication() {
		
	}
	
	public Medication(int id, String name, float tmax, float tmin) {
		this.id = id;
		this.name = name;
		this.tmax= tmax;
		this.tmin = tmin;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTmax() {
		return tmax;
	}

	public void setTmax(float tmax) {
		this.tmax = tmax;
	}

	public float getTmin() {
		return tmin;
	}

	public void setTmin(float tmin) {
		this.tmin = tmin;
	}

	public void load (int id) {
		String query = "SELECT * FROM medication WHERE id='"+id+"';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while(rs.next()) {
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.tmax = rs.getFloat("tmax");
			this.tmin = rs.getFloat("tmin");
		}
	} catch(SQLException e) {
		System.out.println("Error en Medication.load: " + e.getMessage());
		}
	}
}
