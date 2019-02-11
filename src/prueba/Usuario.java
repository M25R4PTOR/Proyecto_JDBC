package prueba;

public class Usuario {

	private String user;
	private String pass;
	private String nombre;
	private String correo;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Usuario(String user, String pass, String nombre, String correo) {
		super();
		this.user = user;
		this.pass = pass;
		this.nombre = nombre;
		this.correo = correo;
	}

	public Usuario() {
		super();
	}

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", pass=" + pass + ", nombre=" + nombre + ", correo=" + correo + "]";
	}

}
