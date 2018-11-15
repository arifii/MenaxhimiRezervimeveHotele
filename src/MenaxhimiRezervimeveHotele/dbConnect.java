package MenaxhimiRezervimeveHotele;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class dbConnect 
{
	public static Connection connectDb(String database, String user, String pass)
	{
		try
		{
			//Will register the driver with the DriverManager so you can create mysql connections	
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/MenaxhimiRezervimeveHotele?characterEncoding=UTF-8&useSSL=false", user, pass);
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			return null;
		}
	}
	public static Connection connectDb()
	{
		try
		{
			//Will register the driver with the DriverManager so you can create mysql connections	
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/MenaxhimiRezervimeveHotele?characterEncoding=UTF-8&useSSL=false", "root", "1234");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			return null;
		}
	}
}