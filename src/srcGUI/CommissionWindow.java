package srcGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import Background.Boat;
import Background.Sale;
import Background.Seller;
import Database.SQLDatabase;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommissionWindow extends JFrame {
	private JTextField tFBoatID;
	private JTextField tFSellerID;
	private JTextField tFPassAmount;
	private SQLDatabase dataBaseConnection;
	private JFormattedTextField tFFDepartureDate;
	private JComboBox<Boat> cBBoat;
	private JComboBox<Seller> cBSeller;
	private JFormattedTextField tFFDepartureHour;
	
	public CommissionWindow(SQLDatabase dataBaseConnection) throws SQLException{
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
		
		try {
			tFFDepartureDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}
		jPSaleInfo.add(tFFDepartureDate, "4, 2, fill, center");
		
		JLabel lblHoraDaSada = new JLabel("Hora da saída");
		jPSaleInfo.add(lblHoraDaSada, "2, 4, left, center");
		
		try {
			tFFDepartureHour = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}
		jPSaleInfo.add(tFFDepartureHour, "4, 4, fill, center");
		
		JLabel lblCod = new JLabel("Cód. barco venda");
		jPSaleInfo.add(lblCod, "2, 6, left, center");
		
		tFBoatID = new JTextField();
		tFBoatID.setEditable(false);
		jPSaleInfo.add(tFBoatID, "4, 6, fill, center");
		tFBoatID.setColumns(10);
		
		cBBoat = new JComboBox<Boat>();
		cBBoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFields();
			}
		});
		List<Boat> boats = dataBaseConnection.loadBoats(null, null);
		for(Boat b : boats) cBBoat.addItem(b);
		jPSaleInfo.add(cBBoat, "6, 6, fill, default");
		
		JLabel lblCdVendedor = new JLabel("Cód. vendedor");
		jPSaleInfo.add(lblCdVendedor, "2, 8, left, center");
		
		tFSellerID = new JTextField();
		tFSellerID.setEditable(false);
		jPSaleInfo.add(tFSellerID, "4, 8, fill, center");
		tFSellerID.setColumns(10);
		
		cBSeller = new JComboBox<Seller>();
		cBSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFields();
			}
		});
		List<Seller> sellers = dataBaseConnection.loadSellers(null, null);
		for(Seller s : sellers) cBSeller.addItem(s);
		jPSaleInfo.add(cBSeller, "6, 8, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Qtde. passageiros");
		jPSaleInfo.add(lblNewLabel_1, "2, 10, left, center");
		
		tFPassAmount = new JTextField();
		jPSaleInfo.add(tFPassAmount, "4, 10, fill, center");
		tFPassAmount.setColumns(10);
		
		JPanel jPButton = new JPanel();
		jPMain.add(jPButton, "2, 4, right, fill");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPButton.add(btnCancelar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processRegButton();
			}
		});
		jPButton.add(btnRegistrar);
	}
	
	Sale getSale(){
		int halfPass = 0;
		double passengers = GUITransUtils.getDoubleFromJText(tFPassAmount);
		int fullPass = (int)passengers;
		if((passengers - fullPass) == 0.5) halfPass = 1;
		Boat boat = (Boat)cBBoat.getSelectedItem();
		Seller seller = (Seller)cBSeller.getSelectedItem();
		return new Sale(fullPass,halfPass,0,GUITransUtils.getDepartureTimestamp(tFFDepartureHour, tFFDepartureDate),
				seller.toString(),seller.enterpriseName(),boat.toString(),boat.enterpriseName());
	}
	
	void processRegButton(){
		try{
			dataBaseConnection.storeSale(getSale());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Insira números corretos", "ERRO", JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Problemas na extração da venda", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	void updateFields(){
		try{
			Boat selectedBoat = (Boat)cBBoat.getSelectedItem();
			Seller selectedSeller = (Seller)cBSeller.getSelectedItem();
			tFBoatID.setText(String.valueOf(selectedBoat.id()));
			tFSellerID.setText(String.valueOf(selectedSeller.id()));
		}catch(Exception e){}
	}

}
