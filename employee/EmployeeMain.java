package kr.sys4u.employee;

public class EmployeeMain {

	public static void main(String[] args) {

		DataBaseConnectionPool DBCP = new DataBaseConnectionPool();
		EmployeeDAO eDao = new EmployeeDAO(DBCP);
		
		for(Employee e : eDao.getEmployees(0)) {
			System.out.println(e.toString());
		}
		
	}

}
