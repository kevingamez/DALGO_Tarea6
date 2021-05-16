package grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * ACLARACIÓN PRELIMINAR: Para esta clase, basamos nuestra implementación en el código GeekForGeeks. 
 * Especialmente en el siguiente enlace: https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
 * Damos completo crédito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */

/**
 * Clase que resuelve el inciso B de la tarea, y ejecuta BFS para encontrar los subconjuntos de vértices.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 *
 */
public class BFS {

	/**
	 * Método constructor vacío de la clase para poder realizar el llamado en el main.
	 */
	public BFS()
	{

	}

	/**
	 * Método que efectua el recorrido implementando BFS y devuelve un arreglo con los colores de cada uno de los vértices.
	 * @param grafo Grafo en representación de matriz de adyacencia.
	 * @return Arreglo con los colores del componente al que pertenece el vértice (que es representado por el índice del arreglo).
	 */
	public int[] componentesConectados(int[][] grafo)
	{
		//Longitud del arreglo.
		int V=grafo[0].length;

		//Se inicializan los colores de los componentes en cada vértice.
		int[] components= new int[V];
		for(int i=0; i<V; ++i)
		{
			components[i]=i;
		}

		//Vector que comprueba si un vértice ha sido visitado.
		boolean[] visited = new boolean[V];
		Arrays.fill(visited, false);
		List<Integer> q = new ArrayList<>(); //Cola de prioridad para visitar vértices adyacentes.
		int vis; //Elemento que será visitado.
		
		boolean end=false;
		while(!end)
		{
			//Busca el índice de un vértice que no haya sido visitado.
			int vertex;
			int index = binarySearch(visited);
			if(index<0)
			{
				//Finaliza el ciclo en caso de que no existan más vértices por visitar.
				end=true;
			}
			else
			{
				//Se visita el vértice encontrado, y todos sus adyacentes.
				vertex = components[index];
				q.add(vertex);
				visited[vertex] = true;
				while (!q.isEmpty())
				{
					vis = q.get(0);
					q.remove(q.get(0));

					//Se recorre cada uno de los vértices adyacentes.
					for(int i = 0; i < V; i++)
					{
						if (grafo[vis][i] == 1 && (!visited[i]))
						{
							//Se agrega el vértice adyacente a la cola de prioridad.
							q.add(i);

							//Se ubica el vértice visitado.
							visited[i] = true;
							
							//Se agrega el color del componente al que pertenece.
							components[i] = components[vis];
						}
					}
				}   
			}
		}
		return components;
	}
	
	/**
	 * Método que efectua una búsqueda binaria sobre un arreglo de booleanos para buscar el índice del primer elemento False.
	 * Este método auxiliar fue adquirido de StackOverflow: https://stackoverflow.com/questions/41959562/binary-search-in-an-array-with-boolean-values/41973650
	 * @param array Arreglo de booleanos que será ingresado por parámetor.
	 * @return Índice del primer elemento falso. Número negativo en caso contrario.
	 */
	public int binarySearch(boolean[] array) 
	{
	    int low = 0, mid;
	    int high = array.length - 1;
	    boolean booleanValue;

	    while (low <= high) {
	        mid = (low + high) >>> 1;
	        booleanValue = array[mid];
	        if (booleanValue) low = mid + 1;
	        else if (low == mid) return mid;
	        else high = mid;
	    }
	    return -low;
	}

	/**
	 * Clase main que ejecuta el programa.
	 * @param args[0]. Nombre del archivo .txt que se encuentra en la carpeta ./data del proyecto. 
	 * 				   Se debe incluir el .txt después del nombre del archivo para que el programa ejecute correctamente.
	 * @throws Exception Si ocurre un error al ejecutar el programa. 
	 */
	public static void main(String[] args) throws Exception {

		BFS bfs = new BFS();

		//Carga el grafo del archivo .txt almacenado en la carpeta data.
		String nombreArchivo=args[0];
		int [][] grafo = generarGrafo(nombreArchivo);

		//Ejecución del algoritmo
		long startTime = System.currentTimeMillis();
		int [] componentes = bfs.componentesConectados(grafo);
		long endTime = System.currentTimeMillis();

		//Salida del programa
		System.out.println("Componentes:\n");
		
		HashMap<Integer, ArrayList<Integer>> mapa = new HashMap<Integer, ArrayList<Integer>>();
		for(int i=0; i<componentes.length; ++i)
		{
			if(mapa.containsKey(componentes[i]))
			{
				ArrayList<Integer> lista = mapa.get(componentes[i]);
				lista.add(i);
				mapa.put(componentes[i], lista);
			}
			else
			{
				ArrayList<Integer> lista = new ArrayList<Integer>();
				lista.add(i);
				mapa.put(componentes[i], lista);
			}
		}	
		
		int contador = 0;
		for (HashMap.Entry<Integer, ArrayList<Integer>> entry : mapa.entrySet()) {
		    System.out.println("Componente "+contador+ ":"+entry.getValue().toString());
		    ++contador;
		}

		System.out.println("\nEl tiempo que tardó en ejecutar BFS fue de: "+(endTime-startTime)+ " ms.");
	}

	/**
	 * Método que carga el grafo en representación matricial.
	 * @param pNombreArchivo Nombre del archivo desde donde se cargará el grafo.
	 * @throws Exception si ocurre un error al cargar el archivo.
	 * @return Grafo generado.
	 */
	public static int[][] generarGrafo(String pNombreArchivo) throws Exception
	{
		File file = new File("./data/"+pNombreArchivo);
		BufferedReader br = new BufferedReader(new FileReader(file));

		int [][] grafo = null;
		int i=0;
		String linea;
		while((linea = br.readLine())!=null)
		{
			String [] fila = linea.split("\t");
			if(grafo==null)
			{
				grafo=new int[fila.length][fila.length];
			}
			for(int j=0; j<fila.length; ++j)
			{
				grafo[i][j]=Integer.parseInt(fila[j].trim());
			}
			++i;
		}	
		return grafo;
	}
}
