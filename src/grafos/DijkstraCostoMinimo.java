package grafos;

/*
 * ACLARACI�N PRELIMINAR: Para esta clase, basamos nuestra implementaci�n en el c�digo GeekForGeeks. 
 * Especialmente en el siguiente enlace: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 * Damos completo cr�dito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */

/**
 * Clase que implementa el algoritmo de Dijkstra para la soluci�n del problema de costo m�nimo.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public class DijkstraCostoMinimo implements AlgoritmoCostoMinimo{

	/**
	 * Implementaci�n del algoritmo de Dijkstra.
	 * @param grafo Grafo ingresado por par�metro.
	 * @return Matriz que contiene todos los costos m�nimos de cada v�rtice de origen a cada v�rtice de destino.
	 */
	@Override
	public int[][] costoMinimo(int[][] grafo) 
	{
		int V = grafo[0].length;
		int m[][]= new int[V][V];
		
		for (int i = 0; i < V; i++) {
			//Llama al m�todo dijkstra() para traer los costos asociados a cada v�rtice respecto al destino.
			int[] fila = dijkstra(grafo, i); 
			for (int j = 0; j < fila.length; j++) {
				m[i][j] = fila[j];
			}
		}
		return m;
	}

	/**
	 * Implementaci�n del algoritmo de Dijkstra.
	 * @param graph Grafo ingresado por par�metro.
	 * @param src V�rtice de inicio donde se ejecutar�.
	 * @return Arreglo de costos m�nimos del v�rtice de inicio al v�rtice de destino.
	 */
	public int[] dijkstra(int graph[][], int src)
	{
		int V =graph[0].length;
		boolean sptSet[] = new boolean[V];
		
		//Inicializaci�n las distancias en infinito y el arreglo de v�rtices visitados.
		int[] dist = new int[V];
		for (int i = 0; i < V; i++)
		{
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		//Se inicializa la distancia del v�rtice de origen en cero.
		dist[src] = 0;

		//Se recorren los V-1 v�rtices.
		for (int count = 0; count < V-1; count++)
		{
			int u = colaPrioridad(dist, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < V; v++)
				//Relajaci�n del v�rtice.
				if (!sptSet[v]  && graph[u][v]!=-1 && 
				dist[u] != Integer.MAX_VALUE &&
				dist[u]+graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}
		return dist;
	}

	/**
	 * M�todo que implementa una cola de prioridad para obtener el costo m�nimo
	 * @param dist Arreglo de distancias que guarda la cola.
	 * @param sptSet Arreglo que verifica si un v�rtice ya fue visitado.
	 * @return Costo m�nimo asociado.
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
