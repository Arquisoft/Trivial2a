package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.model.Score;
import java.awt.Color;
import java.awt.Font;

public class PanelJugador extends JPanel {
	/**
	 * Clase que crea un panel en el que en el que se muestra el nombre y la
	 * puntuacion de un usuario
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNombre;

	private JPanel pnScore;
	private JLabel lblSports;
	private JLabel lblScience;
	private JLabel lblHistory;
	private JLabel lblShows;
	private JLabel lblArt;
	private JLabel lblGeo;
	
	private GameAPI game;
	private String jugador;

	/**
	 * Create the panel.
	 * @throws IllegalActionException 
	 */
	public PanelJugador(int i, GameAPI game) throws IllegalActionException {
		setOpaque(false);
		this.game = game;
		this.jugador=game.getPlayersNameList().get(i);
		setLayout(new BorderLayout(0, 0));
		add(getLblNombre(), BorderLayout.NORTH);
		add(getPnScore(), BorderLayout.CENTER);
		refreshScore();

	}

	public JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(jugador);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setVerticalAlignment(SwingConstants.CENTER);
			lblNombre.setAlignmentY(Component.CENTER_ALIGNMENT);
			lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblNombre;
	}
	private JPanel getPnScore() {
		if (pnScore == null) {
			pnScore = new JPanel();
			pnScore.setOpaque(false);
			pnScore.setLayout(new GridLayout(2, 3, 0, 0));
			pnScore.add(getLblSports());
			pnScore.add(getLblScience());
			pnScore.add(getLblHistory());
			pnScore.add(getLblShows());
			pnScore.add(getLblArt());
			pnScore.add(getLblGeo());
		}
		return pnScore;
	}
	private JLabel getLblSports() {
		if (lblSports == null) {
			lblSports = new JLabel();
			lblSports.setIcon(new ImageIcon("resources/images/images/sports.png"));
		}
		return lblSports;
	}
	private JLabel getLblScience() {
		if (lblScience == null) {
			lblScience = new JLabel();
			lblScience.setIcon(new ImageIcon("resources/images/images/science.png"));
		}
		return lblScience;
	}
	private JLabel getLblHistory() {
		if (lblHistory == null) {
			lblHistory = new JLabel();
			lblHistory.setIcon(new ImageIcon("resources/images/images/history.png"));
		}
		return lblHistory;
	}
	private JLabel getLblShows() {
		if (lblShows == null) {
			lblShows = new JLabel();
			lblShows.setIcon(new ImageIcon("resources/images/images/shows.png"));
		}
		return lblShows;
	}
	private JLabel getLblArt() {
		if (lblArt == null) {
			lblArt = new JLabel();
			lblArt.setIcon(new ImageIcon("resources/images/images/art.png"));
		}
		return lblArt;
	}
	private JLabel getLblGeo() {
		if (lblGeo == null) {
			lblGeo = new JLabel();
			lblGeo.setIcon(new ImageIcon("resources/images/images/geo.png"));
		}
		return lblGeo;
	}
	public void refreshScore() throws IllegalActionException{
		Score s=game.getPlayerScore(jugador);
		boolean[] quesitos={s.isSports(),s.isScienceAndTechnology(),s.isHistory(), s.isShowsAndEntertainment(), s.isArtAndLiterature(),s.isGeography()};
		int i=0;
		for(Component lbl: getPnScore().getComponents()){
			lbl.setVisible(quesitos[i]);
			i++;
		}
		if(s.isArtAndLiterature())
			getLblArt().setVisible(true);
	}
}
