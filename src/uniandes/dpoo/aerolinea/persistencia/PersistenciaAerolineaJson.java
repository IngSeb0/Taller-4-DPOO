package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;
import uniandes.dpoo.aerolinea.exceptions.ClienteRepetidoException;
import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteTiqueteException;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea{
	private static final String NOMBRE_AEROPUERTO= "nombre";
    private static final String CODIGO_AEROPUERTO = "codigo";
    private static final String NOMBRE_CIUDAD = "nombreCiudad";
    private static final String LATITUD = "latitud";
    private static final String LONGITUD = "longitud";
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
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ){
    {
        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );

     
        
        cargarVuelo( aerolinea, raiz.getJSONArray( "vuelo" ) );
        cargarAvion( aerolinea, raiz.getJSONArray( "avion" ) );
        cargarRuta( aerolinea, raiz.getJSONArray( "ruta" ) );
        
    }

    /**
     * Salva en un archivo toda la información sobre los clientes y los tiquetes vendidos por la aerolínea
     * @param archivo La ruta al archivo donde debe quedar almacenada la información
     * @param aerolinea La aerolínea que tiene la información que se quiere almacenar
     * @throws IOException Se lanza esta excepción si hay problemas escribiendo el archivo
     */
    @Override
    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) 
    {
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
        JSONArray jVuelos = new JSONArray( );
        for( Vuelo vuelo : aerolinea.getVuelos( ) )
        {
            // Acá también se utilizaron dos estrategias para salvar los clientes.
            // Para los clientes naturales, esta clase extrae la información de los objetos y la organiza para que luego sea salvada.
            // Para los clientes corporativos, la clase ClienteCorporativo hace todo lo que está en sus manos para persistir un cliente
            
                JSONObject jVuelo = new JSONObject( );
                
                jVuelos.put( jVuelo );
          
            }
        

        jobject.put( "vuelos", jVuelos );
    }

    private void cargarRuta( Aerolinea aerolinea, JSONArray jRuta) 
    {
        int numRuta= jRuta.length( );
        for( int i = 0; i < numRuta ; i++ )
        {
            JSONObject Ruta = jRuta.getJSONObject( i );
            
            Aeropuerto origen = new Aeropuerto(Ruta.getString(ORIGEN),Ruta.getString(CODIGO_AEROPUERTO), Ruta.getString(NOMBRE_CIUDAD), Ruta.getDouble(LATITUD), Ruta.getDouble(LONGITUD));
            Aeropuerto destino = new Aeropuerto(Ruta.getString(DESTINO),Ruta.getString(CODIGO_AEROPUERTO), Ruta.getString(NOMBRE_CIUDAD), Ruta.getDouble(LATITUD), Ruta.getDouble(LONGITUD));
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
    private void salvarRutas( Aerolinea aerolinea, JSONObject jobject )
    {
        JSONArray jRutas= new JSONArray( );
        for( Ruta ruta : aerolinea.getRutas( ) )
        {
            // Acá también se utilizaron dos estrategias para salvar los clientes.
            // Para los clientes naturales, esta clase extrae la información de los objetos y la organiza para que luego sea salvada.
            // Para los clientes corporativos, la clase ClienteCorporativo hace todo lo que está en sus manos para persistir un cliente
            
                JSONObject jVuelo = new JSONObject( );
                
                jRutas.put( jVuelo );
          
            }
        

        jobject.put( "Rutas", jRutas );
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
        JSONArray jAviones= new JSONArray( );
        for( Avion avion : aerolinea.getAviones() )
        {
            // Acá también se utilizaron dos estrategias para salvar los clientes.
            // Para los clientes naturales, esta clase extrae la información de los objetos y la organiza para que luego sea salvada.
            // Para los clientes corporativos, la clase ClienteCorporativo hace todo lo que está en sus manos para persistir un cliente
            
                JSONObject jAvion = new JSONObject( );
                
                jAviones.put( jAvion );
          
            }
        

        jobject.put( "Aviones", jAviones );
    }
	}

