package srcGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import Database.SQLDatabase;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CommissionWindow extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private SQLDatabase dataBaseConnection;
	
	public CommissionWindow(SQLDatabase dataBaseConnection) {
		this.dataBaseConnection = dataBaseConnection;
		setSize(new Dimension(488, 390));
		JPanel jPMain = new JPanel();
		jPMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//getContentPane().add(panel, BorderLayout.NORTH);
		setContentPane(jPMain);
		jPMain.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("139px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("35px"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JPanel jPSaleInfo = new JPanel();
		jPSaleInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jPMain.add(jPSaleInfo, "2, 2, fill, fill");
		jPSaleInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Data da venda");
		jPSaleInfo.add(lblNewLabel, "2, 2, left, center");
		
		textField = new JTextField();
		jPSaleInfo.add(textField, "4, 2, fill, center");
		textField.setColumns(10);
		
		JLabel lblHoraDaSada = new JLabel("Hora da saída");
		jPSaleInfo.add(lblHoraDaSada, "2, 4, left, center");
		
		textField_1 = new JTextField();
		jPSaleInfo.add(textField_1, "4, 4, fill, center");
		textField_1.setColumns(10);
		
		JLabel lblCod = new JLabel("Cód. barco venda");
		jPSaleInfo.add(lblCod, "2, 6, left, center");
		
		textField_2 = new JTextField();
		jPSaleInfo.add(textField_2, "4, 6, fill, center");
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		jPSaleInfo.add(comboBox, "6, 6, fill, default");
		
		JLabel lblCdVendedor = new JLabel("Cód. vendedor");
		jPSaleInfo.add(lblCdVendedor, "2, 8, left, center");
		
		textField_3 = new JTextField();
		jPSaleInfo.add(textField_3, "4, 8, fill, center");
		textField_3.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		jPSaleInfo.add(comboBox_1, "6, 8, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Qtde. passageiros");
		jPSaleInfo.add(lblNewLabel_1, "2, 10, left, center");
		
		textField_4 = new JTextField();
		jPSaleInfo.add(textField_4, "4, 10, fill, center");
		textField_4.setColumns(10);
		
		JPanel jPButton = new JPanel();
		jPMain.add(jPButton, "2, 4, right, fill");
		
		JButton btnCancelar = new JButton("Cancelar");
		jPButton.add(btnCancelar);
		
		JButton btnRegistrar = new JButton("Registrar");
		jPButton.add(btnRegistrar);
	}

}
