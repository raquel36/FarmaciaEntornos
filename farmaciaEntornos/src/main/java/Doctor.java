import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Person {
	
	private String pass;
	private LocalDate lastLog;
	private int session;
	private ArrayList<Xip> releaseList;
	
//	Constructors
	
	public Doctor() {
		this.releaseList = new ArrayList<>();
		
	}
	
	public Doctor(String name, String mail, String pass, LocalDate lastLog, int session) {
		super(name, mail);
		this.setPass(pass);
		this.setLastLog(lastLog);
		this.setSession(session);
		this.releaseList = new ArrayList<>();
	}
	
//	Métodos
	public void load (String id) {
		String query = "SELECT * FROM doctor where mail ='"+id+"';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while(rs.next()) {
			this.setName(rs.getString("name"));
			this.setMail(rs.getString("mail"));
			this.setPass(rs.getString("pass"));
			}
		} catch (SQLException e) {
			System.out.println("Error a Doctor.load: " + e.getMessage());
		}
		
		
	}
	
	public void login (String mail, String pass) {
		String query = "SELECT * FROM doctor where mail ='"+mail+"' AND pass='"+pass+"';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		try {
			if (rs.next()) {
				this.setLastLog(LocalDate.now());
				Random random = new Random();
				String code = "";
				for (int i= 0; i<9;i++) {
					code+=random.nextInt(10);
				}
				int session = Integer.parseInt(code);
				
				this.setSession(session);
				
				query = "UPDATE doctor SET last_Log= '"+this.getLastLog()+"',session='"+this.getSession()+"' WHERE mail='"+mail+"';";
				bd.updateDoctor(query);
				
				this.load(mail);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isLogged(String mail, String session) {
        String query = "SELECT * FROM doctor WHERE mail ='" + mail + "' AND session = " + session + ";";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);

		try {
			if (rs.next()) {
				this.login(mail, this.pass);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
    }

	public void loadReleaseList() {
		String query = "SELECT * FROM xip WHERE doctor_mail = '" + this.getMail() + "';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);

		try {
			while (rs.next()) {
				Xip xip = new Xip();
				int xipId = rs.getInt("id");
				xip.load(xipId);
				this.releaseList.add(xip);
			}
			System.out.println("Loaded " + this.releaseList.size() + " xips.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public String getTable() {
		this.loadReleaseList();
		
		StringBuilder html = new StringBuilder();

		html.append("<table>");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th>ID</th>");
		html.append("<th>Medicine</th>");
		html.append("<th>Patient</th>");
		html.append("<th>Date</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");
		

		for (Xip xip: releaseList) {
		    html.append("<tr>");
		    html.append("<td>").append(xip.getId()).append("</td>");
		    html.append("<td>").append(xip.getMedication().getName()).append("</td>");
		    html.append("<td>").append(xip.getPatient().getName()).append("</td>");
		    html.append("<td>").append(xip.getDate()).append("</td>");
		    html.append("</tr>");
		}

		html.append("</tbody>");
		html.append("</table>");

		
		return html.toString();
	}
//	Getters y setters
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastLog() {
		return lastLog;
	}

	public void setLastLog(LocalDate lastLog) {
		this.lastLog = lastLog;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}


	
}
