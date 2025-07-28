package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// conexão com o banco
			conn = DB.getConnection();

			// Statemente instanciado com o creatStatement
			st = conn.createStatement();
			// Em seguida pega a variavel st, utilize o executeQuery
			// que aguarda uma string para mostrar o resultado em tabela.
			rs = st.executeQuery("Select *from department");

			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			finally {
				DB.closeResultSet(rs);
				DB.closeStatement(st);
				DB.closeConection();
			}
	}

}
