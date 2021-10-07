package banco;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Banco {

	private static Connection conn = null;

	public static Connection abreConexao() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);

			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
		return conn;
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("banco.propriedades")) {
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		} catch (IOException e) {
			throw new BancoException(e.getMessage());
		}

	}

	public static void fecharConexao() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}

	}
	
	public static void fecharStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
	}
	
	public static void fecharResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
	}

}
