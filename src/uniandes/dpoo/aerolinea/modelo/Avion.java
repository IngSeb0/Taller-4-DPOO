package uniandes.dpoo.aerolinea.modelo;


public class Avion {
	private String nombreAvion;
	private int capacidad;

	public Avion(String nombre, int capacidad) {
		super();
		this.nombreAvion = nombre;
		this.capacidad = capacidad;
	}

	public String getNombre() {
		return nombreAvion;
	}
	
	public void setNombre(String nombre) {
		this.nombreAvion = nombre;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	

}
