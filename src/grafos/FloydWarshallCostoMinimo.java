package grafos;

/*
 * ACLARACI�N PRELIMINAR: Para esta clase, basamos nuestra implementaci�n en el c�digo Programiz. 
 * Especialmente en el siguiente enlace: https://www.programiz.com/dsa/floyd-warshall-algorithm
 * Damos completo cr�dito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */

/**
 * Clase que implementa el algoritmo de Floyd Warshall para la soluci�n del problema de costo m�nimo.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 */
public class FloydWarshallCostoMinimo implements AlgoritmoCostoMinimo{

	/**
	 * Implementaci�n del algoritmo de Floyd Warshall.
	 * @param grafo Grafo ingresado por par�metro.
	 * @return Matriz que contiene todos los costos m�nimos de cada v�rtice de origen a cada v�rtice de destino.
	 */
	@Override
	public int[][] costoMinimo(int[][] grafo) 
	{
		int V = grafo[0].length;
		int m[][] = convertirGrafo(grafo, V);

		//Recorrido a trav�s de las matrices.
		for (int k = 0; k < V; k++) 
		{
			for (int i = 0; i < V; i++) 
			{
				for (int j = 0; j < V; j++) 
				{
					//Se verifica si no existe un camino m�s corto entre los dos v�rtices.
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
	 * M�todo que modifica los -1 del grafo por infinitos y retorna el grafo resultante.
	 * @param grafo Grafo que ser� modificado.
	 * @param V N�mero de v�rtices del grafo.
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


