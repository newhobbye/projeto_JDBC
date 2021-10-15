package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import banco.Banco;
import banco.BancoException;

public class Program {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = Banco.abreConexao();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows = st.executeUpdate("update tb_patinadores set modulo = 6 where Id_patinador = 1");
			/*
			int x = 1;
			if(x < 2) {
				throw new SQLException("Erro Falso");
			}
			*/
			int rows2 = st.executeUpdate("update tb_patinadores set modulo = 6 where Id_patinador = 3");
			
			conn.commit();
			
			System.out.println("rows: " + rows);
			System.out.println("rows2: " + rows2);
			
		}
		 catch (SQLException e) {
				try {
					conn.rollback(); // desfazer a transação caso de algum erro
					throw new BancoException("Transaction roller back! Caused by: " + e.getMessage());
				} catch (SQLException e1) {
					throw new BancoException("Erro no rollback" + e1.getMessage());
				}
			}finally {
				Banco.fecharStatement(st);
				Banco.fecharConexao();
				
			}
			/*
			st.setInt(1, 6);
			
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
		*/
		
		/*
		FORMA SIMPLES DE ALTERAR DADOS COM UPDATE!!
		try {
			conn = Banco.abreConexao();
			st = conn.prepareStatement("update tb_patinadores set modulo = ? where (Id_patinador = 3)");
			st.setInt(1, 5);
			
			int linhasAfetadas = st.executeUpdate();
			
			System.out.println("Concluido! Linhas altaredas: " + linhasAfetadas);
		}
		catch(SQLException e) {
			throw new BancoException(e.getMessage());
		}finally {
			Banco.fecharStatement(st);
			Banco.fecharConexao();
		
		
		}
		*/
		/*
		 FORMA SIMPLES DE INSERIR DADOS NO BANCO!
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
		
		*/
		
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
