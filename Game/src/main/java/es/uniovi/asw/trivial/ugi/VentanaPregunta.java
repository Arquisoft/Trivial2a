package es.uniovi.asw.trivial.ugi;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.model.Question;

/**
 * Se muestra la ventana con la pregunta arriba, el tipo de pregunta como título
 * de la ventana las diferentes respuestas como botones
 */

public class VentanaPregunta extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel;
	private Question question;
	private JTextField txtPregunta;
	private JButton btnPrimerarespuesta;
	private JButton btnSegundarespuesta;
	private JButton btnTercerarespuesta;
	private JPanel pnRespuestas;

	private String[] respuestas = new String[3];
	private int[] valor = new int[3];

	public static void main(String[] args) {
		try {
			//TODO: Quitar al acabar
			//Pregunta de prueba
			Question q = new Question();
			q.setCategory("C1");
			q.setCorrectAnswer("Correcta");
			List<String> f = new ArrayList<String>();
			f.add("Falsa 1");
			f.add("Falsa 2");
			q.setIncorrectAnswers(f);
			q.setStatement("Cual es la respuesta correcta?");
			//Pregunta de prueba

			VentanaPregunta dialog = new VentanaPregunta(q);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog. Se le podría pasar la ventana juego entera
	 */
	public VentanaPregunta(Question question) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		this.question = question;
		contentPanel = new JPanelBackground("resources/images/bgquestion.png");
		setTitle(question.getCategory());// Habría que hacerlo con un map para
											// que la muestre en castellano
		setBounds(500, 180, 400, 450);

		asignarRespuestas();

		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 384, 411);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 15));
		contentPanel.add(getTxtPregunta());
		contentPanel.add(getPnRespuestas());
	}

	/**
	 * Método para desordenar las respuestas
	 */
	private void asignarRespuestas() {
		boolean[] usadas = new boolean[3]; // F F V
		int pos;

		for (int i = 0; i < 3; i++) {
			// Boton de la pregunta
			do {
				pos = (int) ((Math.random() * 3));
			} while (valor[pos] != 0);

			// Pregunta
			int v;
			do {
				v = (int) ((Math.random() * 3));
			} while (usadas[v]);

			if (v == 2) {
				usadas[v] = true;
				respuestas[pos] = question.getCorrectAnswer();
				valor[pos] = 1;
			} else {
				usadas[v] = true;
				respuestas[pos] = question.getIncorrectAnswers().get(v);
				valor[pos] = -1;
			}
		}
	}

	private JTextField getTxtPregunta() {
		if (txtPregunta == null) {
			txtPregunta = new JTextField();
			txtPregunta.setBorder(null);
			txtPregunta.setHorizontalAlignment(SwingConstants.LEFT);
			txtPregunta.setAlignmentX(Component.LEFT_ALIGNMENT);
			txtPregunta.setEditable(false);
			txtPregunta.setText(question.getStatement());
			txtPregunta.setColumns(10);
			txtPregunta.setBackground(new Color(0, 0, 0, 0));
		}
		return txtPregunta;
	}

	private JButton getBtnPrimerarespuesta() {
		if (btnPrimerarespuesta == null) {
			btnPrimerarespuesta = new JButton(getRespuesta(1));
			btnPrimerarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					validarRespuesta(valor[0]);
				}
			});
			btnPrimerarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnPrimerarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnPrimerarespuesta;
	}

	private JButton getBtnSegundarespuesta() {
		if (btnSegundarespuesta == null) {
			btnSegundarespuesta = new JButton(getRespuesta(2));
			btnSegundarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnSegundarespuesta.setHorizontalTextPosition(SwingConstants.LEFT);
			btnSegundarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
			btnSegundarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					validarRespuesta(valor[1]);
				}
			});
		}
		return btnSegundarespuesta;
	}

	private JButton getBtnTercerarespuesta() {
		if (btnTercerarespuesta == null) {
			btnTercerarespuesta = new JButton(getRespuesta(3));
			btnTercerarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					validarRespuesta(valor[2]);
				}
			});
			btnTercerarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnTercerarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnTercerarespuesta;
	}

	private String getRespuesta(int boton) {
		return respuestas[boton - 1];
	}

	// TODO: hay que crear el método que comprueba si la respuesta es correcta y
	// muestre la ventana correspondiente

	private JPanel getPnRespuestas() {
		if (pnRespuestas == null) {
			pnRespuestas = new JPanel();
			pnRespuestas.setBackground(new Color(0, 0, 0, 0));
			pnRespuestas.setLayout(new GridLayout(3, 1, 0, 15));
			pnRespuestas.add(getBtnPrimerarespuesta());
			pnRespuestas.add(getBtnSegundarespuesta());
			pnRespuestas.add(getBtnTercerarespuesta());
		}
		return pnRespuestas;
	}

	private void validarRespuesta(int respuesta) {
		{
			if (respuesta == 1) {
				dispose();
				//TODO: Contar turno acertado
				JOptionPane.showMessageDialog(this,
						"¡Has acertado la pregunta!", "¡Correcto!",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"resources/images/acierto.png"));
			} else {
				dispose();
				//TODO: Contar turno fallado
				JOptionPane.showMessageDialog(this,
						"¡Has fallado la pregunta!", "¡Mal!",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"resources/images/fallo.png"));
			}
		}

	}
}
