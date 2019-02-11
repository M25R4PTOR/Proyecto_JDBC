package prueba;

import java.sql.Date;

public class Coche {

	private String matricula;
	private Date fecha;
	private String modelo;
	private Integer kilometros;
	private String color;
	private Double precio;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getKilometros() {
		return kilometros;
	}

	public void setKilometros(Integer kilometros) {
		this.kilometros = kilometros;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Coche(String matricula, Date fecha, String modelo, Integer kilometros, String color, Double precio) {
		super();
		this.matricula = matricula;
		this.fecha = fecha;
		this.modelo = modelo;
		this.kilometros = kilometros;
		this.color = color;
		this.precio = precio;
	}

	public Coche() {
		super();
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", fecha=" + fecha + ", modelo=" + modelo + ", kilometros="
				+ kilometros + ", color=" + color + ", precio=" + precio + "]";
	}

}
