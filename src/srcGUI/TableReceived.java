package srcGUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.text.ParseException;

import Background.*;
import Database.SQLDatabase;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;


public class TableReceived extends JFrame {
	private JTextField textField_2;
	private JTextField tFTourValue;
	private JTextField tFBoatCapacity;
	private JTextField tFTourValueDisc;
	private JTextField tFAnimationValue;
	private JTextField tFQtFull;
	private JTextField tFQtFree;
	private JTextField tFQtTotal;
	private JTextField tFSaleValue;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JComboBox<String> cBBoatName;
	private JFormattedTextField tFFDepartureHour;
	private JFormattedTextField tFFDepartureDate;
	private SQLDatabase dataBaseConnection;
	
	public TableReceived(SQLDatabase dataBaseConnection) {
		
		this.dataBaseConnection = dataBaseConnection;
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
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
		
		try {
			tFFDepartureHour = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}
		jPTourInfo.add(tFFDepartureHour, "4, 2, fill, center");
		
		JLabel lblDataSada_1 = new JLabel("Data Saída");
		jPTourInfo.add(lblDataSada_1, "6, 2, right, center");
		
		try {
			tFFDepartureDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		jPTourInfo.add(tFFDepartureDate, "8, 2, fill, center");
		
		JLabel lblCdigoBarco = new JLabel("Código Barco");
		jPTourInfo.add(lblCdigoBarco, "2, 4, left, center");
		
		textField_2 = new JTextField();
		jPTourInfo.add(textField_2, "4, 4, fill, center");
		textField_2.setColumns(10);
		
		cBBoatName = new JComboBox<String>();
		jPTourInfo.add(cBBoatName, "6, 4, 3, 1, fill, top");
		
		JLabel lblHoraSada = new JLabel("Valor passeio");
		jPTourInfo.add(lblHoraSada, "2, 6, left, center");
		
		tFTourValue = new JTextField();
		jPTourInfo.add(tFTourValue, "4, 6, fill, center");
		tFTourValue.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("<html>Capacidade<br>do Barco</html>");
		jPTourInfo.add(lblNewLabel, "6, 6, left, center");
		
		tFBoatCapacity = new JTextField();
		jPTourInfo.add(tFBoatCapacity, "8, 6, fill, center");
		tFBoatCapacity.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Valor c/ desconto");
		jPTourInfo.add(lblNewLabel_1, "2, 8, left, center");
		
		tFTourValueDisc = new JTextField();
		jPTourInfo.add(tFTourValueDisc, "4, 8, fill, top");
		tFTourValueDisc.setColumns(10);
		
		JLabel lblValorAnimao = new JLabel("Valor animação");
		jPTourInfo.add(lblValorAnimao, "6, 8, left, center");
		
		tFAnimationValue = new JTextField();
		jPTourInfo.add(tFAnimationValue, "8, 8, fill, top");
		tFAnimationValue.setColumns(10);
		
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
		
		tFQtFull = new JTextField();
		tFQtFull.setColumns(10);
		jPQuantities.add(tFQtFull, "4, 2, left, top");
		
		JLabel label_3 = new JLabel("Free");
		jPQuantities.add(label_3, "6, 2, fill, center");
		
		tFQtFree = new JTextField();
		tFQtFree.setColumns(10);
		jPQuantities.add(tFQtFree, "8, 2, right, top");
		
		JLabel label_2 = new JLabel("Total passageiro");
		jPQuantities.add(label_2, "2, 4, left, center");
		
		tFQtTotal = new JTextField();
		tFQtTotal.setColumns(10);
		jPQuantities.add(tFQtTotal, "4, 4, left, top");
		
		JLabel label_1 = new JLabel("Valor da venda");
		jPQuantities.add(label_1, "6, 4, left, center");
		
		tFSaleValue = new JTextField();
		tFSaleValue.setColumns(10);
		jPQuantities.add(tFSaleValue, "8, 4,left, top");
		
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPButtons.add(button);
		
		JButton button_1 = new JButton("Registrar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processRegButton();
			}
		});
		jPButtons.add(button_1);
		
	}
	
	private int getIntFormJText(JTextField jt) throws NumberFormatException{
		try{
			return Integer.parseInt(jt.getText());
		}catch(NumberFormatException e){
			jt.setBackground(Color.RED);
			throw e;
		}
	}
	
	Timestamp getDepartureTimestamp() throws IllegalArgumentException{
		try{
			String dateFormated = tFFDepartureDate.getText();
			String year = dateFormated.substring(dateFormated.lastIndexOf('/') + 1);
			dateFormated = dateFormated.substring(0,dateFormated.lastIndexOf('/'));
			String month = dateFormated.substring(dateFormated.lastIndexOf('/') + 1);
			String day = dateFormated.substring(0,dateFormated.indexOf('/'));
			
			return Timestamp.valueOf(year + "-" + month + "-" + day + " " + tFFDepartureHour.getText() + ":00");
		}catch(IllegalArgumentException e){
			tFFDepartureDate.setBackground(Color.RED);
			tFFDepartureHour.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Insira data ou hora corretamente", "Erro!", JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}
	
	Tour getTour(){
		try{
			String boatEnterprise = null;
			//TODO get boatEnterprise from DB.
			return new Tour(getIntFormJText(tFQtFull),0, getIntFormJText(tFQtFree),
	                getDepartureTimestamp(),
	                (String)cBBoatName.getSelectedItem(), boatEnterprise);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Insira números inteiros corretos", "ERRO", JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Problemas na extração do passeio", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		return null;		
	}
	
	void processRegButton(){
		dataBaseConnection.storeTour(getTour());
	}

}
