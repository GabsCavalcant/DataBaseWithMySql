package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbExeption;
import db.DbIntegrityExeption;

public class Program {

	public static void main(String[] args) { 
		
		Connection conn = null;
		//PreparedStatement st = null;
		Statement st = null;
				
	try {
		conn = DB.getConnection();
		st = conn.createStatement();
		
		
		//começar sempre com o commit False para evitar esse tipo de problema.
		conn.setAutoCommit(false);
		
		int rowns1 = st.executeUpdate("UPDATE  seller "
				+ "SET BaseSalary = 2090 WHERE DepartmentId = 1"); 
		
		int rowns2 = st.executeUpdate("UPDATE  seller "
				+ "SET BaseSalary = 3090 WHERE DepartmentId = 2");
	
		
		
		
		
		/*
		Simular uma falha pra verificar a exceção
		pois é um erro comum que ocorre, onde por algum problema  
		no meio os linhas só executam oprimeiro bloco e ignoram o resto
		 o obejetivo desse programa é evitar isso. (solução linha 24) */
		 
		int x = 1; 
				if(x < 2 ) {
					
					throw new SQLException("Fake error ");
					
				}
		conn.commit();
		
		System.out.println("rows 1 = " + rowns1 );

		System.out.println("rows 2 = " + rowns2 );
		
	}catch (SQLException e) {
		try {
			conn.rollback();
			throw new DbExeption("Transction rolled back,"
					+ "Cause by:  "+ e.getMessage());
		} catch (SQLException e1) {
			throw new DbExeption("Error trying to rollsback by : " + e1.getMessage());
		}
		
	}finally {
		DB.closeStatement(st);
		DB.closeConection();
	}
	}
		
		

}

