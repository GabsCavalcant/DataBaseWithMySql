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
	SimpleDateFormat sdl = new SimpleDateFormat("dd/MM/yyyy");
	Connection conn = null;
	PreparedStatement st = null;
	try
	 {//conectando com o banco, e uiltizando o prepareStatement para
		//colocar linhas do mysql
		conn = DB.getConnection();
		
		st = conn.prepareStatement(
				"INSERT INTO seller "
				+ "(name, Email, BirthDate, BaseSalary, DepartmentId) "
				+ "VALUES "	
				+ "(?,?,?,?,?)", //Os ? funciona como "partes" a serem inseridas
				Statement.RETURN_GENERATED_KEYS);
		
		//Inserindo as partes no ?? com seus tipos
		st.setString(1, "Carl Purple");
		st.setString(2, "CarlEmail2@gmail.com");
		st.setDate(3, new java.sql.Date(sdl.parse("24/06/2005").getTime()));
		st.setDouble(4, 2000.0);
		st.setInt(5, 4);
		
		//Linha Para verificar quais linhas foram alteradas.
		int linhasMudadas = st.executeUpdate();
		
		if (linhasMudadas > 0 ) {
			ResultSet rs =  st.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1);
				System.out.println("O id é = " + id);
			}
			
		}else {
			System.out.println("Nenhuma Linha alterada!");
		}
	
	}catch (SQLException e) {
		e.printStackTrace();
	}catch (ParseException e) {
		e.printStackTrace();
	}finally {
		DB.closeStatement(st);
		DB.closeConection();
	}	
		

}
}
