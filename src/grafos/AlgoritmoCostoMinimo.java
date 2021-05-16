package grafos;

/**
 * Interfaz en com�n para los algoritmos que resuelven el problema de costo m�nimo. 
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public interface AlgoritmoCostoMinimo {

	/**
	 * M�todo que calcula la matriz de costos m�nimos dado un grafo ingresado en representaci�n matricial.
	 * @param grafo Grafo representado matricialmente.
	 * @return
	 */
	public int [][] costoMinimo(int[][] grafo);
}
