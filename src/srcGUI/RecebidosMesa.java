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
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;


public class RecebidosMesa extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	public RecebidosMesa() {
		
		setSize(new Dimension(488, 390));
		//getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//getContentPane().add(mainPanel);
		setContentPane(mainPanel);
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JPanel jPTourInfo = new JPanel();
		jPTourInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel.add(jPTourInfo, "2, 2, fill, center");
		jPTourInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("24px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("30px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JLabel lblDataSada = new JLabel("Hora Saída");
		jPTourInfo.add(lblDataSada, "2, 2, left, center");
		
		textField = new JTextField();
		jPTourInfo.add(textField, "4, 2, fill, center");
		textField.setColumns(10);
		
		JLabel lblDataSada_1 = new JLabel("Data Saída");
		jPTourInfo.add(lblDataSada_1, "6, 2, left, center");
		
		textField_1 = new JTextField();
		jPTourInfo.add(textField_1, "8, 2, fill, center");
		textField_1.setColumns(10);
		
		JLabel lblCdigoBarco = new JLabel("Código Barco");
		jPTourInfo.add(lblCdigoBarco, "2, 4, left, center");
		
		textField_2 = new JTextField();
		jPTourInfo.add(textField_2, "4, 4, fill, center");
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		jPTourInfo.add(comboBox, "6, 4, 3, 1, fill, top");
		
		JLabel lblHoraSada = new JLabel("Valor passeio");
		jPTourInfo.add(lblHoraSada, "2, 6, left, center");
		
		textField_3 = new JTextField();
		jPTourInfo.add(textField_3, "4, 6, fill, center");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("<html>Capacidade<br>do Barco</html>");
		jPTourInfo.add(lblNewLabel, "6, 6, left, center");
		
		textField_4 = new JTextField();
		jPTourInfo.add(textField_4, "8, 6, fill, center");
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Valor c/ desconto");
		jPTourInfo.add(lblNewLabel_1, "2, 8, left, center");
		
		textField_5 = new JTextField();
		jPTourInfo.add(textField_5, "4, 8, fill, top");
		textField_5.setColumns(10);
		
		JLabel lblValorAnimao = new JLabel("Valor animação");
		jPTourInfo.add(lblValorAnimao, "6, 8, left, center");
		
		textField_6 = new JTextField();
		jPTourInfo.add(textField_6, "8, 8, fill, top");
		textField_6.setColumns(10);
		
		JPanel jPQuantities = new JPanel();
		jPQuantities.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel.add(jPQuantities, "2, 4, fill, center");
		jPQuantities.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JLabel label = new JLabel("Inteiro");
		jPQuantities.add(label, "2, 2, left, center");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		jPQuantities.add(textField_7, "4, 2, left, top");
		
		JLabel label_3 = new JLabel("Free");
		jPQuantities.add(label_3, "6, 2, fill, center");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		jPQuantities.add(textField_8, "8, 2, right, top");
		
		JLabel label_2 = new JLabel("Total passageiro");
		jPQuantities.add(label_2, "2, 4, left, center");
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		jPQuantities.add(textField_9, "4, 4, left, top");
		
		JLabel label_1 = new JLabel("Valor da venda");
		jPQuantities.add(label_1, "6, 4, left, center");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		jPQuantities.add(textField_10, "8, 4,left, top");
		
		JPanel jPResults = new JPanel();
		jPResults.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel.add(jPResults, "2, 6, fill, center");
		jPResults.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("19px"),}));
		
		JLabel label_4 = new JLabel("Valor da comissão");
		jPResults.add(label_4, "2, 2, left, center");
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		jPResults.add(textField_11, "4, 2, left, top");
		
		JLabel lblValorDoBilhete = new JLabel("Valor do bilhete");
		jPResults.add(lblValorDoBilhete, "2, 4, left, center");
		
		textField_12 = new JTextField();
		jPResults.add(textField_12, "4, 4, left, default");
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Total animação");
		jPResults.add(lblNewLabel_2, "2, 6, left, center");
		
		textField_13 = new JTextField();
		jPResults.add(textField_13, "4, 6, left, default");
		textField_13.setColumns(10);
		
		
		
		JPanel jPButtons = new JPanel();
		mainPanel.add(jPButtons, "2, 8, right, center");
		
		JButton button = new JButton("Cancelar");
		jPButtons.add(button);
		
		JButton button_1 = new JButton("Registrar");
		jPButtons.add(button_1);
		
	}

}
