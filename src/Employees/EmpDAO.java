package Employees;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmpDAO {
	public int insertDTO(EmpDTO dto) {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int n = 0;
		
		try {
			String sql = "INSERT INTO EMPDB VALUES(EMP_IDX.NEXTVAL,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dto.getName());
			pstmt.setDate(2, java.sql.Date.valueOf(dto.getBirth()));
			pstmt.setString(3,dto.getPhone());
			pstmt.setString(4,dto.getAddr());
			pstmt.setString(5,dto.getEmail());
			n = pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			}
		}catch(SQLException e){
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return n;
	}
	
	public int deleteDTO(String name, String phone) {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int n = 0;
		
		try {
			String sql = "DELETE FROM EMPDB WHERE NAME=? AND PHONE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2, phone);
			n = pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			}
		}catch(SQLException e){
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return n;
	}
	
	public int updateDTO(EmpDTO dto, int cnt) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int n = 0;
		
		try {
			String sql = "UPDATE EMPDB SET NAME=?, BIRTH=?, PHONE=?, ADDR=?, EMAIL=? WHERE IDX=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dto.getName());
			pstmt.setDate(2,java.sql.Date.valueOf(dto.getBirth()));
			pstmt.setString(3,dto.getPhone());
			pstmt.setString(4,dto.getAddr());
			pstmt.setString(5,dto.getEmail());
			pstmt.setInt(6, cnt);
			n = pstmt.executeUpdate();
			
			if(n>0) {
				commit(conn);
			}
		}catch(SQLException e){
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return n;
	}
	
	public EmpDTO getDTO(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int n = 0;
		ResultSet res=null;
		EmpDTO dto = new EmpDTO();
		
		try {
			String sql = "SELECT * FROM EMPDB WHERE IDX=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				dto.setName(res.getString("NAME"));
				dto.setBirth(res.getString("BIRTH"));
				dto.setPhone(res.getString("PHONE"));
				dto.setAddr(res.getString("ADDR"));
				dto.setEmail(res.getString("EMAIL"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return dto;
	}
}
