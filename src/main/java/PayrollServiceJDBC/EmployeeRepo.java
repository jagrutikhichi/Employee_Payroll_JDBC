package PayrollServiceJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
	
	public void insertRecord(Information info) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
		//Step1: Load & Register Driver Class
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
		
		//Step2: Establish a MySql Connection
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service?allowPublicKeyRetrieval=true&useSSL=false", "root", "pass123");
		
		//Step3: Create Statement
		 statement = connection.createStatement();
		
		//Step4: Execute Query
		String query = "insert into payroll_service(Name,department,gender, basicpay) value('"+info.getName()+"','"+info.getDepartment()+"','"+info.getGender()+"','"+info.getBasicPay()+"')";
		int result = statement.executeUpdate(query);
		System.out.print(result);
		
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
			statement.close();
			}
			if(statement != null) {
			connection.close();
			}
		}
		
	}

	public List<Information> findAll() throws SQLException {
		List<Information> infos=new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		try {
		//Step1: Load & Register Driver Class
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
		
		//Step2: Establish a MySql Connection
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service?allowPublicKeyRetrieval=true&useSSL=false", "root", "pass123");
		
		//Step3: Create Statement
		 statement = connection.createStatement();
		
		//Step4: Execute Query
		String query =" select * from payroll_service";
		ResultSet resultset = statement.executeQuery(query);
	
		while(resultset.next()) {
			Information information = new Information();
			
			int id=resultset.getInt(1);
			information.setId(id);
			
			String name = resultset.getString(2);
			information.setName(name);
			
			String dept = resultset.getString(5);
			information.setDepartment(dept);
			
			String gender = resultset.getString(6);
			information.setGender(gender);
			
			Double pay = resultset.getDouble(7);
			information.setBasicPay(pay);
			
			infos.add(information);
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			if(connection != null) {
			statement.close();
			}
			if(statement != null) {
			connection.close();
			}
		}
		return infos;
	}

	public void updatedata(int id, double basicPay) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
		//Step1: Load & Register Driver Class
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
		
		//Step2: Establish a MySql Connection
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service?allowPublicKeyRetrieval=true&useSSL=false", "root", "pass123");
		
		//Step3: Create Statement
		 statement = connection.createStatement();
		
		//Step4: Execute Query
		String query ="Update payroll_service set basicPay="+basicPay+"where Id="+id+"";
		statement.executeUpdate(query);
		System.out.print("Records Updated!");
		
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
			statement.close();
			}
			if(statement != null) {
			connection.close();
			}
		}
		
	}
}
