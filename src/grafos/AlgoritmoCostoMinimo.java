package grafos;

/**
 * Interfaz en común para los algoritmos que resuelven el problema de costo mínimo. 
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public interface AlgoritmoCostoMinimo {

	/**
	 * Método que calcula la matriz de costos mínimos dado un grafo ingresado en representación matricial.
	 * @param grafo Grafo representado matricialmente.
	 * @return
	 */
	public int [][] costoMinimo(int[][] grafo);
}
