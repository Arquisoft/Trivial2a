package model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import conf.Conf;

/** Clase "Score" del modelo.
 * @author González Fernandez Cristian y Velázquez Vico álvaro
 * @version 1 - Last changes: -
 */
public class Panel {
	
	private List<Square> squares;
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
	
	/**Método que lee un fichero y carga los nodos correspondientes
	 * 
	 * @return
	 */
	private List<Square> loadGraph(String fichero)
	{
		List<String> nodes = Conf.getNodes(fichero);
		List<String> edges = Conf.getEdges(fichero);
		
		
		
		
		
		return squares;
		
	}

}
