package es.uniovi.asw.trivial.ugi;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class JPanelBackgroundImage extends JPanel {

	/**
	 * Panel con imagen de fondo
	 */
	private static final long serialVersionUID = 1L;
	private Image image = null;

	public JPanelBackgroundImage(Image image) {
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, this);
	}

}
