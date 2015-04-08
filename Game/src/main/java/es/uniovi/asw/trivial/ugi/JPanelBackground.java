package es.uniovi.asw.trivial.ugi;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelBackground extends JPanel {
	/**
	 * Clase para poner el fondo de un panel.
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imagen;
	String url;

	/**
	 * Create the panel.
	 */
	public JPanelBackground(String nombre) {
		this.url=nombre;
	}
	
	public void paint(Graphics g){
		Dimension tam=getSize();
		imagen= new ImageIcon(VentanaPrincipal.class.getResource(url));
		g.drawImage(imagen.getImage(), 0,0, tam.width, tam.height, null);
		this.setOpaque(false);
		super.paint(g);		
	}

}
