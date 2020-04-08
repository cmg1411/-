package Employees;

public class EmpBiz {
	public int insertDTO(EmpDTO dto) {
		EmpDAO dao = new EmpDAO();
		int n = dao.insertDTO(dto);
		return n;
	}
	
	public int deleteDTO(String name, String phone) {
		EmpDAO dao = new EmpDAO();
		int n = dao.deleteDTO(name, phone);
		return n;
	}
	
	public int updateDTO(EmpDTO dto, int cnt) {
		EmpDAO dao = new EmpDAO();
		int n = dao.updateDTO(dto, cnt);
		return n;
	}
	
	public EmpDTO getDTO(int num) {
		EmpDAO dao = new EmpDAO();
		EmpDTO dto = dao.getDTO(num);
		return dto;
	}
}
