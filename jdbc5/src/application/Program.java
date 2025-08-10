package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityExeption;

public class Program {

	public static void main(String[] args) { 
		
		Connection conn = null;
		PreparedStatement st = null;
				
	try {
		conn = DB.getConnection();
		
		st = conn.prepareStatement(
				"Delete From department "
				+ "WHERE "
				+ "Id = ?");
				
		st.setInt(1, 3);
		
		
		//verificar se uma linha foi ou não alteradas
		int linhasAlteradas = st.executeUpdate();
		System.out.println("Quantidade de linhas alteradas: "
				+ linhasAlteradas);
		
	}catch (SQLException e) {
		throw new DbIntegrityExeption(e.getMessage());
	}finally {
		DB.closeStatement(st);
		DB.closeConection();
	}
	}
		
		

}

