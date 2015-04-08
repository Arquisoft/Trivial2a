package es.uniovi.asw.trivial.ugi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class VentanaPrincipal extends JFrame {

	/**
	 * Ventana de inicio de la aplicación que muestra las opciones de jugar
	 * instrucciones y salir
	 * 
	 * 
	 * Faltan ventanas de: instrucciones, pregunta correcta, pregunta
	 * incorrecta, fin del juego
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnBackground;
	private JPanel pnInicio;
	private JButton btnJugar;
	private JButton btnInstrucciones;
	private JButton btnSalir;
	private JButton btnEstadsticas;

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
				VentanaPrincipal.class
						.getResource("/es/uniovi/asw/trivial/images/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1050, 700); // XXX: Tamaño de la pantalla 1050x700
		contentPane = new JPanelBackground(
				"/es/uniovi/asw/trivial/images/background.png");
		// XXX: Con el otro JPanelBackgroundImage da un error de ImageIcon
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnBackground());
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
}
