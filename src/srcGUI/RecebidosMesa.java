import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;
import javax.swing.JComboBox;


public class RecebidosMesa extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public RecebidosMesa() {
		
		setSize(new Dimension(300,200));
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblDataSada = new JLabel("Hora Saída");
		panel.add(lblDataSada, "1, 2, left, top");
		
		textField = new JTextField();
		panel.add(textField, "2, 2, fill, center");
		textField.setColumns(10);
		
		JLabel lblDataSada_1 = new JLabel("Data Saída");
		panel.add(lblDataSada_1, "3, 2, left, default");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "4, 2, fill, center");
		textField_1.setColumns(10);
		
		JLabel lblCdigoBarco = new JLabel("Código Barco");
		panel.add(lblCdigoBarco, "1, 4, left, default");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "2, 4, fill, center");
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox, "3, 4, 2, 1, fill, default");
	}

}
