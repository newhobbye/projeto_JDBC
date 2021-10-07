package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import banco.Banco;
import banco.BancoException;

public class Program {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = Banco.abreConexao();
			st = conn.createStatement();
			rs = st.executeQuery("select * from tb_patinadores");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id_patinador") + ", " + rs.getString("Nome_patinador") + 
						", " + rs.getString("Sobre_Nome") + ", " + rs.getString("categoria") + ", " +
						rs.getInt("modulo"));
			}
		}catch (SQLException e) {
			throw new BancoException(e.getMessage());
		}finally {
			Banco.fecharStatement(st);
			Banco.fecharResultSet(rs);
			Banco.fecharConexao();
		}
		
	}

}
