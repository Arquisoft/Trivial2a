package es.uniovi.asw.trivial.ugi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

public class VentanaEstadisticas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JPanel pnEstadisticas;
	private JLabel lblEstadsticas;

	String[] nombreColumnas = { "Usuario", "Totales", "Acertadas"};

	// GEOGRAFIA
	private JPanel pnCatGeografia;
	private JLabel lblGeografa;
	private JPanel pnGeoTabla;
	private JScrollPane scrGeoTabla;
	private JTable tblGeo;
	private ModeloNoEditable modeloTablaGeo;

	// ENTRETENIMIENTO
	private JPanel pnCatEntretenimiento;
	private JLabel lblEntr;
	private JPanel pnEntrTabla;
	private JScrollPane scrEntrTabla;
	private JTable tblEntr;
	private ModeloNoEditable modeloTablaEntr;

	// HISTORIA
	private JPanel pnCatHistoria;
	private JLabel lblHist;
	private JPanel pnHistTabla;
	private JScrollPane scrHistTabla;
	private JTable tblHist;
	private ModeloNoEditable modeloTablaHist;

	// ARTE Y LITERATURA
	private JPanel pnCatAYL;
	private JLabel lblAYL;
	private JPanel pnAYLTabla;
	private JScrollPane scrAYLTabla;
	private JTable tblAYL;
	private ModeloNoEditable modeloTablaAYL;

	// CIENCIA Y TECNOLOGÍA
	private JPanel pnCatCYT;
	private JLabel lblCYT;
	private JPanel pnCYTTabla;
	private JScrollPane scrCYTTabla;
	private JTable tblCYT;
	private ModeloNoEditable modeloTablaCYT;

	// DEPORTES
	private JPanel pnCatDep;
	private JLabel lblDep;
	private JPanel pnDepTabla;
	private JScrollPane scrDepTabla;
	private JTable tblDep;
	private ModeloNoEditable modeloTablaDep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaEstadisticas dialog = new VentanaEstadisticas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	/**
	 * 
	 */
	public VentanaEstadisticas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		setBounds(100, 20, 1050, 700);
		setTitle("Trivial2a");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanelBackground("resources/images/background.png");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnEstadisticas());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton cancelButton = new JButton("Atras");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		contentPanel.add(getLblEstadsticas(), BorderLayout.NORTH);
	}

	/**
	 * @return
	 */
	private JPanel getPnEstadisticas() {
		if (pnEstadisticas == null) {
			pnEstadisticas = new JPanel();
			pnEstadisticas.setOpaque(false);
			pnEstadisticas.setLayout(new GridLayout(2, 3, 0, 0));
			pnEstadisticas.add(getPnCatGeografia());
			pnEstadisticas.add(getPnCatEntretenimiento());
			pnEstadisticas.add(getPnCatHistoria());
			pnEstadisticas.add(getPnCatAYL());
			pnEstadisticas.add(getPnCatCYT());
			pnEstadisticas.add(getPnCatDep());
		}
		return pnEstadisticas;
	}

	private JLabel getLblEstadsticas() {
		if (lblEstadsticas == null) {
			lblEstadsticas = new JLabel("Estad\u00EDsticas");
			lblEstadsticas.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstadsticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblEstadsticas;
	}

	private JPanel getPnCatGeografia() {
		if (pnCatGeografia == null) {
			pnCatGeografia = new JPanel();
			pnCatGeografia.setOpaque(false);
			pnCatGeografia.setLayout(null);
			pnCatGeografia.add(getLblGeografa());
			pnCatGeografia.add(getPnGeoTabla());
		}
		return pnCatGeografia;
	}

	private JLabel getLblGeografa() {
		if (lblGeografa == null) {
			lblGeografa = new JLabel("Geograf\u00EDa");
			lblGeografa.setBounds(0, 5, 344, 14);
		}
		return lblGeografa;
	}

	private JPanel getPnGeoTabla() {
		if (pnGeoTabla == null) {
			pnGeoTabla = new JPanel();
			pnGeoTabla.setBounds(0, 24, 344, 277);
			pnGeoTabla.setLayout(new CardLayout(0, 0));
			pnGeoTabla.add(getScrGeoTabla(), "name_722905047230256");
		}
		return pnGeoTabla;
	}

	private JScrollPane getScrGeoTabla() {
		if (scrGeoTabla == null) {
			scrGeoTabla = new JScrollPane();
			scrGeoTabla.setViewportView(getTblGeo());
		}
		return scrGeoTabla;
	}

	private JTable getTblGeo() {
		if (tblGeo == null) {
			modeloTablaGeo = new ModeloNoEditable(nombreColumnas, 0);
			tblGeo = new JTable(modeloTablaGeo);
			tblGeo.getTableHeader().setReorderingAllowed(false);
			
			TableColumnModel columnModel = tblGeo.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(5);
			columnModel.getColumn(1).setPreferredWidth(2);
			columnModel.getColumn(2).setPreferredWidth(2);
		}
		return tblGeo;
	}

	// ////////////////ENTRETENIMIENTO//////////////////////
	private JPanel getPnCatEntretenimiento() {
		if (pnCatEntretenimiento == null) {
			pnCatEntretenimiento = new JPanel();
			pnCatEntretenimiento.setOpaque(false);
			pnCatEntretenimiento.setLayout(null);
			pnCatEntretenimiento.add(getLblEntr());
			pnCatEntretenimiento.add(getPnEntrTabla());
		}
		return pnCatEntretenimiento;
	}

	private JLabel getLblEntr() {
		if (lblEntr == null) {
			lblEntr = new JLabel("Entretenimiento");
			lblEntr.setBounds(0, 5, 210, 14);
		}
		return lblEntr;
	}

	private JPanel getPnEntrTabla() {
		if (pnEntrTabla == null) {
			pnEntrTabla = new JPanel();
			pnEntrTabla.setBounds(0, 24, 344, 277);
			pnEntrTabla.setLayout(new CardLayout(0, 0));
			pnEntrTabla.add(getScrEntrTabla(), "name_722932889272643");
		}
		return pnEntrTabla;
	}

	private JScrollPane getScrEntrTabla() {
		if (scrEntrTabla == null) {
			scrEntrTabla = new JScrollPane();
			scrEntrTabla.setViewportView(getTblEntr());
		}
		return scrEntrTabla;
	}

	private JTable getTblEntr() {
		if (tblEntr == null) {
			modeloTablaEntr = new ModeloNoEditable(nombreColumnas, 0);
			tblEntr = new JTable(modeloTablaEntr);
			tblEntr.getTableHeader().setReorderingAllowed(false);
			
			TableColumnModel columnModel = tblEntr.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(5);
			columnModel.getColumn(1).setPreferredWidth(2);
			columnModel.getColumn(2).setPreferredWidth(2);
		}
		return tblEntr;
	}

	// //////////////HISTORIA//////////////////////

	private JPanel getPnCatHistoria() {
		if (pnCatHistoria == null) {
			pnCatHistoria = new JPanel();
			pnCatHistoria.setOpaque(false);
			pnCatHistoria.setLayout(null);
			pnCatHistoria.add(getLblHist());
			pnCatHistoria.add(getPnHistTabla());
		}
		return pnCatHistoria;
	}

	private JLabel getLblHist() {
		if (lblHist == null) {
			lblHist = new JLabel("Historia");
			lblHist.setBounds(0, 5, 190, 14);
		}
		return lblHist;
	}

	private JPanel getPnHistTabla() {
		if (pnHistTabla == null) {
			pnHistTabla = new JPanel();
			pnHistTabla.setBounds(0, 24, 344, 277);
			pnHistTabla.setLayout(new CardLayout(0, 0));
			pnHistTabla.add(getScrHistTabla(), "name_82321767191981");
		}
		return pnHistTabla;
	}

	private JScrollPane getScrHistTabla() {
		if (scrHistTabla == null) {
			scrHistTabla = new JScrollPane();
			scrHistTabla.setViewportView(getTblHist());
		}
		return scrHistTabla;
	}

	private JTable getTblHist() {
		if (tblHist == null) {
			modeloTablaHist = new ModeloNoEditable(nombreColumnas, 0);
			tblHist = new JTable(modeloTablaHist);
			tblHist.getTableHeader().setReorderingAllowed(false);
			
			TableColumnModel columnModel = tblHist.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(5);
			columnModel.getColumn(1).setPreferredWidth(2);
			columnModel.getColumn(2).setPreferredWidth(2);
		}
		return tblHist;
	}

	// //////////////ARTE Y LIT//////////////////////

	private JPanel getPnCatAYL() {
		if (pnCatAYL == null) {
			pnCatAYL = new JPanel();
			pnCatAYL.setOpaque(false);
			pnCatAYL.setLayout(null);
			pnCatAYL.add(getLblAYL());
			pnCatAYL.add(getPnAYLTabla());
		}
		return pnCatAYL;
	}

	private JLabel getLblAYL() {
		if (lblAYL == null) {
			lblAYL = new JLabel("Arte y Literatura");
			lblAYL.setBounds(0, 5, 190, 14);
		}
		return lblAYL;
	}

	private JPanel getPnAYLTabla() {
		if (pnAYLTabla == null) {
			pnAYLTabla = new JPanel();
			pnAYLTabla.setBounds(0, 24, 344, 277);
			pnAYLTabla.setLayout(new CardLayout(0, 0));
			pnAYLTabla.add(getScrAYLTabla(), "name_722886192758659");
		}
		return pnAYLTabla;
	}

	private JScrollPane getScrAYLTabla() {
		if (scrAYLTabla == null) {
			scrAYLTabla = new JScrollPane();
			scrAYLTabla.setViewportView(getTblAYL());
		}
		return scrAYLTabla;
	}

	private JTable getTblAYL() {
		if (tblAYL == null) {
			modeloTablaAYL = new ModeloNoEditable(nombreColumnas, 0);
			tblAYL = new JTable(modeloTablaAYL);
			tblAYL.getTableHeader().setReorderingAllowed(false);
			
			TableColumnModel columnModel = tblAYL.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(5);
			columnModel.getColumn(1).setPreferredWidth(2);
			columnModel.getColumn(2).setPreferredWidth(2);
		}
		return tblAYL;
	}

	// //////////////CIENCIA Y TECNO//////////////////////

	private JPanel getPnCatCYT() {
		if (pnCatCYT == null) {
			pnCatCYT = new JPanel();
			pnCatCYT.setOpaque(false);
			pnCatCYT.setLayout(null);
			pnCatCYT.add(getLblCYT());
			pnCatCYT.add(getPnCYTTabla());
		}
		return pnCatCYT;
	}

	private JLabel getLblCYT() {
		if (lblCYT == null) {
			lblCYT = new JLabel("Ciencia y Tecnolog\u00EDa");
			lblCYT.setBounds(0, 5, 190, 14);
		}
		return lblCYT;
	}

	private JPanel getPnCYTTabla() {
		if (pnCYTTabla == null) {
			pnCYTTabla = new JPanel();
			pnCYTTabla.setBounds(0, 24, 344, 277);
			pnCYTTabla.setLayout(new CardLayout(0, 0));
			pnCYTTabla.add(getScrCYTTabla(), "name_82321767191981");
		}
		return pnCYTTabla;
	}

	private JScrollPane getScrCYTTabla() {
		if (scrCYTTabla == null) {
			scrCYTTabla = new JScrollPane();
			scrCYTTabla.setViewportView(getTblCYT());
		}
		return scrCYTTabla;
	}

	private JTable getTblCYT() {
		if (tblCYT == null) {
			modeloTablaCYT = new ModeloNoEditable(nombreColumnas, 0);
			tblCYT = new JTable(modeloTablaCYT);
			tblCYT.getTableHeader().setReorderingAllowed(false);
			
			TableColumnModel columnModel = tblCYT.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(5);
			columnModel.getColumn(1).setPreferredWidth(2);
			columnModel.getColumn(2).setPreferredWidth(2);
		}
		return tblCYT;
	}

	// //////////////DEPORTES//////////////////////
	
	private JPanel getPnCatDep() {
		if (pnCatDep == null) {
			pnCatDep = new JPanel();
			pnCatDep.setOpaque(false);
			pnCatDep.setLayout(null);
			pnCatDep.add(getLblDep());
			pnCatDep.add(getPnDepTabla());
		}
		return pnCatDep;
	}
	private JLabel getLblDep() {
		if (lblDep == null) {
			lblDep = new JLabel("Deportes");
			lblDep.setBounds(0, 5, 190, 14);
		}
		return lblDep;
	}
	
	private JPanel getPnDepTabla() {
		if (pnDepTabla == null) {
			pnDepTabla = new JPanel();
			pnDepTabla.setBounds(0, 24, 344, 277);
			pnDepTabla.setLayout(new CardLayout(0, 0));
			pnDepTabla.add(getScrDepTabla(), "name_82321767191981");
		}
		return pnDepTabla;
	}
	private JScrollPane getScrDepTabla() {
		if (scrDepTabla == null) {
			scrDepTabla = new JScrollPane();
			scrDepTabla.setViewportView(getTblDep());
		}
		return scrDepTabla;
	}
	private JTable getTblDep() {
		if (tblDep == null) {
			modeloTablaDep= new ModeloNoEditable(nombreColumnas, 0);
			tblDep = new JTable(modeloTablaDep);
			tblDep.getTableHeader().setReorderingAllowed(false);
			
			TableColumnModel columnModel = tblDep.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(5);
			columnModel.getColumn(1).setPreferredWidth(2);
			columnModel.getColumn(2).setPreferredWidth(2);
		}
		return tblDep;
	}
}
