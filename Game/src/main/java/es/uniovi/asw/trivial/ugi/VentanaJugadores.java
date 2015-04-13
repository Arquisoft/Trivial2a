package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.model.User;
import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.bussines.gameClasses.BoardOptionsFactory;
import es.uniovi.asw.trivial.factories.BusinessFactory;

public class VentanaJugadores extends JDialog {

	/**
	 * En esta ventana se irían introduciendo el nombre de los jugadores
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JButton okButton;
	private JTextField txtUsuario1;
	private JTextField txtUsuario2;
	private JTextField txtUsuario3;
	private JTextField txtUsuario4;
	private JTextField txtUsuario5;
	private JTextField txtUsuario6;
	private JLabel lblJugadores;
	private JPanel pnJugadores;
	private JLabel lblJ1;
	private JLabel lblJ6;
	private JLabel lblJ4;
	private JLabel lblJ5;
	private JLabel lblJ2;
	private JLabel lblJ3;

	private List<JTextField> jugadores;
	private VentanaPrincipal vp;

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanelBackground(
					"resources/images/bgquestion.png");
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(new BorderLayout(0, 0));
			contentPanel.add(getLblJugadores(), BorderLayout.NORTH);
			jugadores = new ArrayList<JTextField>();
			jugadores.add(getTxtUsuario1());
			jugadores.add(getTxtUsuario2());
			jugadores.add(getTxtUsuario3());
			jugadores.add(getTxtUsuario4());
			jugadores.add(getTxtUsuario5());
			jugadores.add(getTxtUsuario6());
			contentPanel.add(getPnJugadores());
			{
				JPanel buttonPane = new JPanel();
				contentPanel.add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setOpaque(false);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				{
					okButton = new JButton("Jugar");
			//HEAD - okButton.setEnabled(false);
					okButton.setEnabled(true);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								mostrarVentanaJuego();
							} catch (IllegalActionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
			asociarEventosJugadores();
		}
		return contentPanel;
	}

	public JTextField getTxtUsuario1() {
		if (txtUsuario1 == null) {
			txtUsuario1 = new JTextField();
			txtUsuario1.setColumns(10);
		}
		return txtUsuario1;
	}

//	// se activa la posibilidad de añadir el siguiente jugador
//	// y se queda guardado el nombre dle jugador anteior
//	private void txtComprobaciones() {
//		okButton.setEnabled(jugadores.get(0).getText().isEmpty() ? false : true);
//		for (int i = 0; i < jugadores.size() - 1; i++) {
//			if (i == 0)
//				jugadores.get(i + 1).setEnabled(true);
//			else {
//				if (jugadores.get(i).getText().length() > 0) {
//					if (!exist(i)) {
//						jugadores.get(i + 1).setEnabled(true);
//						if (jugadores.get(i - 1).getText().length() > 0)
//							jugadores.get(i - 1).setEditable(false);
//					}
//				}
//			}
//		}
//	}

//	// comprueba que no se repita el nombre de ningún jugador en la aprtida
//	private boolean exist(int i) {
//		boolean rep = false;
//		int j = 0;
//		while (!rep && j < jugadores.size()) {
//			// && !jugadores.get(i).isEditable()) {
//			if (i != j
//					&& !jugadores.get(j).isEditable()
//					&& jugadores.get(i).getText()
//							.equals(jugadores.get(j).getText())) {
//				rep = true;
//				jugadores.get(i).setText("");
//				jugadores.get(i).setEnabled(true);
//				jugadores.get(i + 1).setEnabled(true);
//			}
//			j++;
//		}
//		return rep;
//	}

	public JTextField getTxtUsuario2() {
		if (txtUsuario2 == null) {
			txtUsuario2 = new JTextField();
			//HEAD - txtUsuario2.setEnabled(false);
			txtUsuario2.setEnabled(true);
			txtUsuario2.setColumns(10);
		}
		return txtUsuario2;
	}

	private void asociarEventosJugadores() {
		for (int i = 1; i < pnJugadores.getComponents().length; i = i + 2) {
			JTextField txt = (JTextField) pnJugadores.getComponent(i);

			txt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
//					txtComprobaciones();
				}
			});
		}
	}

	public JTextField getTxtUsuario3() {
		if (txtUsuario3 == null) {
			txtUsuario3 = new JTextField();
			//HEAD - txtUsuario3.setEnabled(false);
			txtUsuario3.setEnabled(true);
			txtUsuario3.setColumns(10);
		}
		return txtUsuario3;
	}

	public JTextField getTxtUsuario4() {
		if (txtUsuario4 == null) {
			//HEAD - txtUsuario4.setEnabled(false);
			txtUsuario4.setEnabled(true);
			txtUsuario4.setColumns(10);
		}
		return txtUsuario4;
	}

	public JTextField getTxtUsuario5() {
		if (txtUsuario5 == null) {
			txtUsuario5 = new JTextField();
			//HEAD - txtUsuario5.setEnabled(false);
			txtUsuario5.setEnabled(true);
			txtUsuario5.setColumns(10);
		}

		return txtUsuario5;
	}

	public JTextField getTxtUsuario6() {
		if (txtUsuario6 == null) {
			txtUsuario6 = new JTextField();
			//HEAD - txtUsuario6.setEnabled(false);
			txtUsuario6.setEnabled(true);
			txtUsuario6.setColumns(10);
		}
		return txtUsuario6;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaJugadores dialog = new VentanaJugadores(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaJugadores(VentanaPrincipal ventanaPrincipal) {
		this.vp = ventanaPrincipal;
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/icon.png"));
		setResizable(false);
		setTitle("Trivial2a");
		setBounds(370, 180, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
	}

	private JLabel getLblJugadores() {
		if (lblJugadores == null) {
			lblJugadores = new JLabel("\u00BFQui\u00E9n va a jugar?");
			lblJugadores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblJugadores;
	}

	private void mostrarVentanaJuego() throws IllegalActionException {
		GameAPI g = BusinessFactory.getGameAPI();
		 List<String> userNames=new ArrayList<String>();
		for (JTextField txt : jugadores)
			if(!txt.getText().isEmpty())
				userNames.add(txt.getText());
		g.startGame(userNames,BoardOptionsFactory.getBoardOption(0));
		this.dispose();

		VentanaJuego vJ = new VentanaJuego(this, g);
		vJ.setModal(true);
		vJ.setVisible(true);
	}

	private JPanel getPnJugadores() {
		if (pnJugadores == null) {
			pnJugadores = new JPanel();
			pnJugadores.setOpaque(false);
			pnJugadores.setLayout(new GridLayout(6, 2, 30, 20));
			pnJugadores.add(getLblJ1());
			pnJugadores.add(getTxtUsuario1());
			pnJugadores.add(getLblJ2());
			pnJugadores.add(getTxtUsuario2());
			pnJugadores.add(getLblJ3());
			pnJugadores.add(getTxtUsuario3());
			pnJugadores.add(getLblJ4());
			pnJugadores.add(getTxtUsuario4());
			pnJugadores.add(getLblJ5());
			pnJugadores.add(getTxtUsuario5());
			pnJugadores.add(getLblJ6());
			pnJugadores.add(getTxtUsuario6());
		}
		return pnJugadores;
	}

	private JLabel getLblJ1() {
		if (lblJ1 == null) {
			lblJ1 = new JLabel("Jugador Uno:");
			lblJ1.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblJ1;
	}

	private JLabel getLblJ6() {
		if (lblJ6 == null) {
			lblJ6 = new JLabel("Jugador Seis:");
			lblJ6.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblJ6;
	}

	private JLabel getLblJ4() {
		if (lblJ4 == null) {
			lblJ4 = new JLabel("Jugador Cuatro:");
			lblJ4.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblJ4;
	}

	private JLabel getLblJ5() {
		if (lblJ5 == null) {
			lblJ5 = new JLabel("Jugador Cinco:");
			lblJ5.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblJ5;
	}

	private JLabel getLblJ2() {
		if (lblJ2 == null) {
			lblJ2 = new JLabel("Jugador Dos:");
			lblJ2.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblJ2;
	}

	private JLabel getLblJ3() {
		if (lblJ3 == null) {
			lblJ3 = new JLabel("Jugador Tres:");
			lblJ3.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblJ3;
	}
}
