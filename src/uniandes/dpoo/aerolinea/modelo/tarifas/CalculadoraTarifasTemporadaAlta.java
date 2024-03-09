package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		Ruta ruta = vuelo.getRuta();

        // Calcular la distancia del vuelo utilizando el método heredado
        int distanciaVuelo = calcularDistanciaVuelo(ruta);

        // Calcular el costo base multiplicando la distancia por el costo por kilómetro
        int costoBase = distanciaVuelo * COSTO_POR_KM;

        return costoBase;
	}

	@Override
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		// Se calcula la misma tarifa para todos los clientes
		return 0;
	}

	protected int COSTO_POR_KM = 1000;
	

}
