package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import banco.Banco;
import banco.BancoException;

public class Program {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = Banco.abreConexao();
			
			st = conn.prepareStatement("insert into tb_patinadores (Nome_patinador, Sobre_Nome,"
					+ " categoria, modulo) values ( ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Marx");
			st.setString(2, "da Pri");
			st.setString(3, "Intermediario");
			st.setInt(4, 2);
			
			int linhasAfetadas = st.executeUpdate();
			
			System.out.println("Concluido! Linhas altaredas: " + linhasAfetadas);
			
		}
		catch(SQLException e) {
			throw new BancoException(e.getMessage());
		}
		finally {
			Banco.fecharStatement(st);
			Banco.fecharConexao();
		}
		
		
		/*
		 * 
		 * CODIGO RESERVADO PARA CONSULTA DE INFORMAÇÕES NO BANCO!
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
		
		
		*/
		
	}

}
