package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaLoginAdmin extends JDialog {

	/**
	 * Ventana que permite al administrador registrarse para ver las
	 * estad�sticas
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private VentanaPrincipal vp;
	private char[] passInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaLoginAdmin dialog = new VentanaLoginAdmin(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaLoginAdmin(VentanaPrincipal ventanaPrincipal) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		setResizable(false);
		setTitle("Trivial2a");
		setBounds(400, 180, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanelBackground("resources/images/bgquestion.png");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblIniciarSesion = new JLabel("Iniciar sesion");
			lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblIniciarSesion, BorderLayout.NORTH);
		}
		{
			JPanel pnForm = new JPanel();
			pnForm.setOpaque(false);
			contentPanel.add(pnForm, BorderLayout.CENTER);
			pnForm.setLayout(null);
			{
				JLabel lblUsuario = new JLabel("Usuario");
				lblUsuario.setBounds(90, 39, 59, 25);
				pnForm.add(lblUsuario);
			}
			{
				txtUser = new JTextField();
				txtUser.setBounds(161, 44, 157, 25);
				pnForm.add(txtUser);
				txtUser.setColumns(10);
			}
			{
				JLabel lblContrasea = new JLabel("Contrase\u00F1a");
				lblContrasea.setBounds(90, 86, 59, 36);
				pnForm.add(lblContrasea);
			}
			{
				txtPass = new JPasswordField(10);
				txtPass.setBounds(161, 92, 157, 25);
				pnForm.add(txtPass);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Entrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						passInput = txtPass.getPassword();						
						if (txtUser.getText().equals("admin") && isPasswordCorrect()) {
							mostrarVentanaEstadisticas();
						} else {
							mostrarVentanaError();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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

	private void mostrarVentanaError() {
		JOptionPane.showMessageDialog(this, "Datos incorrectos", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarVentanaEstadisticas() {
		dispose();
		VentanaEstadisticas vEst = new VentanaEstadisticas();
		vEst.setModal(true);
		vEst.setVisible(true);		
	}
	
	private boolean isPasswordCorrect() {
		boolean isCorrect = true;
		char[] correctPassword = { 'a', 'd', 'm', 'i', 'n' };
		if (passInput.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(passInput, correctPassword);
		}
		return isCorrect;
	}
}
