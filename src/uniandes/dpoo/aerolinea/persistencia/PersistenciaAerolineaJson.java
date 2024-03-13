package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;


import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea{
	private static final String NOMBRE_AEROPUERTO= "nombre";
    private static final String CODIGO_AEROPUERTO1 = "codigo1";
    private static final String CODIGO_AEROPUERTO2 = "codigo2";
    private static final String NOMBRE_CIUDAD1 = "nombreCiudad1";
    private static final String NOMBRE_CIUDAD2 = "nombreCiudad2";
    private static final String LATITUD1 = "latitud1";
    private static final String LONGITUD1 = "longitud1";
    private static final String LATITUD2 = "latitud2";
    private static final String LONGITUD2 = "longitud2";
    private static final String CODIGOS_UTILIZADOS = "codigosUtilizados";
    private static final String  NOMBRE_AVION = "nombreAvion";
    private static final String CAPACIDAD_AVION = "capacidad";
    private static final String ORIGEN = "origen";
  
    private static final String  DESTINO= "destino";
    private static final String HORA_SALIDA = "horaSalida";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String TIQUETES = "tiquetes";
    private static final String FECHA = "fecha";
    private static final String RUTAS = "rutas";
    private static final String AVION = "avion";
    
    private static final String VUELO = "vuelo";
    

	@Override
    public void cargarAerolinea( String archivo, Aerolinea aerolinea )throws IOException, InformacionInconsistenteException, AeropuertoDuplicadoException{
    {
        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );

     
        
        cargarVuelo( aerolinea, raiz.getJSONArray( "vuelo" ) );
        cargarAvion( aerolinea, raiz.getJSONArray( "avion" ) );
        cargarRuta( aerolinea, raiz.getJSONArray( "ruta" ) );
    }
        
    }

    /**
     * Salva en un archivo toda la información sobre los clientes y los tiquetes vendidos por la aerolínea
     * @param archivo La ruta al archivo donde debe quedar almacenada la información
     * @param aerolinea La aerolínea que tiene la información que se quiere almacenar
     * @throws IOException Se lanza esta excepción si hay problemas escribiendo el archivo
     */
    @Override
    public void salvarAerolinea( String archivo, Aerolinea aerolinea )throws IOException, AeropuertoDuplicadoException{
        JSONObject jobject = new JSONObject( );

       
        

        
        salvarVuelos( aerolinea, jobject );
        

        
        salvarRutas( aerolinea, jobject );
        salvarAvion( aerolinea, jobject );

        // Escribir la estructura JSON en un archivo
        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
    }

    private void cargarVuelo( Aerolinea aerolinea, JSONArray jVuelo ) 
    {
        int numVuelo = jVuelo.length( );
        for( int i = 0; i < numVuelo ; i++ )
        {
            JSONObject Vuelo = jVuelo.getJSONObject( i );
            
            String fecha= Vuelo.getString( FECHA );
            Ruta Ruta= aerolinea.getRuta(RUTAS);
           
            Avion Avion = aerolinea.getAvion(NOMBRE_AVION);
            Vuelo nuevoVuelo = null;
           
             
             nuevoVuelo = new Vuelo(Ruta, fecha,Avion );
            
           
                aerolinea.agregarVuelos( nuevoVuelo );
           
        }
    }

    /**
     * Salva la información de los clientes de la aerolínea dentro del objeto json que se recibe por parámetro.
     * 
     * La información de los clientes queda dentro de la llave 'clientes'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información de los clientes
     */
    private void salvarVuelos( Aerolinea aerolinea, JSONObject jobject )
    {
    	 JSONArray jVuelos = new JSONArray();
    	    for (Vuelo vuelo : aerolinea.getVuelos()) {
    	        JSONObject jVuelo = new JSONObject();
    	        jVuelo.put(FECHA, vuelo.getFecha());
    	        jVuelo.put(AVION, vuelo.getAvion());
    	        jVuelo.put(CODIGO_RUTA, vuelo.getRuta().getCodigoRuta());
    	        // Agregar atributos adicionales del vuelo
    	        jVuelo.put("tiquetes", vuelo.getTiquetes()); // Aquí puedes cambiar "tiquetes" por el nombre que desees para el arreglo de tiquetes

    	        // Agregar el objeto JSON del vuelo al arreglo de vuelos
    	        jVuelos.put(jVuelo);
    	    }

    	    // Agregar el arreglo de vuelos al objeto JSON principal
    	    jobject.put(VUELO, jVuelos);
    }

    private void cargarRuta( Aerolinea aerolinea, JSONArray jRuta) throws AeropuertoDuplicadoException
    {
        int numRuta= jRuta.length( );
        for( int i = 0; i < numRuta ; i++ )
        {
            JSONObject Ruta = jRuta.getJSONObject( i );
            
            Aeropuerto origen = new Aeropuerto(Ruta.getString(ORIGEN),Ruta.getString(CODIGO_AEROPUERTO1), Ruta.getString(NOMBRE_CIUDAD1), Ruta.getDouble(LATITUD1), Ruta.getDouble(LONGITUD1));
            Aeropuerto destino = new Aeropuerto(Ruta.getString(DESTINO),Ruta.getString(CODIGO_AEROPUERTO2), Ruta.getString(NOMBRE_CIUDAD2), Ruta.getDouble(LATITUD2), Ruta.getDouble(LONGITUD2) );
            String horaSalida = Ruta.getString(HORA_SALIDA);
            String horaLlegada= Ruta.getString(HORA_LLEGADA);
            String ruta = Ruta.getString(CODIGO_RUTA);
            
            Ruta nuevaRuta = null;
           
             
             nuevaRuta = new Ruta(origen,destino, horaSalida,horaLlegada, ruta);
            
           
                aerolinea.agregarRuta( nuevaRuta );
           
        }
    }

    /**
     * Salva la información de los clientes de la aerolínea dentro del objeto json que se recibe por parámetro.
     * 
     * La información de los clientes queda dentro de la llave 'clientes'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información de los clientes
     */
    private void salvarRutas( Aerolinea aerolinea, JSONObject jobject ) throws AeropuertoDuplicadoException {
    Set<String> codigosAeropuerto = new HashSet<>(); // Conjunto para almacenar los códigos de aeropuerto

    JSONArray jRutas = new JSONArray();
    for (Ruta ruta : aerolinea.getRutas()) {
        // Obtener los aeropuertos de la ruta y verificar si los códigos ya han sido utilizados
        String codigoAeropuertoOrigen = ruta.getOrigen().getCodigo();
        String codigoAeropuertoDestino = ruta.getDestino().getCodigo();

        if (codigosAeropuerto.contains(codigoAeropuertoOrigen) || codigosAeropuerto.contains(codigoAeropuertoDestino)) {
            throw new AeropuertoDuplicadoException("El aeropuerto con código " + codigoAeropuertoOrigen + " o " + codigoAeropuertoDestino + " ya ha sido agregado a una ruta.");
        }

        // Agregar los códigos de aeropuerto al conjunto
        codigosAeropuerto.add(codigoAeropuertoOrigen);
        codigosAeropuerto.add(codigoAeropuertoDestino);

        // Crear el objeto JSON para la ruta y agregarlo al arreglo de rutas
        JSONObject jRuta = new JSONObject();
        jRuta.put(ORIGEN, codigoAeropuertoOrigen);
        jRuta.put(DESTINO, codigoAeropuertoDestino);
        jRuta.put(HORA_SALIDA, ruta.getHoraSalida());
        jRuta.put(HORA_LLEGADA, ruta.getHoraLlegada());
        jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());

        jRutas.put(jRuta);
    }

    // Agregar el arreglo de rutas al objeto JSON principal
    jobject.put(RUTAS, jRutas);
}
	
	private void cargarAvion( Aerolinea aerolinea, JSONArray jAvion) 
    {
        int numAvion= jAvion.length( );
        for( int i = 0; i < numAvion ; i++ )
        {
            JSONObject Avion = jAvion.getJSONObject( i );
            
            String nombre = Avion.getString(NOMBRE_AVION);
            int capacidad = Avion.getInt(CAPACIDAD_AVION);
            
            
            Avion nuevoAvion = null;
           
             
            nuevoAvion = new Avion(nombre, capacidad);
            
           
                aerolinea.agregarAvion( nuevoAvion );
           
        }
    }

    /**
     * Salva la información de los clientes de la aerolínea dentro del objeto json que se recibe por parámetro.
     * 
     * La información de los clientes queda dentro de la llave 'clientes'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información de los clientes
     */
    private void salvarAvion( Aerolinea aerolinea, JSONObject jobject )
    {
    	JSONArray jAviones = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject jAvion = new JSONObject();
            jAvion.put(NOMBRE_AVION, avion.getNombre());
            jAvion.put(CAPACIDAD_AVION, avion.getCapacidad());
            // Agregar otros atributos del avión si los hay

            // Agregar el objeto JSON del avión al arreglo de aviones
            jAviones.put(jAvion);
        }

        // Agregar el arreglo de aviones al objeto JSON principal
    }
	}

