package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente{
	public static String NATURAL = "Natural";
	private String nombre;
	
	public ClienteNatural(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNatural() {
		return NATURAL;
	}
	public void setNatural(String natural) {
		NATURAL = natural;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String getTipoCliente() {
 		return NATURAL;
	}
	@Override
	public String getIdentificador() {
		
		return nombre;
	}
	

}
