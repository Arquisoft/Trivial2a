package es.uniovi.asw.trivial.ugi;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.uniovi.asw.trivial.model.Question;

/**
 * Se muestra la ventana con la pregunta arriba, el tipo de pregunta como título de la ventana
 * las diferentes respuestas como botones
 */

public class VentanaPregunta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Question question;
	private JTextField txtPregunta;
	private JButton btnPrimerarespuesta;
	private JButton btnSegundarespuesta;
	private JButton btnTercerarespuesta;
	
	public static void main(String[] args) {
		try {
			VentanaPregunta dialog = new VentanaPregunta(new Question());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * Se le podría pasar la ventana juego entera
	 */
	public VentanaPregunta(Question question) {
		this.question=question;
		setTitle(question.getCategory());//Habría que hacerlo con un map para que la muestre en castellano
		setBounds(100, 100, 394, 449);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 378, 411);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		contentPanel.add(getTxtPregunta());
		contentPanel.add(getBtnPrimerarespuesta());
		contentPanel.add(getBtnSegundarespuesta());
		contentPanel.add(getBtnTercerarespuesta());
	}

	private JTextField getTxtPregunta() {
		if (txtPregunta == null) {
			txtPregunta = new JTextField();
			txtPregunta.setHorizontalAlignment(SwingConstants.LEFT);
			txtPregunta.setAlignmentX(Component.LEFT_ALIGNMENT);
			txtPregunta.setEditable(false);
			txtPregunta.setText(question.getStatement());
			txtPregunta.setColumns(10);
		}
		return txtPregunta;
	}
	private JButton getBtnPrimerarespuesta() {
		if (btnPrimerarespuesta == null) {
			btnPrimerarespuesta = new JButton(crearRespuesta());
			btnPrimerarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnPrimerarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnPrimerarespuesta;
	}
	private JButton getBtnSegundarespuesta() {
		if (btnSegundarespuesta == null) {
			btnSegundarespuesta = new JButton(crearRespuesta());
			btnSegundarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnSegundarespuesta.setHorizontalTextPosition(SwingConstants.LEFT);
			btnSegundarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
			btnSegundarespuesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return btnSegundarespuesta;
	}
	private JButton getBtnTercerarespuesta() {
		if (btnTercerarespuesta == null) {
			btnTercerarespuesta = new JButton(crearRespuesta());
			btnTercerarespuesta.setVerticalAlignment(SwingConstants.TOP);
			btnTercerarespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnTercerarespuesta;
	}
	//coloca las respuestas de forma aleatoria
	private boolean[] creadas={false, false, false};
	private String crearRespuesta(){
		Random rnd=new Random();
		int pos=-1;
		do{
			pos=(int) (rnd.nextDouble() * 3 + 0);
		}while(!creadas[pos]);
		return (pos>0)?question.getIncorrectAnswers().get(pos):question.getCorrectAnswer();
		
	}
	
	//hay que crear el método que comprueba si la respuesta es correcta y muestre la ventana correspondiente
	
}
