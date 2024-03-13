package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTO_POR_KM_COPORATIVO = 900;
	protected double DESCUENTO_PEQ = 0.02;
	protected double DESCUENTO_MEDIANAS = 0.1;
	protected double DESCUENTO_GRANDES= 0.2;
	
	
	
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		// Obtener la ruta del vuelo
	    Ruta ruta = vuelo.getRuta();

	    // Calcular la distancia del vuelo utilizando el método heredado
	    int distanciaVuelo = calcularDistanciaVuelo(ruta);

	    // Obtener el tipo de cliente
	    String tipoCliente = cliente.getTipoCliente();

	    // Verificar el tipo de cliente y aplicar el costo por kilómetro correspondiente
	    if (tipoCliente.equals("corporativo")) {
	        // Cliente corporativo: Aplicar costo por kilómetro para cliente corporativo
	        return distanciaVuelo * COSTO_POR_KM_COPORATIVO;
	    } else {
	        // Cliente natural: Aplicar costo por kilómetro para cliente natural
	        return distanciaVuelo * COSTO_POR_KM_NATURAL;
	    }
	}
	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		
		if (cliente.getTipoCliente().equals("corporativo")) {
			ClienteCorporativo cliente1 = (ClienteCorporativo) cliente;
		
			int tamañoEmpresa = cliente1.getTamanoEmpresa();

		    // Aplicar el porcentaje de descuento según el tamaño de la empresa
		    if (tamañoEmpresa == 1) {
		        return DESCUENTO_PEQ;
		    } else if (tamañoEmpresa == 2) {
		        return DESCUENTO_MEDIANAS;
		    } else if (tamañoEmpresa == 3) {
		        return DESCUENTO_GRANDES;
		    }
		
	}
		return 0;

	
	

}
}

