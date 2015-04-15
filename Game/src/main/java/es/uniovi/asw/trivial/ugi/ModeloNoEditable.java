package es.uniovi.asw.trivial.ugi;

import javax.swing.table.DefaultTableModel;

public class ModeloNoEditable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public ModeloNoEditable(String[] nombreColumnas, int i) {
		super(nombreColumnas, i);
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
}