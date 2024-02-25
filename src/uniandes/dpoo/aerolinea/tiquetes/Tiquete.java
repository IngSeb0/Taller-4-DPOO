package uniandes.dpoo.aerolinea.tiquetes;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
public class Tiquete {
	private String codigo;
	private int tarifa;
	private boolean usado;
	private Cliente clienteComprador;
	private Vuelo vuelo;
	public Tiquete(String codigo,Vuelo vuelo, Cliente clienteComprador, int tarifa) {
		super();
		this.clienteComprador = clienteComprador;
		this.vuelo = vuelo;
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.usado = false;
		
		
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getTarifa() {
		return tarifa;
	}
	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}
	public boolean isUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	public Cliente getClienteComprador() {
		return clienteComprador;
	}
	public void setClienteComprador(Cliente clienteComprador) {
		this.clienteComprador = clienteComprador;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public void marcarComoUsado() {
		this.usado = true;
	}
	public boolean esUsado() {
		return usado;
	}
}
