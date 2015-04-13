//package es.uniovi.asw.trivial.ugi;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
//
//public class VentanaJugada extends JDialog {
//
//	/**
//	 * En esta ventana aparece el nombre del jugador actual as� como la opci�n
//	 * de tirar el dado Tras tirarlo se muestra la opci�n de mover y se cierra
//	 * la ventana apareciendo en la de juego los botones accesibles
//	 */
//	private static final long serialVersionUID = 1L;
//	private final JPanel contentPanel = new JPanel();
//	private JPanel pnDice;
//	private JLabel lblDado;
//	private JButton btnTirar;
//
//	private boolean dadotirado = false;
//
//	private VentanaJuego vj;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			VentanaJugada dialog = new VentanaJugada(null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 */
//	public VentanaJugada(VentanaJuego vj) {
//		this.vj = vj;
//		setBounds(100, 100, 356, 378);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setLayout(new FlowLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.NORTH);
//		{
//			JLabel lblTurno = new JLabel("Turno de: "
//					+ vj.getGame().getCurentUser().getLogin());
//			contentPanel.add(lblTurno);
//		}
//		getContentPane().add(getPnDice(), BorderLayout.CENTER);
//	}
//
//	public JPanel getPnDice() {
//		if (pnDice == null) {
//			pnDice = new JPanel();
//			pnDice.setOpaque(false);
//			pnDice.add(getLblDado());
//			pnDice.add(getBtnTirar());
//		}
//		return pnDice;
//	}
//
//	public JButton getBtnTirar() {
//		if (btnTirar == null) {
//			btnTirar = new JButton("tirar");
//			btnTirar.setBounds(121, 222, 101, 44);
//			btnTirar.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					if (dadotirado)
//						mover();
//					else
//						tirarDado();
//				}
//			});
//		}
//		return btnTirar;
//	}
//
//	protected void mover() {
//		// vj.pintarTablero(vj.getGame().getMovements());
//		dispose();
//	}
//
//	public JLabel getLblDado() {
//		if (lblDado == null) {
//			getPnDice().setLayout(null);
//			lblDado = new JLabel("");
//			lblDado.setBounds(64, 11, 204, 185);
//			pnDice.add(lblDado);
//		}
//		return lblDado;
//	}
//
//	public void tirarDado() {
//		try {
//			lblDado.setIcon(new ImageIcon("resources/images/"
//					+ vj.getGame().rollDice() + ".png"));
//		} catch (IllegalActionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		btnTirar.setText("mover");
//	}
//
//}
