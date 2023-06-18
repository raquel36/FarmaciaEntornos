
public abstract class Person {
	
	protected String name;
	protected String mail;
	
	public Person() {

}
	public Person(String name, String mail) {
		this.setName(name);
		this.setMail(mail);
	}
//		Métodos
	public abstract void load(String id);
//		Getters y setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
	
