package uniandes.dpoo.aerolinea.modelo.cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import java.util.ArrayList;
import java.util.List;
public abstract class Cliente {
	private List<Tiquete> tiquetes = new ArrayList<Tiquete>();
	public abstract String getTipoCliente();
	public abstract String getIdentificador	();
	public void agregarTiquete(Tiquete tiquete) {
		tiquetes.add(tiquete);
		
		
	}
	public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetes) {
            total += tiquete.getTarifa();
        }
        return total;
	}
	 public void usarTiquetes(Vuelo vuelo) {
	        for (Tiquete tiquete : tiquetes) {
	            if (tiquete.getVuelo().equals(vuelo) && !tiquete.esUsado()) {
	                tiquete.marcarComoUsado();}
	        }
	 }
	public List<Tiquete> getTiquetes() {
		return tiquetes;
	}
	public void setTiquetes(List<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}
}
