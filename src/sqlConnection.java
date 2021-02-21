import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqlConnection {

	public static Connection dbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentjava", "root", "");
			JOptionPane.showMessageDialog(null, "Connection successfully");
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database has error : " + e);
			return null;
		}
	}
}
