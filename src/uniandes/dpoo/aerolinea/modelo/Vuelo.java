package uniandes.dpoo.aerolinea.modelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import java.util.Collection;
public class Vuelo {
	private Collection<Tiquete> tiquetes;
	private String fecha;
	private Ruta ruta;
	private Avion avion;
	


public Vuelo(Ruta ruta, String fecha, Avion avion) {
		
		this.ruta= ruta;
		this.avion = avion;
		
		
		this.fecha = fecha;
		
	}

public Collection<Tiquete> getTiquetes() {
	return tiquetes;
}

public void setTiquetes(Collection<Tiquete> tiquetes) {
	this.tiquetes = tiquetes;
}

public Ruta getRuta() {
	return ruta;
	}


public Avion getAvion() {
	return avion;
}

public void setAvion(Avion avion) {
	this.avion = avion;
}

public void setRuta(Ruta ruta) {
	this.ruta = ruta;
}

public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
	int valorInt = (int) calculadora.calcularTarifa(this, cliente);
	return valorInt;
	
}}