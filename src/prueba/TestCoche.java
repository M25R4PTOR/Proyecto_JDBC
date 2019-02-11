package prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TestCoche {

	public static void main(String[] args) {
		TestCoche javaMySQLBasic = new TestCoche();
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

			// insertarCoche(connection, "9999XXX", "Y", 0, "Negro", 4999.99);
			// actualizarCochePorMatricula(connection, "9999XXX", "ActuY", 110,
			// "Negro-Blanco", 999.99);
			// borraCochePorMatricula(connection, "0000XXX");
			// selectCoches(connection);
			menu(connection);

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
	}

	private static java.sql.Timestamp getCurrentTimestamp(java.util.Date fecha) {
		// java.util.Date hoy = new java.util.Date();
		return new java.sql.Timestamp(fecha.getTime());
	}

	public static void insertarCoche(Connection conn, String matricula, String modelo, Integer kilometros, String color,
			Double precio) throws SQLException {
		String sql = "INSERT INTO coches (matricula, fecha, modelo, kilometros, color, precio) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, matricula);
		statement.setTimestamp(2, getCurrentTimestamp(new java.util.Date()));
		statement.setString(3, modelo);
		statement.setInt(4, kilometros);
		statement.setString(5, color);
		statement.setDouble(6, precio);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche insertado!");
		}
	}

	public static void actualizarCochePorMatricula(Connection conn, String matricula, String modelo, Integer kilometros,
			String color, Double precio) throws SQLException {
		String sql = "UPDATE coches SET fecha=?, modelo=?, kilometros=?, color=?, precio=? WHERE matricula=?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setTimestamp(1, getCurrentTimestamp(new java.util.Date()));
		statement.setString(2, modelo);
		statement.setInt(3, kilometros);
		statement.setString(4, color);
		statement.setDouble(5, precio);
		statement.setString(6, matricula);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Coche " + matricula + " actualizado!");
		}
	}

	public static void borraCochePorMatricula(Connection conn, String matricula) throws SQLException {
		String sql = "DELETE FROM coches WHERE matricula=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, matricula);

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Coche " + matricula + " eliminado!");
		}
	}

	public static void selectCoches(Connection conn) throws SQLException {
		String sql = "Select * FROM coches";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			Coche coche = new Coche(result.getString(1), result.getDate(2), result.getString(3), result.getInt(4),
					result.getString(5), result.getDouble(6));

			count++;
			JOptionPane.showMessageDialog(null, count + " - " + coche);
		}
	}

	public static void selectCochesPorMatricula(Connection conn, String matricula) throws SQLException {
		String sql = "Select * FROM coches WHERE matricula =?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, matricula);
		ResultSet result = statement.executeQuery();
		Coche coche = new Coche(result.getString(1), result.getDate(2), result.getString(3), result.getInt(4),
				result.getString(5), result.getDouble(6));
		JOptionPane.showMessageDialog(null, coche);
	}

	private static void menu(Connection con) throws SQLException {
		String eleccion = "5";
		do {
			eleccion = (String) JOptionPane.showInputDialog(null,
					"---Menu---\n1. Mostrar coches\n2. Insertar coche\n3. Actualizar coche\n4. Borrar coche\n5. Mostrar coche por matricula\n0. Salir");
			if (eleccion == null) {
				eleccion = "0";
			}
			switch (eleccion) {
			case "1":
				selectCoches(con);
				break;
			case "2":
				String matricula = (String) JOptionPane.showInputDialog(null, "Introduzca la matrícula");
				String modelo = (String) JOptionPane.showInputDialog(null, "Introduzca el modelo");
				String km = (String) JOptionPane.showInputDialog(null, "Introduzca los kilómetros");
				String color = (String) JOptionPane.showInputDialog(null, "Introduzca el color");
				String precio = (String) JOptionPane.showInputDialog(null, "Introduzca el precio");
				insertarCoche(con, matricula, modelo, Integer.parseInt(km), color, Double.valueOf(precio));
				break;
			case "3":
				String matriculaAct = (String) JOptionPane.showInputDialog(null,
						"Introduzca la matrícula del coche a modificar");
				String modeloAct = (String) JOptionPane.showInputDialog(null, "Introduzca el modelo nuevo");
				String kmAct = (String) JOptionPane.showInputDialog(null, "Introduzca los kilómetros nuevos");
				String colorAct = (String) JOptionPane.showInputDialog(null, "Introduzca el color nuevo");
				String precioAct = (String) JOptionPane.showInputDialog(null, "Introduzca el precio nuevo");
				actualizarCochePorMatricula(con, matriculaAct, modeloAct, Integer.parseInt(kmAct), colorAct,
						Double.valueOf(precioAct));
				break;
			case "4":
				String matriculaBorrar = (String) JOptionPane.showInputDialog(null,
						"Introduzca la matrícula del coche a eliminar");
				borraCochePorMatricula(con, matriculaBorrar);
				break;
			case "5":
				String matriculaSel = (String) JOptionPane.showInputDialog(null,
						"Introduzca la matrícula del coche a buscar");
				selectCochesPorMatricula(con, matriculaSel);
				break;
			case "0":
				break;
			default:
				eleccion = "5";
				JOptionPane.showMessageDialog(null, "Esa opción no es correcta");
				break;
			}
		} while (!eleccion.equals("0"));
	}
}
