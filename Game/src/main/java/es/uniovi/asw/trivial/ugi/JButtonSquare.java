package es.uniovi.asw.trivial.ugi;

import java.awt.Point;

import javax.swing.JButton;

import es.uniovi.asw.trivial.model.Square;

public class JButtonSquare extends JButton{

	/**
	 * Clase que crea un botón en la posición que debe de 33x33
	 * 
	 * Esta clase tiene que ser modificada puesto que la posición ahora estará almacenada en la propia Square
	 */
	private static final long serialVersionUID = 1L;
	
	private Point posicion;
	private Square info;
	
	public JButtonSquare(Point pos, Square inf){
		super();
		this.posicion=pos;
		this.info=inf;
		this.setContentAreaFilled(false);
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setBounds(pos.x, pos.y, 33, 33);
	}
	public Point getPosicion() {
		return posicion;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	public Square getInfo() {
		return info;
	}
	public void setInfo(Square info) {
		this.info = info;
	}
	
//	@Override
//	 protected void paintComponent(Graphics g) { 
//        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
//        super.paintComponent(g); 
//    } 
	

}
