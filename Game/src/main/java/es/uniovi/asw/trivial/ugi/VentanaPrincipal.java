package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

	/**
	 * Ventana de inicio de la aplicación que muestra las opciones de jugar instrucciones y salir
	 * 
	 * 
	 * faltan ventanas de:
	 *  instrucciones, pregunta correcta, pregunta incorrecta, fin del juego
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnBackground;
	private JPanel pnInicio;
	private JButton btnJugar;
	private JButton btnInstrucciones;
	private JButton btnSalir;
	
	//Util

	

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnBackground());
	}
	private JPanel getPnBackground() {
		if (pnBackground == null) {
			pnBackground = new JPanel();
			pnBackground.setLayout(null);
			pnBackground.add(getPnInicio());
		}
		return pnBackground;
	}
	private JPanel getPnInicio() {
		if (pnInicio == null) {
			pnInicio = new JPanel();
			pnInicio.setBounds(162, 69, 346, 278);
			pnInicio.setLayout(new GridLayout(3, 1, 0, 0));
			pnInicio.add(getBtnJugar());
			pnInicio.add(getBtnInstrucciones());
			pnInicio.add(getBtnSalir());
		}
		return pnInicio;
	}
	private JButton getBtnJugar() {
		if (btnJugar == null) {
			btnJugar = new JButton("Jugar");
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
		}
		return btnInstrucciones;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
		}
		return btnSalir;
	}
	private void mostrarVentanaJugadores(){
		VentanaJugadores vJ=new VentanaJugadores(this);
		vJ.setModal(true);
		vJ.setVisible(true);
	}
}
