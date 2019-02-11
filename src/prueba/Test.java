package prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		Test javaMySQLBasic = new Test();
		javaMySQLBasic.conectarBaseDatos();

	}

	public void conectarBaseDatos() {
		try {
			try {
				// Se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			boolean valid = connection.isValid(5000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			// insertarRegistro(connection);
			// actualizaRegistro(connection);
			// borraRegistros(connection);
			selectRegistros(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	public void insertarRegistro(Connection conn) throws SQLException {
		String sql = "INSERT INTO usuario (user, pass, nombre, correo) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, "Manu1234");
		statement.setString(2, "passManu");
		statement.setString(3, "Manu");
		statement.setString(4, "manu@aytos.es");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado!");
		}
	}

	public void actualizaRegistro(Connection conn) throws SQLException {
		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre=?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, "actuManu1234");
		statement.setString(2, "actupassManu");
		statement.setString(3, "actumanu@aytos.es");
		statement.setString(4, "Manu");

		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Actualizado!");
		}
	}

	public void borraRegistros(Connection conn) throws SQLException {
		String sql = "DELETE FROM usuario WHERE user=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "actuManu1234");

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Registro eliminado!");
		}
	}

	public void selectRegistros(Connection conn) throws SQLException {
		String sql = "Select * FROM usuario";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String pass = result.getString(2);
			String nombre1 = result.getString(3);
			String nombre = result.getString("nombre");
			String email = result.getString("correo");
			count++;
			System.out.println(count + " " + pass + " " + nombre1 + " " + nombre + " " + email);
		}
	}
}
