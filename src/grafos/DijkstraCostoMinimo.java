package grafos;

/*
 * ACLARACIÓN PRELIMINAR: Para esta clase, basamos nuestra implementación en el código GeekForGeeks. 
 * Especialmente en el siguiente enlace: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 * Damos completo crédito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */

/**
 * Clase que implementa el algoritmo de Dijkstra para la solución del problema de costo mínimo.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public class DijkstraCostoMinimo implements AlgoritmoCostoMinimo{

	/**
	 * Implementación del algoritmo de Dijkstra.
	 * @param grafo Grafo ingresado por parámetro.
	 * @return Matriz que contiene todos los costos mínimos de cada vértice de origen a cada vértice de destino.
	 */
	@Override
	public int[][] costoMinimo(int[][] grafo) 
	{
		int V = grafo[0].length;
		int m[][]= new int[V][V];
		
		for (int i = 0; i < V; i++) {
			//Llama al método dijkstra() para traer los costos asociados a cada vértice respecto al destino.
			int[] fila = dijkstra(grafo, i); 
			for (int j = 0; j < fila.length; j++) {
				m[i][j] = fila[j];
			}
		}
		return m;
	}

	/**
	 * Implementación del algoritmo de Dijkstra.
	 * @param graph Grafo ingresado por parámetro.
	 * @param src Vértice de inicio donde se ejecutará.
	 * @return Arreglo de costos mínimos del vértice de inicio al vértice de destino.
	 */
	public int[] dijkstra(int graph[][], int src)
	{
		int V =graph[0].length;
		boolean sptSet[] = new boolean[V];
		
		//Inicialización las distancias en infinito y el arreglo de vértices visitados.
		int[] dist = new int[V];
		for (int i = 0; i < V; i++)
		{
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		//Se inicializa la distancia del vértice de origen en cero.
		dist[src] = 0;

		//Se recorren los V-1 vértices.
		for (int count = 0; count < V-1; count++)
		{
			int u = colaPrioridad(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < V; v++)
				//Relajación del vértice.
				if (!sptSet[v]  && graph[u][v]!=-1 && 
				dist[u] != Integer.MAX_VALUE &&
				dist[u]+graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}
		return dist;
	}

	/**
	 * Método que implementa una cola de prioridad para obtener el costo mínimo
	 * @param dist Arreglo de distancias que guarda la cola.
	 * @param sptSet Arreglo que verifica si un vértice ya fue visitado.
	 * @return Costo mínimo asociado.
	 */
	public int colaPrioridad(int dist[], boolean sptSet[])
	{
		int min = Integer.MAX_VALUE, min_index=-1;
		for (int v = 0; v < dist.length; v++)
		{
			//Actualiza la distancia si encuentra otra menor.
			if (sptSet[v] == false && dist[v] <= min)
			{
				min = dist[v];
				min_index = v;
			}
		}
		return min_index;
	}
}
