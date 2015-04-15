package es.uniovi.asw.trivial.ugi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;

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
	private JPanel pnIzq;
	private JPanel pnDrch;
	private JPanel pnJugador1;
	private JPanel pnJugador5;
	private JPanel pnJugador3;
	private JPanel pnJugador2;
	private JPanel pnJugador6;
	private JPanel pnJugador4;
	private JPanel panel;
	private JPanel pnJugada;
	private JLabel lblName;
	private JPanel pnDice;
	private JButton btnDice;
	List<JButtonSquare> squareButtons;

	// Util
	public VentanaJugadores vj;
	private Set<String> colours;
	VentanaPregunta vpreg;

	// Clases usadas de la lógica
	public GameAPI game;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// VentanaJuego dialog = new VentanaJuego(null, null);
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 * 
	 * @throws IllegalActionException
	 */
	public VentanaJuego(VentanaJugadores ventanaJugadores, GameAPI game)
			throws IllegalActionException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		setResizable(false);
		setTitle("Trivial2a");
		getContentPane().setBackground(new Color(49, 54, 146));
		this.game = game;
		this.vj = ventanaJugadores;
		setBounds(100, 20, 1050, 700);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		getContentPane().add(getPnIzdo());
		getContentPane().add(getPnDcho());
		getContentPane().add(getPnBoard());

		chooseColours();
		pintarTablero();
	}

	/**
	 * Crea el panel del tablero
	 * 
	 * @return el panel del tabelro
	 * @throws IllegalActionException
	 */
	private JPanel getPnBoard() throws IllegalActionException {
		if (pnBoard == null) {
			pnBoard = new JPanelBackground("resources/images/gameboard.png");
			pnBoard.setBounds(187, 1, 670, 670);
			pnBoard.setOpaque(false);
			pnBoard.setLayout(null);
			pnBoard.add(getPanel());
		}
		return pnBoard;
	}

	/**
	 * Crea el panel izquierdo de jugadores
	 * 
	 * @return el panel izquierdo de jugadores
	 * @throws IllegalActionException
	 */
	private JPanel getPnIzdo() throws IllegalActionException {
		if (pnIzq == null) {
			pnIzq = new JPanel();
			pnIzq.setBounds(864, 0, 180, 671);
			pnIzq.setOpaque(false);
			pnIzq.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnIzq.setLayout(new GridLayout(3, 0, 0, 0));

			pnIzq.add(getPnJugador2());
			pnIzq.add(getPnJugador6());
			pnIzq.add(getPnJugador4());
		}
		return pnIzq;
	}

	/**
	 * Crea el panel derecho de jugadores
	 * 
	 * @return el panel derecho de jugadores
	 * @throws IllegalActionException
	 */
	private JPanel getPnDcho() throws IllegalActionException {
		if (pnDrch == null) {
			pnDrch = new JPanel();
			pnDrch.setBounds(0, 0, 180, 671);
			pnDrch.setOpaque(false);
			pnDrch.setAlignmentX(Component.RIGHT_ALIGNMENT);
			pnDrch.setLayout(new GridLayout(0, 1, 0, 0));

			pnDrch.add(getPnJugador1());
			pnDrch.add(getPnJugador3());
			pnDrch.add(getPnJugador5());

		}
		return pnDrch;
	}

	/**
	 * Crea el panel con el score del jugador 1
	 * 
	 * @return el panel del jugador 1
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugador1() throws IllegalActionException {
		if (pnJugador1 == null) {
			pnJugador1 = crearPanel(0);
			pnJugador1.setOpaque(false);
		}
		return pnJugador1;
	}

	/**
	 * Crea el panel con el score del jugador 2
	 * 
	 * @return el panel del jugador 2
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugador2() throws IllegalActionException {
		if (pnJugador2 == null) {
			pnJugador2 = crearPanel(1);
			pnJugador2.setOpaque(false);
		}
		return pnJugador2;
	}

	/**
	 * Crea el panel con el score del jugador 3
	 * 
	 * @return el panel del jugador 3
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugador3() throws IllegalActionException {
		if (pnJugador3 == null) {
			pnJugador3 = crearPanel(2);
			pnJugador3.setOpaque(false);
		}
		return pnJugador3;
	}

	/**
	 * Crea el panel con el score del jugador 4
	 * 
	 * @return el panel del jugador 4
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugador4() throws IllegalActionException {
		if (pnJugador4 == null) {
			pnJugador4 = crearPanel(3);
			pnJugador4.setOpaque(false);
		}
		return pnJugador4;
	}

	/**
	 * Crea el panel con el score del jugador 5
	 * 
	 * @return el panel del jugador 5
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugador5() throws IllegalActionException {
		if (pnJugador5 == null) {
			pnJugador5 = crearPanel(4);
			pnJugador5.setOpaque(false);
		}
		return pnJugador5;
	}

	/**
	 * Crea el panel con el score del jugador 6
	 * 
	 * @return el panel del jugador 6
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugador6() throws IllegalActionException {
		if (pnJugador6 == null) {
			pnJugador6 = crearPanel(5);
			pnJugador6.setOpaque(false);
		}
		return pnJugador6;
	}

	/**
	 * Crea el panel de score del jugador indicado
	 * 
	 * @param i
	 *            el numero del jugador
	 * @return el panel creado
	 * @throws IllegalActionException
	 */
	private JPanel crearPanel(int i) throws IllegalActionException {
		return (game.getPlayersNameList().size() > i) ? new PanelJugador(i,
				game) : new JPanel();
	}

	/**
	 * Devuelve el panel
	 * 
	 * @return el panel
	 * @throws IllegalActionException
	 */
	private JPanel getPanel() throws IllegalActionException {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 10, 670, 660);
			panel.setOpaque(false);
			panel.setLayout(null);
			panel.add(getPnJugada());
		}
		return panel;
	}

	/**
	 * Devuelve el panel de juego
	 * 
	 * @return el panel de juego
	 * @throws IllegalActionException
	 */
	private JPanel getPnJugada() throws IllegalActionException {
		if (pnJugada == null) {
			pnJugada = new JPanel();
			pnJugada.setBounds(287, 79, 89, 86);
			pnJugada.setOpaque(false);
			pnJugada.setLayout(null);
			pnJugada.add(getLblName());
			pnJugada.add(getPnDice());
		}
		return pnJugada;
	}

	/**
	 * Devuelve la etiqueta del nombre del jugador en turno
	 * 
	 * @return la etiqueta del jugador en turno
	 * @throws IllegalActionException
	 */
	private JLabel getLblName() throws IllegalActionException {
		if (lblName == null) {
			lblName = new JLabel(game.getActivePlayer());
			lblName.setVerticalAlignment(SwingConstants.TOP);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblName.setForeground(Color.WHITE);
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setHorizontalTextPosition(SwingConstants.CENTER);
			lblName.setBounds(0, 0, 90, 22);
		}
		return lblName;
	}

	/**
	 * Crea el panel del dado
	 * 
	 * @return el panel del dado
	 */
	private JPanel getPnDice() {
		if (pnDice == null) {
			pnDice = new JPanel();
			pnDice.setBounds(0, 33, 90, 54);
			pnDice.setOpaque(false);
			pnDice.setLayout(null);
			pnDice.add(getBtnDice());
		}
		return pnDice;
	}

	/**
	 * Crea el boton del dado
	 * 
	 * @return el boton del dado
	 */
	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton();
			btnDice.setContentAreaFilled(false);
			btnDice.setBorderPainted(false);
			btnDice.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnDice.setBounds(20, 0, 50, 50);
			btnDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						tirarDado();
					} catch (IllegalActionException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnDice.setOpaque(false);
			btnDice.setIcon(new ImageIcon("resources/images/1.png"));
		}
		return btnDice;
	}

	/**
	 * Devuelve la instancia de GameApi que esta usando la partida en curso
	 * 
	 * @return la instancia de GameAPI
	 */
	public GameAPI getGame() {
		return game;
	}

	/**
	 * Muestra los botones de las posiciones a las que se puede ir
	 * 
	 * @param sqs
	 *            conjunto de casillas a las que se puede ir en la tirada en
	 *            curso
	 * @throws IllegalActionException
	 */
	public void showButtons(Set<Integer> sqs) throws IllegalActionException {
		squareButtons = new ArrayList<JButtonSquare>();
		for (Integer s : sqs) {
			JButtonSquare aux = new JButtonSquare(s, game.getSquares().get(s));
			aux.setVisible(true);
			squareButtons.add(aux);
			aux.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JButtonSquare j = (JButtonSquare) e.getSource();
						game.movePlayerTo(j.getInfo(), game.getActivePlayer());
						deleteButtons();
						mostrarPregunta();
					} catch (IllegalActionException e1) {
						e1.printStackTrace();
					}
				}
			});
			getPnBoard().add(aux);
		}
	}

	/**
	 * Muestra la ventana de la pregunta
	 * 
	 * @throws IllegalActionException
	 */
	private void mostrarPregunta() throws IllegalActionException {
		vpreg = new VentanaPregunta(this);
		vpreg.setModal(true);
		vpreg.setVisible(true);
	}

	/**
	 * Elimina todos los botones tras su uso
	 * 
	 * @throws IllegalActionException
	 */
	private void deleteButtons() throws IllegalActionException {
		getPnBoard().removeAll();
		getPnBoard().add(getPanel());
		pintarTablero();
	}

	/**
	 * Pinta el tablero con el estado de la partida
	 * 
	 * @throws IllegalActionException
	 */
	public void pintarTablero() throws IllegalActionException {
		Map<Integer, String> pos = new HashMap<Integer, String>();
		panel.removeAll();
		panel.add(getPnJugada());
		int count = 0;
		int i = 0;
		for (String c : colours) {
			String name = game.getPlayersNameList().get(i);
			int square = game.getPlayerLocation(name);
			if (!pos.containsKey(square))
				count = 0;
			pos.put(square, name);
			int x = (int) game.getSquares().get(square).getX();
			int y = (int) game.getSquares().get(square).getY();
			JLabel j = new JLabel(new ImageIcon("resources/images/" + c
					+ ".png"));
			double a = (count) * (Math.PI * 2) / colours.size();
			if (colours.size() > 1) {
				x = (int) Math.round(11 * Math.cos(a) + x);
				y = (int) Math.round(11 * Math.sin(a) + y);
			}
			j.setBounds(x - 10, y - 20, 25, 24);
			count++;
			panel.add(j);
			i++;
		}
		panel.repaint();
	}

	/**
	 * Refresca las puntuaciones de los jugadores
	 * 
	 * @throws IllegalActionException
	 */
	public void refreshScore() throws IllegalActionException {
		for (Component c : getPnDcho().getComponents()) {
			if (c instanceof PanelJugador) {
				if (((PanelJugador) c).getLblNombre().getText()
						.equals(game.getActivePlayer())) {
					((PanelJugador) c).refreshScore();
				}
			}
		}
		for (Component c : getPnIzdo().getComponents()) {
			if (c instanceof PanelJugador) {
				System.out.println(((PanelJugador) c).getLblNombre().getText());
				System.out.println(game.getActivePlayer());
				if (((PanelJugador) c).getLblNombre().getText()
						.equals(game.getActivePlayer())) {
					((PanelJugador) c).refreshScore();
				}
			}
		}
	}

	public void chooseColours() {
		String[] posibleColours = { "green", "blue", "orange", "brown", "red",
				"yellow" };
		Random r = new Random();
		colours = new TreeSet<String>();
		for (int i = 0; i < game.getPlayersNameList().size(); i++) {
			int colour;
			do {
				colour = r.nextInt(6);
			} while (colours.contains(posibleColours[colour]));
			colours.add(posibleColours[colour]);
		}
	}

	/**
	 * Tira el dado
	 * 
	 * @throws IllegalActionException
	 */
	public void tirarDado() throws IllegalActionException {
		btnDice.setIcon(new ImageIcon("resources/images/" + game.rollDice()
				+ ".png"));
		showButtons(game.getMovements(game.getActivePlayer(),
				game.getPlayerLocation(game.getActivePlayer())));
	}

	/**
	 * Cambia el nombre de la etiqueta del jugador en curso
	 * 
	 * @param player
	 *            el nombre del jugador
	 */
	public void changeLabelName(String player) {
		lblName.setText(player);
	}
}
