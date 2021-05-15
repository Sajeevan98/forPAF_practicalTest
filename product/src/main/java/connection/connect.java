package connection;

import java.sql.*;

public class connect {
	
	Connection connect;
	public Connection getConnection() {
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");		 
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "");
				
				
		}catch(Exception ex){
			
		}
		return connect;
	}
}
