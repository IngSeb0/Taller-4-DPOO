package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
public abstract class CalculadoraTarifas {
	public double IMPUESTO = 0.28;
	public  int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		return 0;
	}
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente) ;
		
	
	protected  int calcularValorImpuestos(int costoBase){
		return 0;
	}

	

}
