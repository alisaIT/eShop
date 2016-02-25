package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBVerbindung
{
	public DBVerbindung()
	{
	}

	// Variablen für den Verbindungsaufbau
	Connection conn = null;
	Statement stmtSQL = null;

	public void oeffneDB()
	{
		try
		{
			System.out.println("* Treiber laden");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println(Class.forName("com.mysql.jdbc.Driver"));
			System.out.println(Class.forName("com.mysql.jdbc.Driver").newInstance());
		}
		catch (Exception e)
		{
			System.err.println("Unable to load driver.");
			e.printStackTrace();
		}

		// Connection
		try
		{
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost/bücher?user=root&password=root");
			stmtSQL = conn.createStatement(); // (3)
			System.out.println("Connection etabliert");
		}
		catch (SQLException ex)
		{
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			JOptionPane.showMessageDialog(null, "keine Datenbank geladen");
		}
	}

	public ResultSet lesen(String pSQL)
	{
		ResultSet rs;
		try
		{
			rs = stmtSQL.executeQuery(pSQL);
			return rs;
		}
		catch (SQLException err)
		{
			System.out.println("Error "+err);
			rs = null;
			return rs;
		}
	}
	
	public void schreibe(String pSQL)
	{
		try
		{
			stmtSQL.execute(pSQL);
			
		}
		catch (SQLException err)
		{
			System.out.println("Error "+err);
			
		}
	}

	public void schliesseDB()
	{
		try
		{
			stmtSQL.close();
			conn.close();
		}
		catch (SQLException err)
		{
			
		}
	}
}