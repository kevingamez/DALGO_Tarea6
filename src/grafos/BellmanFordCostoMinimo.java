package grafos;

/*
 * ACLARACI�N PRELIMINAR: Para esta clase, basamos nuestra implementaci�n en el c�digo GeekForGeeks. 
 * Especialmente en el siguiente enlace: https://www.geeksforgeeks.org/bellman-ford-algorithm-simple-implementation/
 * Damos completo cr�dito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */


/**
 * Clase que implementa el algoritmo de Bellman Ford para la soluci�n del problema de costo m�nimo.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public class BellmanFordCostoMinimo implements AlgoritmoCostoMinimo{

	/**
	 * Implementaci�n del algoritmo de Bellman Ford.
	 * @param grafo Grafo ingresado por par�metro.
	 * @return Matriz que contiene todos los costos m�nimos de cada v�rtice de origen a cada v�rtice de destino.
	 */
	@Override
	public int[][] costoMinimo(int[][] graph) {
		int[][] grafo = convertirGrafo(graph);
		int V = graph[0].length; // N�mero de v�rtices en el grafo.
		int E = grafo.length; // N�mero de arcos en el grafo.
		int m[][]= new int[V][V];
		for (int i = 0; i < V; i++) {
			//Llama al m�todo bellmanFord() para traer los costos asociados a cada v�rtice respecto al destino.
			int[] fila = bellmanFord(grafo, V, E, i);
			for (int j = 0; j < fila.length; j++) {
				m[i][j] = fila[j];
			}
		}
		return m;
	}

	/**
	 * Implementaci�n del algoritmo de Bellman Ford.
	 * @param graph Grafo ingresado por par�metro.
	 * @param V N�mero de v�rtices del grafo.
	 * @param E N�mero de arcos del grafo.
	 * @param src V�rtice de inicio donde se ejecutar�.
	 * @return Arreglo de costos m�nimos del v�rtice de inicio al v�rtice de destino.
	 */
	public int[] bellmanFord(int graph[][], int V, int E, int src)
	{
		//Inicializaci�n las distancias en infinito.
		int []dis = new int[V];
		for (int i = 0; i < V; i++)
			dis[i] = Integer.MAX_VALUE;

		//Se inicializa la distancia del v�rtice de origen en cero.
		dis[src] = 0;

		//Se recorren los V-1 v�rtices.
		for (int i = 0; i < V - 1; i++)
		{
			for (int j = 0; j < E; j++)
			{
				//Relajaci�n del v�rtice.
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
	 * M�todo que transforma el grafo de su representaci�n en matriz de adyacencia
	 * a una matriz de conjuntos de tuplas {u, v, w}, donde u es el v�rtice de origen
	 * v es el v�rtice de destino y w es el costo/distancia entre ellos.
	 * N�tese que la matriz  de salida tendr� tres columnas y cada una guardar� un valor de la tupla.
	 * @param grafo Grafo en representaci�n de matriz de adyacencia que ser� transformado.
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
