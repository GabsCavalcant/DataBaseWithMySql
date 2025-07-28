package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) { 
		
		Connection conn = null;
		PreparedStatement st = null;
				
	try {
		conn = DB.getConnection();
		
		st = conn.prepareStatement(
				"UPDATE seller "
				+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ? )");
		st.setDouble(1, 200.0);
		st.setInt(2, 2);
		
		//verificar se uma linha foi ou não alteradas
		int linhasAlteradas = st.executeUpdate();
		System.out.println("Quantidade de linhas alteradas: "
				+ linhasAlteradas);
		
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		DB.closeStatement(st);
		DB.closeConection();
	}
	}
		
		

}

