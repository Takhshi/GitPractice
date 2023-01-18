package dto;

public class AllUser {
	private String name;
	private int age;
	private int gender;
	private String gen;
	private String phone;
	private String mail;
	
	public AllUser(String name, int age, int gender, String gen, String phone, String mail) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.gen = gen;
		this.phone = phone;
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gender==0?"男":"女";
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}