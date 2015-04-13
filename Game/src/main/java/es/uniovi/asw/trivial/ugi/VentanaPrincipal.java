package es.uniovi.asw.trivial.ugi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class VentanaPrincipal extends JFrame {

	/**
	 * Ventana de inicio de la aplicación que muestra las opciones de jugar
	 * instrucciones y salir
	 * 
	 * 
	 * Faltan ventanas de: fin del juego
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JPanel pnBackground;
	private JPanel pnInicio;
	private JButton btnJugar;
	private JButton btnInstrucciones;
	private JButton btnSalir;
	private JButton btnEstadsticas;
	private JMenuBar menuBar;
	private JMenu mnAdministrador;

	// Util

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1050, 700); // XXX: Tamaño de la pantalla 1050x700
		contentPane = new JPanel();
//TODO - <<<<<<< HEAD
		contentPane = new JPanelBackground("resources/images/background.png");
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnBackground());
//=======
		contentPane_1 = new JPanelBackground("resources/images/background.png");
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		contentPane_1.add(getPnBackground());
		contentPane_1.add(getMenuBar_1());
//TODO - >>>>>>> origin/Interface-3.0
		setTitle("Trivial2a");
		setResizable(false);
	}

	private JPanel getPnBackground() {
		if (pnBackground == null) {
			pnBackground = new JPanel();
			pnBackground.setBounds(5, 5, 1024, 655);
			pnBackground.setOpaque(false);
			pnBackground.setLayout(null);

			pnBackground.add(getPnInicio());
		}
		return pnBackground;
	}

	private JPanel getPnInicio() {
		if (pnInicio == null) {
			pnInicio = new JPanel();
			pnInicio.setBounds(335, 400, 350, 220);
			pnInicio.setLayout(new GridLayout(4, 1, 0, 25));
			pnInicio.setOpaque(false);
			pnInicio.add(getBtnJugar());
			pnInicio.add(getBtnInstrucciones());
			pnInicio.add(getBtnEstadsticas());
			pnInicio.add(getBtnSalir());
		}
		return pnInicio;
	}

	private JButton getBtnJugar() {
		if (btnJugar == null) {
			btnJugar = new JButton("Jugar");
			btnJugar.setBackground(Color.cyan);
			btnJugar.setOpaque(true);
			btnJugar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarVentanaJugadores();
				}
			});
		}
		return btnJugar;
	}

	private JButton getBtnInstrucciones() {
		if (btnInstrucciones == null) {
			btnInstrucciones = new JButton("Instrucciones");
			btnInstrucciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaInstrucciones();
				}
			});
			btnInstrucciones.setBackground(Color.yellow);
			btnInstrucciones.setOpaque(true);
		}
		return btnInstrucciones;
	}

	private JButton getBtnEstadsticas() {
		if (btnEstadsticas == null) {
			btnEstadsticas = new JButton("Estad\u00EDsticas");
			btnEstadsticas.setBackground(Color.green);
			btnEstadsticas.setOpaque(true);
			btnEstadsticas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarVentanaLoginAdmin();
				}
			});
		}
		return btnEstadsticas;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setBackground(Color.magenta);
			btnSalir.setOpaque(true);
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);				
				}
			});
		}
		return btnSalir;
	}

	private void mostrarVentanaJugadores() {
		VentanaJugadores vJ = new VentanaJugadores(this);
		vJ.setModal(true);
		vJ.setVisible(true);
	}

	private void mostrarVentanaLoginAdmin() {
		VentanaLoginAdmin vL = new VentanaLoginAdmin(this);
		vL.setModal(true);
		vL.setVisible(true);
	}
	
	private void mostrarVentanaInstrucciones(){
		JOptionPane.showMessageDialog(this,
			    "Para jugar, lo primero que has de hacer es seleccionar \n "
			    + "los usuarios de todos los jugadores. \n"
			    + "Una vez hecho esto, podrás aceder a la partida. \n\n"
			    + "Por turnos, id tirando el dado y respondiendo a las preguntas \n"
			    + "de las casillas que os haya tocado. Si aciertas, volverá a ser \n"
			    + "tu turno. Ganarás quesitos cada vez que respondas una pregunta \n"
			    + "de una casilla grande. \n\n "
			    + "Una vez conseguidos los SEIS quesitos de colores, ve a la casilla \n"
			    + "central y responde bien a las preguntas indicadas para ganar la partida. ",
			    "Instrucciones",
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon("resources/images/icon.png"));
	}
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setVisible(false);
			menuBar.setOpaque(false);
			menuBar.setBounds(0, 0, 1044, 21);
			menuBar.add(getMnAdministrador());
		}
		return menuBar;
	}
	private JMenu getMnAdministrador() {
		if (mnAdministrador == null) {
			mnAdministrador = new JMenu("Opciones");
		}
		return mnAdministrador;
	}
}
