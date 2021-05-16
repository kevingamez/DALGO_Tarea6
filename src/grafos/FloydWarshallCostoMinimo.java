package grafos;

/*
 * ACLARACIÓN PRELIMINAR: Para esta clase, basamos nuestra implementación en el código Programiz. 
 * Especialmente en el siguiente enlace: https://www.programiz.com/dsa/floyd-warshall-algorithm
 * Damos completo crédito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */

/**
 * Clase que implementa el algoritmo de Floyd Warshall para la solución del problema de costo mínimo.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public class FloydWarshallCostoMinimo implements AlgoritmoCostoMinimo{

	/**
	 * Implementación del algoritmo de Floyd Warshall.
	 * @param grafo Grafo ingresado por parámetro.
	 * @return Matriz que contiene todos los costos mínimos de cada vértice de origen a cada vértice de destino.
	 */
	@Override
	public int[][] costoMinimo(int[][] grafo) 
	{
		int V = grafo[0].length;
		int m[][] = convertirGrafo(grafo, V);

		//Recorrido a través de las matrices.
		for (int k = 0; k < V; k++) 
		{
			for (int i = 0; i < V; i++) 
			{
				for (int j = 0; j < V; j++) 
				{
					//Se verifica si no existe un camino más corto entre los dos vértices.
					//Se genera la matriz recursivamente y se traen los valores.
					if ((m[i][k]!=Integer.MAX_VALUE && m[k][j]!=Integer.MAX_VALUE)
							&&m[i][k] + m[k][j] < m[i][j])
					{
						m[i][j] = m[i][k] + m[k][j];
					}
				}
			}
		}
		return m;
	}

	/**
	 * Método que modifica los -1 del grafo por infinitos y retorna el grafo resultante.
	 * @param grafo Grafo que será modificado.
	 * @param V Número de vértices del grafo.
	 * @return Grafo modificado. 
	 */
	public int [][] convertirGrafo(int[][] grafo, int V)
	{
		int m[][] = new int[V][V];
		for (int i = 0; i < V; i++)
		{
			for (int j = 0; j < V; j++)
			{
				if(grafo[i][j]==-1)
				{
					m[i][j] = Integer.MAX_VALUE;
				}
				else 
				{
					m[i][j] = grafo[i][j];
				}
			}		       
		}
		return m;
	}
}


