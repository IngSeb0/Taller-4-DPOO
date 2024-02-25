package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente{
	public String Natural = "Natural";
	private String nombre;
	public String getNatural() {
		return Natural;
	}
	public void setNatural(String natural) {
		Natural = natural;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String getTipoCliente() {
 		return Natural;
	}
	@Override
	public String getIdentificador() {
		
		return nombre;
	}
	

}
