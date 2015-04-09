package es.uniovi.asw.trivial.ugi;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.model.Square;

public class VentanaJuego extends JDialog {

	/**
	 * Clase en la que se muestra el tablero en el centro En el lateral izquiero
	 * hay un panel con los jugadores 1-5-3 En el panel del lateral derecho
	 * estarían los jugadores 2-6-4 Cana uno de estos paneles es de tipo
	 * "PanelJugador"
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel;
	private JPanelBackground pnBoard;

	// Util

	public VentanaJugadores vj;

	// Clases usadas de la lógica
	public GameAPI game;
	private JPanel pnIzq;
	private JPanel pnDrch;
	private JPanel pnJugador1;
	private JPanel pnJugador5;
	private JPanel pnJugador3;
	private JPanel pnJugador2;
	private JPanel pnJugador6;
	private JPanel pnJugador4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaJuego dialog = new VentanaJuego(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaJuego(VentanaJugadores ventanaJugadores, GameAPI game) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		setResizable(false);
		setTitle("Trivial2a");
		getContentPane().setBackground(new Color(49, 54, 146));
		this.game = game;
		this.vj = ventanaJugadores;
		setBounds(100, 20, 1050, 700); // XXX: Tamaño de la pantalla 1050x700
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		getContentPane().add(getPnIzq());
		getContentPane().add(getPnDrch());
		getContentPane().add(getPnBoard());
	}

	public GameAPI getGame() {
		return game;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanelBackground("resources/images/gameboard.jpg");
			pnBoard.setBounds(187, 1, 670, 670);
			pnBoard.setOpaque(false);
			pnBoard.setLayout(null);
		}
		return pnBoard;
	}

	private void showButtons(List<Square> sqs) {

	}

	public void pintarTablero(List<Square> sqs) {
		showButtons(sqs);
	}

	private void mostrarPregunta(Square s) {
		// TODO Obtener pregunta de la categoría y pasarla a VentanaPregunta
	}

	private JPanel getPnIzq() {
		if (pnIzq == null) {
			pnIzq = new JPanel();
			pnIzq.setBounds(864, 0, 180, 671);
			pnIzq.setOpaque(false);
			pnIzq.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnIzq.setLayout(new GridLayout(3, 0, 0, 0));
			pnIzq.add(getPnJugador1());
			pnIzq.add(getPnJugador5());
			pnIzq.add(getPnJugador3());
		}
		return pnIzq;
	}

	private JPanel getPnDrch() {
		if (pnDrch == null) {
			pnDrch = new JPanel();
			pnDrch.setBounds(0, 0, 180, 671);
			pnDrch.setOpaque(false);
			pnDrch.setAlignmentX(Component.RIGHT_ALIGNMENT);
			pnDrch.setLayout(new GridLayout(0, 1, 0, 0));
			pnDrch.add(getPnJugador2());
			pnDrch.add(getPnJugador6());
			pnDrch.add(getPnJugador4());
		}
		return pnDrch;
	}

	private JPanel getPnJugador1() {
		if (pnJugador1 == null) {
			pnJugador1 = crearPanel(0);
		}
		return pnJugador1;
	}

	private JPanel getPnJugador5() {
		if (pnJugador5 == null) {
			pnJugador5 = crearPanel(4);
		}
		return pnJugador5;
	}

	private JPanel getPnJugador3() {
		if (pnJugador3 == null) {
			pnJugador3 = crearPanel(2);
		}
		return pnJugador3;
	}

	private JPanel getPnJugador2() {
		if (pnJugador2 == null) {
			pnJugador2 = crearPanel(1);
		}
		return pnJugador2;
	}

	private JPanel getPnJugador6() {
		if (pnJugador6 == null) {
			pnJugador6 = crearPanel(5);
		}
		return pnJugador6;
	}

	private JPanel getPnJugador4() {
		if (pnJugador4 == null) {
			pnJugador4 = crearPanel(3);
		}
		return pnJugador4;
	}

	private JPanel crearPanel(int i) {
		return (game.getUserList().size() >= i) ? new PanelJugador(game
				.getUserList().get(i)) : new JPanel();
	}

}
