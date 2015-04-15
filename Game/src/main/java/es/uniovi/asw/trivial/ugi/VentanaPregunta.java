package es.uniovi.asw.trivial.ugi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
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

	private VentanaJuego vj;

	private String[] respuestas = new String[3];
	private int[] valor = new int[3];

	// public static void main(String[] args) {
	// try {
	// VentanaPregunta dialog = new VentanaPregunta(null);
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog. Se le podría pasar la ventana juego entera
	 * 
	 * @throws IllegalActionException
	 */
	public VentanaPregunta(VentanaJuego vj) throws IllegalActionException {
		this.vj = vj;
		// no hay preguntas****
		String name = vj.getGame().getActivePlayer();

		question = vj.getGame().getQuestion(name,
				vj.getGame().getPlayerLocation(name));

		// Pregunta de prueba
		// Question q = new Question();
		// q.setCategory(Category.HISTORY);
		// q.setCorrectAnswer("Correcta");
		// ArrayList<String> f = new ArrayList<String>();
		// f.add("Falsa 1");
		// f.add("Falsa 2");
		// q.setIncorrectAnswers(f);
		// q.setStatement("Cual es la respuesta correcta?");
		// Pregunta de prueba

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"resources/images/icon.png"));
		contentPanel = new JPanelBackground("resources/images/bgquestion.png");
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
	 * Crea el txt donde irá el enunciado de la pregunta
	 * 
	 * @return el txttField con el enuncioado
	 */
	private JTextField getTxtPregunta() {
		if (txtPregunta == null) {
			txtPregunta = new JTextField(question.getStatement());
			txtPregunta.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtPregunta.setOpaque(false);
			txtPregunta.setColumns(10);
			txtPregunta.setEditable(false);
			txtPregunta.setBorder(null);
		}
		return txtPregunta;
	}

	/**
	 * Crea el panel de respuestas
	 * 
	 * @return el panel de respuestas
	 */
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

	/**
	 * Crea el boton de la primera respuesta
	 * 
	 * @return el boton de la primera respuesta
	 */
	private JButton getBtnPrimerarespuesta() {
		if (btnPrimerarespuesta == null) {
			btnPrimerarespuesta = new JButton(getRespuesta(1));
			btnPrimerarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						validarRespuesta(((JButton) arg0.getSource()).getText());
					} catch (IllegalActionException e) {
						e.printStackTrace();
					}
				}
			});
			btnPrimerarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnPrimerarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnPrimerarespuesta;
	}

	/**
	 * Crea el boton de la segunda respuesta
	 * 
	 * @return el boton de la segunda respuesta
	 */
	private JButton getBtnSegundarespuesta() {
		if (btnSegundarespuesta == null) {
			btnSegundarespuesta = new JButton(getRespuesta(2));
			btnSegundarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnSegundarespuesta.setHorizontalTextPosition(SwingConstants.LEFT);
			btnSegundarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
			btnSegundarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						validarRespuesta(((JButton) e.getSource()).getText());
					} catch (IllegalActionException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnSegundarespuesta;
	}

	/**
	 * Crea el boton de la tercera respuesta
	 * 
	 * @return el boton de la tercera respuesta
	 */
	private JButton getBtnTercerarespuesta() {
		if (btnTercerarespuesta == null) {
			btnTercerarespuesta = new JButton(getRespuesta(3));
			btnTercerarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						validarRespuesta(((JButton) e.getSource()).getText());
					} catch (IllegalActionException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnTercerarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnTercerarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnTercerarespuesta;
	}

	/**
	 * Método que valida la respuesta pulsada
	 * 
	 * @param answer
	 *            la respuesta escogida
	 * @throws IllegalActionException
	 */
	private void validarRespuesta(String answer) throws IllegalActionException {
		{
			if (vj.getGame().isAnswerCorrect(
					question.getId(),
					answer,
					vj.getGame().getActivePlayer(),
					vj.getGame().getPlayerLocation(
							vj.getGame().getActivePlayer()))) {

				JOptionPane.showMessageDialog(this,
						"¡Has acertado la pregunta!", "¡Correcto!",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"resources/images/acierto.png"));
				vj.refreshScore();
				this.dispose();
			} else {
				dispose();
				JOptionPane.showMessageDialog(this,
						"¡Has fallado la pregunta!", "¡Mal!",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"resources/images/fallo.png"));
				vj.changeLabelName(vj.getGame().getActivePlayer());
				this.dispose();
			}
		}

	}

	/**
	 * Método que obtiene el valor de la respuesta
	 * 
	 * @param boton
	 *            posicion del boton pulsado
	 * @return valor de la respuesta
	 */
	private String getRespuesta(int boton) {
		return respuestas[boton - 1];
	}

	/**
	 * Método que sirve para desordenar las respuestas
	 */
	private void asignarRespuestas() {
		boolean[] usadas = new boolean[3];
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
}
