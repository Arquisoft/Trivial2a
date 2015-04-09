package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.uniovi.asw.trivial.model.User;

public class PanelJugador extends JPanel {
	/**
	 * Clase que crea un panel en el que en el que se muestra el nombre y la
	 * puntuacion de un usuario
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNombre;

	private User user;
	private JLabel lblscore;

	/**
	 * Create the panel.
	 */
	public PanelJugador(User user) {
		this.user = user;
		setLayout(new BorderLayout(0, 0));
		add(getLblNombre(), BorderLayout.NORTH);
		add(getLblscore(), BorderLayout.CENTER);

	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(user.getLogin());
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setVerticalAlignment(SwingConstants.CENTER);
			lblNombre.setAlignmentY(Component.CENTER_ALIGNMENT);
			lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblNombre;
	}

	private JLabel getLblscore() {
		if (lblscore == null) {
			lblscore = new JLabel("");
			lblscore.setIcon(new ImageIcon("resources/images/scoreCompleto.jpg"));
		}
		return lblscore;
	}
}
