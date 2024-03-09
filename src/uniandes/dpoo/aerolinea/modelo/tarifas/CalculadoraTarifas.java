package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
public abstract class CalculadoraTarifas {
	public double IMPUESTO = 0.28;
	public  int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		return 0;
	}
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente) ;
	protected  int calcularDistanciaVuelo(Ruta ruta) {
		Aeropuerto aeropuertoOrigen = ruta.getOrigen();
        Aeropuerto aeropuertoDestino = ruta.getDestino();
        
        // Calcula la distancia entre los aeropuertos
        int distancia = Aeropuerto.calcularDistancia(aeropuertoOrigen, aeropuertoDestino);
		return distancia;
	}
	
	
	
	protected  int calcularValorImpuestos(int costoBase){
		return (int) Math.round(costoBase * IMPUESTO);
    
	}

	

}
