package es.uniovi.asw.trivial.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Jdbc {

	private static String SQL_DRV = "org.hsqldb.jdbcDriver";
	private static String SQL_URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";
	private static String PASS = "";

	static {
		try {
			Class.forName(SQL_DRV);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection con =DriverManager.getConnection(SQL_URL, USER, PASS);
		con.setAutoCommit(false);
		return con;
	}
	
	public static void close(ResultSet rs, Statement st, Connection c) {
		close(rs);
		close(st);
		close(c);
	}

	public static void close(ResultSet rs, Statement st) {
		close(rs);
		close(st);
	}

	protected static void close(ResultSet rs) {
		if (rs != null) try { rs.close(); } catch(SQLException e) {};
	}

	public static void close(Statement st) {
		if (st != null ) try { st.close(); } catch(SQLException e) {};
	}

	public static void close(Connection c) {
		if (c != null) try { c.close(); } catch(SQLException e) {};
	}


}