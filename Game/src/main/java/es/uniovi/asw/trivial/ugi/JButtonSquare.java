package es.uniovi.asw.trivial.ugi;

import java.awt.Point;

import es.uniovi.asw.trivial.model.Square;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonSquare extends JButton {

	/**
	 * Clase que crea un boton en la posicion que debe de 33x33
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int info;

	public JButtonSquare(int inf, Point p) {
		super();
		this.setIcon(new ImageIcon("resources/images/3D.png"));
		this.info = inf;
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setBounds((int)Math.round(p.getX()-20), (int)Math.round(p.getY()-20), 41, 41);
	}
	
	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

//	 @Override
//	 protected void paintComponent(Graphics g) {
//	 g.drawImage(Toolkit.getDefaultToolkit().getImage("/resources/images/3D.png"), 0, 27, null);
//	 super.paintComponent(g);
//	 }
}
