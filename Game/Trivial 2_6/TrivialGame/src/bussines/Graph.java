package bussines;

import java.util.ArrayList;
import java.util.List;


public class Graph<T> {
	
	private double [][] weights;//matriz de pesos
	private boolean [][] edges;//matriz de aristas
	private T[] nodes;//matriz de nodos
	private int size;//tamaño del grafo
	
	/**
	 * Constructor de la clase grafo con un parametro
	 * @param size numero maximo de nodos que puede tener el grafo
	 */

	@SuppressWarnings("unchecked")
	public Graph(int size){
		weights= new double [size][size];
		edges= new boolean [size][size];
		nodes = (T[]) new Object [size];
		this.size=0;
	}
	
	/**
	 * Metodo que añade un nodo al grafo
	 * @param  node nodo que se quiere añadir
	 * @return int, Devuelve 0 cuando puede añadir el nodo y -1 si no lo ha añadido
	 */

	public int addNode(T node){
		if (existNode(node)|| node == null)
			return -1;
		if(size<nodes.length){
			nodes[size]=node;
			size++;
			return 0;
		}
		return -1;
	}
	/**
	 * Metodo para buscar la posicion de un nodo en el grafo 
	 * @param node nodo del cual queremos saber la posicion
	 * @return int,  Si el nodo no existe va a devolver -1, en caso contrario devuelve la posicion del nodo
	 */
	public int getNode(T node){
		for(int i=0; i<size;i++)
			if (nodes[i].equals(node))
				return i;
		
			return -1;
	}
	
	/**
	 * Metodo que devuelve el valor de la arista ente dos nodos.
	 * @param source nodo origen de la arista.
	 * @param target nodo destino de la arista.
	 * @return int,  devuelve el peso de la arista si esta no existe devuelve -1
	 */
	public double getEdge(T o, T d){
		if (existNode(o) && existNode(d) && existEdge(o,d))
			return weights[getNode(o)][getNode(d)];
		return -1;
	}
	/**
	 * Metodo para saber si existe un nodo.
	 * @param  node nodo que se quiere saber si existe.
	 * @return boolean,  Devuelve true si existe un nodo y false en caso contrario
	 */
	public boolean existNode(T node) {
			return getNode(node)!= -1;
	}
	
	/**
	 * Método que devuelve el tamaño del grafo
	 * @return size, tamaño del grafo.
	 */
	public int getSize(){
		return size;
	}
	
	
	
	/**
	 * Método que borra un nodo.
	 * @param  node que se quiere eliminar
	 * @return int, devuelve 0 si la ha conseguido borrar y -1 en caso contrario
	 */
	public int removeNode(T node){
		int i= getNode(node);
		if(i!=-1){//Comprueba que exista el nodo en el grafo.
			size--;//Reducimos el numero de nodos, ya que se borra uno
			if (i != size+1) {
				nodes[i] = nodes[size];
				nodes[size]=null;
			
				for(int j=0; j<size;j++){
					edges[j][i]=edges[j][size];
					edges[i][j]=edges[size][j];
					edges[j][size]=false;
					edges[size][j]=false;
					weights[j][i]=weights[j][size];
					weights[i][j]=weights[size][j];
					weights[j][size]=0;
					weights[size][j]=0;						
				}		
				edges[i][i]=edges[size][size];
				weights[i][i]=weights[size][size];
				
				edges[size][size]=false;
				weights[size][size]=0;
			}return 0;
		}else
			return -1;
	}
	

	/**
	 * Metodo que borra una arista.
	 * @param  source nodo origen de la arista
	 * @param  target nodo destino de la arista.
	 * @return int,  devuelve 0 si la ha conseguido borrar y -1 en caso contrario
	 */
	public int removeEdge(T o, T d){
		if (existEdge(o, d)&& existNode(o)&&existNode(d)){
			edges[getNode(o)][getNode(d)]=false;
			
			weights[getNode(o)][getNode(d)]=0;
			return 0;
			}
		return -1;
	}
	
	/**
	 * Mira si existe una arista entre dos nodos que se pasan como parámetro
	 * @param source nodo origen
	 * @param target nodo destino
	 * @return verdadero o falso si existe o no, si alguno de los nodos no existe también falso
	 */
	private boolean existEdge(T o, T d){
	
				if(existNode(o)&& existNode(d) && edges[getNode(o)][getNode(d)])
					return true;
		return false;
	
}
	/**
	 * Método auxiliar que genera la matriz de costes en el algoritmo de Djikstra
	 * @param node, nodo origen por donde empieza el algoritmo
	 * @return costes, matriz de costes por los que puede ir el nodo origen
	 */
	private double[] intilializeDjikstraD(T node){
		double[] costes= new double[size];
		int nodePosition= getNode(node);
		for (int i=0;i<size;i++){
			 if(nodePosition==i)
				costes[i]=0;
			 else if(edges[nodePosition][i]){
				costes[i]=new Double(weights[nodePosition][i]);
			 }
			else
				costes[i]=Double.POSITIVE_INFINITY;			
		}	
		return costes;
		
		
	}
/**
 * Método auxiliar para inicializar la matriz de nodos  por los que hay union directa con el nodo origen en Djikstra
 * @param node, nodo de origen del algoritmo
 * @return p, matriz de nodos por los que se puede acceder desde el nodo Origen
 */
private T[] intilializeDjikstraP(T node){
	@SuppressWarnings("unchecked")
	T[] p = (T[]) new Object[size];
	int nodePosition= getNode(node);
	for (int i=0; i<size;i++){
		if(edges[nodePosition][i]|| nodePosition==i)
			p[i]=node;
	}
	return p;
	}

/**
 * Algoritmo de Dijkstra para encontrar el camino de coste minimo desde nodo
 * hasta el resto de nodos
 * @param nodo  El nodo por el que se quiere empezar el recorrido en
 * profundidad
 *  @return double[], lista con los pesos minimos entre el nodo pasado como parametro y los demas nodos.
 */
public double[] djikstra(T source){
	if(!existNode(source))
		return null;
	
	
	boolean[]s= new boolean[size];
	
	s[getNode(source)]=true;
	double [] D =intilializeDjikstraD(source);
	T[] P= intilializeDjikstraP(source);
	
	djisktra(s,D,P);
	
	
	
	
	
	return D;
	
	
}
/**
 * método recursivo de djisktra que va escogiendo el arco de coste minimo posible en cada accion recursiva
 * @param s array de nodos visitados
 * @param D array de costes
 * @param P array de nodos del grafo
 */
public void djisktra(boolean s[],double[] D, T[] P){
	int minEdgeNode=minEdge(D, s);
	if( minEdgeNode!=-1){
		s[minEdgeNode]=true;
	for(int i=0;i<size;i++){
		if(edges[minEdgeNode][i]  &&( D[minEdgeNode]+ weights[minEdgeNode][i])<D[i]){
			D[i]= D[minEdgeNode]+ weights[minEdgeNode][i];
			P[i]=nodes[minEdgeNode];
	}
		}
	
	djisktra(s, D, P);
	}
		
		
	

}

/**
 * Metodo para añadir una arista
 * @param  source nodo origen del vertice
 * @param  target nodo destino del vertice
 * @param  w peso de la arista.
 * @return int, Devuelve 0 si consigue insertar la arista y -1 en caso contrario
 */
public int addEdge(T source,T target , double weight){
	if(existNode(source)&&existNode(target)&& !existEdge(source, target)){
		edges[getNode(source)][getNode(target)]=true;
	weights[getNode(source)][getNode(target)]=weight;
	return 0;
	}
	return -1;
}

/**
 * Devuelve el indice dentro del vector de distancias que sea mas pequeño y que no 
 * este visitado arista y si no hay ninguna arista devuelve -1
 * @param D, vector de costes
 * @param s, vector de nodos vistitados o no
 * @return int, minimo coste de un nodo al resto.
 */
private int minEdge(double[] D, boolean[] s){
	int minEdge=-1;
	for(int i=0;i<size;i++){
		if(!s[i] && (minEdge==-1 || D[i]<D[minEdge])){
			minEdge=i;
					
	}
		
	}
	
	
	return minEdge;
}


/**
 * Algoritmo de floyd-warshall que devuelve al amatriz de caminos
 * Inicializa la matriz A(coste) con el peso de las aristas, y 0 en la diagonal
 * @return double[][] a, matriz que te indica entre que dos nodos hay camino pasando por un nodo intermedio 
 * señanalndolos con el indice del nodo intermedio y el resto de posiciones con -1.
 */
public double[][] floyd(){
	double[][] a =initializeFloydA();
	T[][]p= initializeFloydP();
	
	
	for(int pivot=0;pivot<size;pivot++){
		//floid para rutas especiales if(pivot in L)...
		for(int source=0;source<size;source++){
			for(int target=0;target<size;target++){
				if(a[source][pivot]+ a[pivot][target]< a [source][target])
					a[source][target]=a[source][pivot]+ a[pivot][target];
					p[source][target]=nodes[pivot];
			}
			
		}
	}
	return a;
}
/**
 * Método auxiliar que inicializa la matriz de costes para el algoritmo de Floyd.
 * @return a, matriz de costes inicializada
 */
private double[][] initializeFloydA() {
	double[][] a= new double[size][size];
	for(int i=0;i<size;i++){
		for(int j=0;j<size;j++){
			if(weights[i][j]!=0)
				a[i][j]=weights[i][j];
			else if(i==j)
				a[i][j]=0;
			else
				a[i][j]=Double.POSITIVE_INFINITY;
		}
	}
	return a;
}
/**
 * Método auxiliar que inicializa la matriz de arcos para el algoritmo de Floyd.
 * @return p ,matriz de arcos inicializada
 */
private T[][] initializeFloydP() {
	
	@SuppressWarnings("unchecked")
	T[][]p= (T[][])new Object[size][size];
	
	for(int i=0;i<size;i++){
		for(int j =0;j<size;j++){
			T source =nodes[i];
			T target =nodes[j];
			if(existEdge(source,target))
				p[i][j]=nodes[getNode(source)];
				
				
		}
		}
	return p;
}


/**
 * Metodo que intenta recorrer, a partir del nodo que le pasas, el grafo completo.
 * @param  nodo nodo de origen para intentar recorrer todos los demas
 * @return int, 0 si recorre todos los nodos del grafo y -1 si no puede recorrerlos todos.
 */
public int recorridoProfundidad(T nodo){
	if(!existNode(nodo))
		return -1;
	
	boolean[] visitados = new boolean[size];

	recursiveDFS(nodo,visitados);
		
		
	return allNodesVisited(visitados);
}
/**
 * Metodo recursivo que va recorriendo los nodos hasta que los recorre todos.
 * Al modificar el vector se modifica fuera
 * @param source nodo desde el que se va a intentar recorrer todos los nodos.
 * @param visitados, array que indica que nodos han sido visitados y cuales no,
 */
private void recursiveDFS(T source, boolean[]visitados){
	System.out.println(source);
	int nodoOrigen= getNode(source);
	visitados[nodoOrigen]=true;
	for(int i=0;i<size;i++){
		if(visitados[i]==false && existEdge(source,nodes[i]))
			recursiveDFS(nodes[i],visitados);
		
		
	}
}


/**
 * Método auxiliar que comprueba que todos los nodos hayan sido visitados
 * @param visited array booleano que indica cuales nodos han sido visitados
 * @return -1 si queda alguno que no ha sido visitado, 0 si están todos visitados.
 */
public int allNodesVisited(boolean[] visited){
	for(int i=0;i<size;i++){
		if(visited[i]==false){
			return -1;
		}
		}
		return 0;
}

public double excentricidad(T nodo){
	if (!existNode(nodo))
		return -1;
	double[][] A = floyd();
	int posicion=getNode(nodo);
	double max=0;
	for (int i=0;i<size;i++){
		if(A[i][posicion]>max)
			max= A[i][posicion];
	}
	return max;
}

public double centroDelGrafo(){
	List <Double> excentricidades= new ArrayList<Double>();
	for(int i =0;i<size;i++){
		excentricidades.add(excentricidad(nodes[i]));
	}
	double min=Double.POSITIVE_INFINITY;
	for (int i=0;i<excentricidades.size();i++){
		if(excentricidades.get(i)<min)
			min=excentricidades.get(i);
		}
	return min;
}

public boolean nodoFuertementeConexo(T nodo){
	if(!existNode(nodo))
		return false;
	if(recorridoProfundidad(nodo)==0)
			return true;
	return false;
	
}
public boolean grafoFuertementeConexo(){
	for(int i=0;i<size;i++)
		if(nodoFuertementeConexo(nodes[i]))
			return true;
	
	return false;
}
public boolean isNodoAislado(T nodo){
	int Gsalida=0;
	int GEntrada=0;
	for(int i=0;i<size;i++){
		if(existEdge(nodo, nodes[i]) )
			Gsalida++;
		if(existEdge(nodes[i], nodo))
			GEntrada++;
	}
	if (Gsalida==0 && GEntrada==0)
		return true;
	return false;
}
public boolean isNodoSumidero(T nodo){
	int Gsalida=0;
	int GEntrada=0;
	for(int i=0;i<size;i++){
		if(existEdge(nodo, nodes[i]) )
			Gsalida++;
		if(existEdge(nodes[i], nodo))
			GEntrada++;
	}
	if (Gsalida==0 && GEntrada>0)
		return true;
	return false;
	
}

public boolean isNodoFuente(T nodo){
	int Gsalida=0;
	int GEntrada=0;
	for(int i=0;i<size;i++){
		if(existEdge(nodo, nodes[i]) )
			Gsalida++;
		if(existEdge(nodes[i], nodo))
			GEntrada++;
	}
	if (Gsalida>0 && GEntrada==0)
		return true;
	return false;
	
}
}

