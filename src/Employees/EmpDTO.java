package Employees;

public class EmpDTO {
	private String name;
	private String birth;
	private String phone;
	private String addr;
	private String email;
	
	public EmpDTO() {
	
	}

	public EmpDTO(String name, String birth, String phone, String addr, String email) {
		super();
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.addr = addr;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
