package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.factories.BusinessFactory;
import es.uniovi.asw.trivial.model.User;

public class VentanaJugadores extends JDialog {

	/**
	 * En esta ventana se ir�an introduciendo el nombre de los jugadores
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JButton okButton;
	private JTextField txtUsuario1;
	private JTextField txtUsuario2;
	private JTextField txtUsuario3;
	private JTextField txtUsuario4;

	private List<JTextField> jugadores;
	private VentanaPrincipal vp;

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_contentPanel.rowHeights = new int[] { 50, 30, 30, 0, 0, 0, 0,
					0, 0, 0, 0 };
			gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			contentPanel.setLayout(gbl_contentPanel);
			GridBagConstraints gbc_lblJugadores = new GridBagConstraints();
			gbc_lblJugadores.insets = new Insets(0, 0, 5, 5);
			gbc_lblJugadores.gridx = 1;
			gbc_lblJugadores.gridy = 2;
			contentPanel.add(getLblJugadores(), gbc_lblJugadores);
			jugadores = new ArrayList<JTextField>();
			GridBagConstraints gbc_txtUsuario1 = new GridBagConstraints();
			gbc_txtUsuario1.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario1.gridx = 1;
			gbc_txtUsuario1.gridy = 4;
			contentPanel.add(getTxtUsuario1(), gbc_txtUsuario1);
			jugadores.add(getTxtUsuario1());
			GridBagConstraints gbc_txtUsuario2 = new GridBagConstraints();
			gbc_txtUsuario2.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario2.gridx = 1;
			gbc_txtUsuario2.gridy = 5;
			contentPanel.add(getTxtUsuario2(), gbc_txtUsuario2);
			jugadores.add(getTxtUsuario2());
			GridBagConstraints gbc_txtUsuario3 = new GridBagConstraints();
			gbc_txtUsuario3.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario3.gridx = 1;
			gbc_txtUsuario3.gridy = 6;
			contentPanel.add(getTxtUsuario3(), gbc_txtUsuario3);
			jugadores.add(getTxtUsuario3());
			GridBagConstraints gbc_txtUsuario4 = new GridBagConstraints();
			gbc_txtUsuario4.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario4.gridx = 1;
			gbc_txtUsuario4.gridy = 7;
			contentPanel.add(getTxtUsuario4(), gbc_txtUsuario4);
			jugadores.add(getTxtUsuario4());
			GridBagConstraints gbc_txtUsuario5 = new GridBagConstraints();
			gbc_txtUsuario5.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario5.gridx = 1;
			gbc_txtUsuario5.gridy = 8;
			contentPanel.add(getTxtUsuario5(), gbc_txtUsuario5);
			jugadores.add(getTxtUsuario5());
			GridBagConstraints gbc_txtUsuario6 = new GridBagConstraints();
			gbc_txtUsuario6.insets = new Insets(0, 0, 0, 5);
			gbc_txtUsuario6.gridx = 1;
			gbc_txtUsuario6.gridy = 9;
			contentPanel.add(getTxtUsuario6(), gbc_txtUsuario6);
			jugadores.add(getTxtUsuario6());
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

	// se activa la posibilidad de a�adir el siguiente jugador
	// y se queda guardado el nombre dle jugador anteior
	private void txtComprobaciones() {
		okButton.setEnabled(jugadores.get(0).getText().isEmpty() ? false : true);
		for (int i = 0; i < jugadores.size() - 1; i++) {
			if (i == 0)
				jugadores.get(i + 1).setEnabled(true);
			else {
				if (jugadores.get(i).getText().length() > 0) {
					if (!exist(i)) {
						jugadores.get(i + 1).setEnabled(true);
						if (jugadores.get(i - 1).getText().length() > 0)
							jugadores.get(i - 1).setEditable(false);
					}
				}
			}

		}
	}

	// comprueba que no se repita el nombre de ning�n jugador en la aprtida
	private boolean exist(int i) {
		boolean rep = false;
		int j = 0;
		while (!rep && j < jugadores.size()) {
			// && !jugadores.get(i).isEditable()) {
			if (i != j
					&& !jugadores.get(j).isEditable()
					&& jugadores.get(i).getText()
							.equals(jugadores.get(j).getText())) {
				rep = true;
				jugadores.get(i).setText("");
				jugadores.get(i).setEnabled(true);
				jugadores.get(i + 1).setEnabled(false);
			}
			j++;
		}
		return rep;
	}

	public JTextField getTxtUsuario2() {
		if (txtUsuario2 == null) {
			txtUsuario2 = new JTextField();
			txtUsuario2.setEnabled(false);
			txtUsuario2.setColumns(10);
		}
		return txtUsuario2;
	}

	private void asociarEventosJugadores() {
		for (int i = 1; i < contentPanel.getComponents().length; i++) {
			JTextField txt = (JTextField) contentPanel.getComponent(i);

			txt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					txtComprobaciones();
				}
			});
		}

	}

	public JTextField getTxtUsuario3() {
		if (txtUsuario3 == null) {
			txtUsuario3 = new JTextField();
			txtUsuario3.setEnabled(false);
			txtUsuario3.setColumns(10);
		}
		return txtUsuario3;
	}

	public JTextField getTxtUsuario4() {
		if (txtUsuario4 == null) {
			txtUsuario4 = new JTextField();
			txtUsuario4.setEnabled(false);
			txtUsuario4.setColumns(10);
		}
		return txtUsuario4;
	}

	public JTextField getTxtUsuario5() {
		if (txtUsuario5 == null) {
			txtUsuario5 = new JTextField();
			txtUsuario5.setEnabled(false);
			txtUsuario5.setColumns(10);
		}

		return txtUsuario5;
	}

	public JTextField getTxtUsuario6() {
		if (txtUsuario6 == null) {
			txtUsuario6 = new JTextField();
			txtUsuario6.setEnabled(false);
			txtUsuario6.setColumns(10);
		}
		return txtUsuario6;
	}

	private JTextField txtUsuario5;
	private JTextField txtUsuario6;
	private JLabel lblJugadores;

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
		setBounds(100, 100, 760, 418);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Jugar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarVentanaJuego();
					}
				});
				okButton.setEnabled(false);
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
	}

	private JLabel getLblJugadores() {
		if (lblJugadores == null) {
			lblJugadores = new JLabel("\u00BFQui\u00E9n va a jugar?");
		}
		return lblJugadores;
	}

	private void mostrarVentanaJuego() {
		GameAPI g = BusinessFactory.getGameAPI();
		// Conf problema
		// g.selectBoardOption(BoardOptions.BIG);
		for (JTextField txt : jugadores)
			g.createUser(new User(txt.getText()));
		// Mostrar ventana de confirmaci�n de jugadores mejor
		VentanaJuego vJ = new VentanaJuego(this, g);
		vJ.setModal(true);
		vJ.setVisible(true);
	}
}
