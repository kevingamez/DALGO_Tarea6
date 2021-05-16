package grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * ACLARACIÓN PRELIMINAR: Para esta clase, basamos nuestra implementación en el código GeekForGeeks. 
 * Especialmente en el siguiente enlace: https://www.geeksforgeeks.org/implementation-of-dfs-using-adjacency-matrix/
 * Damos completo crédito a los autores y referenciamos su uso libre dentro del marco legal correspondiente.
 * No buscamos generar plagio, ni tampoco tener inconvenietes asociados al mismo.
 */

/**
 * Clase que resuelve el inciso B de la tarea, y ejecuta BFS para encontrar los subconjuntos de vértices.
 * @author Kevin Steven Gamez Abril y Sergio Julian Zona Moreno
 *
 */
public class DFS 
{
	//Grafo
	private int[][] graph;
	
	//ArrayList de ordenes tipograficos
	ArrayList<Integer> order;

	/**
	 * Método constructor vacio de la clase para poder realizar el llamado en el main.
	 */
	public DFS()
	{

	}

	/**
	 *  Método que efectua el recorrido implementando DFS y devuelve un booleano indicando si existe un ciclo. 
	 * @param graph Grafo en representación de matriz de adyacencia.
	 * @return bolleano que indica si existe un ciclo.
	 */
	public boolean DFS(int[][] graph)
	{

		order= new ArrayList<Integer>();
		this.graph = graph;

		int i = 0;
		while(i< graph.length)
		{
			boolean[] visitado = new boolean[graph.length];
			boolean[] pila = new boolean[graph.length];
			if(hayCiclo(i, visitado, pila)==true)
			{
				return true;
			}

			i ++;
		}

		return false;
	}

	/**
	 * Método que efectua un recorrido buscando si existe o no un ciclo.
	 * @param i Posicion donde se inicia la busqueda.
	 * @param visitado booleano que indica cuales ya han sido visitados.
	 * @param pila Stack donde se almacenan los booleanos.
	 * @return
	 */
	private boolean hayCiclo(int i, boolean[] visitado, boolean[] pila) 
	{
		boolean hay=false;
		if(pila[i]) {hay= true;}
		if (visitado[i]) {hay= true;}

		pila[i] = true;
		visitado[i] = true;

		int j = 0;

		while(j < graph[i].length && hay==false)
		{
			if(graph[i][j]>0 && !order.contains(i) && !order.contains(j)) {order.add(i);order.add(j);}
			else if(graph[i][j]>0 && !order.contains(i)){order.add(0,i);}
			else if(graph[i][j]>0 && !order.contains(j)){order.add(j);}
			if(graph[i][j]>0 && hayCiclo(j, visitado, pila))
			{
				hay= true;
			}

			pila[i]= false;
			j++;
		}

		return hay;
	}

	/**
	 * Clase main que ejecuta el programa.
	 * @param args[0]. Nombre del archivo .txt que se encuentra en la carpeta ./data del proyecto. 
	 * 				   Se debe incluir el .txt después del nombre del archivo para que el programa ejecute correctamente.
	 * @throws Exception Si ocurre un error al ejecutar el programa. 
	 */
	public static void main(String[] args) throws Exception {

		//Carga el graph del archivo .txt almacenado en la carpeta data.
		String nombreArchivo=args[0];
		int [][] graph = generargraph(nombreArchivo);


		//Ejecución del dfs
		long startTime = System.currentTimeMillis();
		DFS dfs= new DFS();
		boolean ciclo=dfs.DFS(graph);
		System.out.println("Existe Algun ciclo: "+ciclo);			
		if(ciclo==false)
		{
			String orden="";
			for (int n: dfs.order)
			{
				orden+=n+" ";
			}
			System.out.println("Orden topológico:"+orden);
		}
		long endTime = System.currentTimeMillis();


		System.out.println("\nEl tiempo que tardó en ejecutar BFS fue de: "+(endTime-startTime)+ " ms.");
	}

	/**
	 * Método que carga el graph en representación matricial.
	 * @param pNombreArchivo Nombre del archivo desde donde se cargará el graph.
	 * @throws Exception si ocurre un error al cargar el archivo.
	 * @return graph generado.
	 */
	public static int[][] generargraph(String pNombreArchivo) throws Exception
	{
		File file = new File("./data/"+pNombreArchivo);
		BufferedReader br = new BufferedReader(new FileReader(file));

		int [][] graph = null;
		int i=0;
		String linea;
		while((linea = br.readLine())!=null)
		{
			String [] fila = linea.split("\t");
			if(graph==null)
			{
				graph=new int[fila.length][fila.length];
			}
			for(int j=0; j<fila.length; ++j)
			{
				graph[i][j]=Integer.parseInt(fila[j].trim());
			}
			++i;
		}	
		return graph;
	}

}
