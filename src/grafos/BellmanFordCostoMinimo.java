package grafos;

/*
 * ACLARACIÓN PRELIMINAR: Para esta clase, basamos nuestra implementación en el código GeekForGeeks. 
 * Especialmente en el siguiente enlace: https://www.geeksforgeeks.org/bellman-ford-algorithm-simple-implementation/
 * Damos completo crédito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */


/**
 * Clase que implementa el algoritmo de Bellman Ford para la solución del problema de costo mínimo.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public class BellmanFordCostoMinimo implements AlgoritmoCostoMinimo{

	/**
	 * Implementación del algoritmo de Bellman Ford.
	 * @param grafo Grafo ingresado por parámetro.
	 * @return Matriz que contiene todos los costos mínimos de cada vértice de origen a cada vértice de destino.
	 */
	@Override
	public int[][] costoMinimo(int[][] graph) {
		int[][] grafo = convertirGrafo(graph);
		int V = graph[0].length; // Número de vértices en el grafo.
		int E = grafo.length; // Número de arcos en el grafo.
		int m[][]= new int[V][V];
		for (int i = 0; i < V; i++) {
			//Llama al método bellmanFord() para traer los costos asociados a cada vértice respecto al destino.
			int[] fila = bellmanFord(grafo, V, E, i);
			for (int j = 0; j < fila.length; j++) {
				m[i][j] = fila[j];
			}
		}
		return m;
	}

	/**
	 * Implementación del algoritmo de Bellman Ford.
	 * @param graph Grafo ingresado por parámetro.
	 * @param V Número de vértices del grafo.
	 * @param E Número de arcos del grafo.
	 * @param src Vértice de inicio donde se ejecutará.
	 * @return Arreglo de costos mínimos del vértice de inicio al vértice de destino.
	 */
	public int[] bellmanFord(int graph[][], int V, int E, int src)
	{
		//Inicialización las distancias en infinito.
		int []dis = new int[V];
		for (int i = 0; i < V; i++)
			dis[i] = Integer.MAX_VALUE;

		//Se inicializa la distancia del vértice de origen en cero.
		dis[src] = 0;

		//Se recorren los V-1 vértices.
		for (int i = 0; i < V - 1; i++)
		{
			for (int j = 0; j < E; j++)
			{
				//Relajación del vértice.
				if (dis[graph[j][0]] != Integer.MAX_VALUE && dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]])
					dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2];
			}
		}
		
		//Se verifica que no existan ciclos negativos en el grafo.
		for (int i = 0; i < E; i++)
	    {
	        int x = graph[i][0];
	        int y = graph[i][1];
	        int weight = graph[i][2];
	        if (dis[x] != Integer.MAX_VALUE && dis[x] + weight < dis[y])
	            System.out.println("Graph contains negative weight cycle");
	    }		
		return dis;
	}

	/**
	 * Método que transforma el grafo de su representación en matriz de adyacencia
	 * a una matriz de conjuntos de tuplas {u, v, w}, donde u es el vértice de origen
	 * v es el vértice de destino y w es el costo/distancia entre ellos.
	 * Nótese que la matriz  de salida tendrá tres columnas y cada una guardará un valor de la tupla.
	 * @param grafo Grafo en representación de matriz de adyacencia que será transformado.
	 * @return Matriz de tuplas transformada.
	 */
	public int[][] convertirGrafo(int[][] grafo)
	{
		int V = grafo[0].length;
		int[][] ret = new int[V*V][3];
		int k = 0;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if(grafo[i][j] != -1) {
					ret[k][0] = i;
					ret[k][1] = j;
					ret[k][2] = grafo[i][j];
					k++;
				}
			}
		}
		return ret;
	}
}
