package model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import bussines.Graph;
import conf.Conf;

/** Clase "Score" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class Panel {
	
	private List<Square> squares = new ArrayList<>();
	private String image;
	private String fichero;
	



	public Panel(String image, String fichero) {
		
		loadGraph(fichero);
		
	}
	
	public String getFichero() {
		return fichero;
	}


	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Panel(List<Square> squares){
		this.squares = squares;
	}
	
	public List<Square> getSquares() {
		return squares;
	}
	
	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}
	
	/**M�todo que lee un fichero y carga los nodos correspondientes
	 * 
	 * @return
	 */
	private Graph<Square> loadGraph(String fichero)
	{
		//Read information about the graph to be charged
		List<String> nodes = Conf.getNodes(fichero);
		List<String> edges = Conf.getEdges(fichero);
		
		
		Graph<Square> tablero = new Graph<>(nodes.size());
		
		//Add the nodes 
		addNodes(nodes);
		
		//Add edges
		addEdges(edges, tablero);
		
		return tablero;
		
	}

	private void addEdges(List<String> edges, Graph<Square> tablero) {
		for(String edge : edges)
		{
			edge.trim();
			String[] infoEdge = edge.split("\\|");
			int id1 = Integer.parseInt(infoEdge[0].trim());
			int id2 = Integer.parseInt(infoEdge[1].trim());
			
			Square s1 = getSquare(squares, id1);
			Square s2 = getSquare(squares, id2);
			
			tablero.addEdge(s1, s2, 1.0);
			tablero.addEdge(s2, s1, 1.0);
		}
	}

	private void addNodes(List<String> nodes) {
		for(String nodo : nodes)
		{
			Square square = getSquare(nodo);
			squares.add(square);
		}
	}
	
	private Square getSquare(String nodo)
	{
		String[] params = nodo.split("-");
		
		int id = Integer.parseInt(params[0].trim());
		QuestionType type = null;
		if(!params[1].trim().isEmpty())
		{
			type = QuestionType.valueOf(params[1]);
			
		}
		Category category = Category.valueOf(params[2].trim());
		return new Square(id, category, type);		
	}
	
	private Square getSquare(List<Square> list, int id)
	{
		for(Square node : list)
		{
			if(node.getId()==id)
			{
				return node;
			}
		}
		return null;
	}

}
