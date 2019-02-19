package kr.sys4u.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	private final DataBaseConnectionPool DBCP;
	
	public EmployeeDAO(DataBaseConnectionPool DBCP) {
		this.DBCP = DBCP;
	}
	
	public List<Employee> getEmployees(int page){
		String sql = "SELECT TAB.*"
				   + "  FROM (SELECT ROWNUM RN, TAB1.* "
				   		     + "FROM (SELECT * FROM EMP ORDER BY EMPNO ASC) TAB1 "
				   		    + "WHERE ROWNUM <= "+ 5 * page + ") TAB "
				   	+ "WHERE RN >= " + (5 * (page-1) + 1);
		
		List<Employee> employeeList = new ArrayList<>();
		
		ResultSet rSet = null;
		try(Connection conn = DBCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			rSet = pstmt.executeQuery();
			while(rSet.next()) {
				employeeList.add(new Employee(rSet.getInt("EMPNO"), 
											rSet.getString("ENAME"), 
											rSet.getString("JOB"), 
											rSet.getInt("MGR"), 
											rSet.getDate("HIREDATE"), 
											rSet.getInt("SAL"), 
											rSet.getInt("COMM"), 
											rSet.getInt("DEPTNO")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rSet != null) {
				try {rSet.close();} catch (SQLException e) {}
			}
		}
		return employeeList;
	}
	
	public int getEmployeeCount() {
		return 0;
	}
	
	public int insertEmployee(Employee e) {
		return 0;
	}
	
}
