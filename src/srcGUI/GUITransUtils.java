package srcGUI;

import java.awt.Color;
import java.sql.Timestamp;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Background.Boat;
import Background.Seller;
import test.JulianoTester;

public class GUITransUtils {
	public static int getIntFromJText(JTextField jt) throws NumberFormatException{
		try{
			return Integer.parseInt(jt.getText());
		}catch(NumberFormatException e){
			jt.setBackground(Color.RED);
			throw e;
		}	
	}
	
	public static double getDoubleFromJText(JTextField jt) throws NumberFormatException{
		try{
			return Double.parseDouble(jt.getText());
		}catch(NumberFormatException e){
			jt.setBackground(Color.RED);
			throw e;
		}	
	}
	
	public static Timestamp getDepartureTimestamp(JFormattedTextField jTHour, JFormattedTextField jTDate) throws IllegalArgumentException{
		try{
			String dateFormated = jTDate.getText();
			String year = dateFormated.substring(dateFormated.lastIndexOf('/') + 1);
			dateFormated = dateFormated.substring(0,dateFormated.lastIndexOf('/'));
			String month = dateFormated.substring(dateFormated.lastIndexOf('/') + 1);
			String day = dateFormated.substring(0,dateFormated.indexOf('/'));
			
			return Timestamp.valueOf(year + "-" + month + "-" + day + " " + jTHour.getText() + ":00");
		}catch(IllegalArgumentException e){
			jTDate.setBackground(Color.RED);
			jTHour.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Insira data ou hora corretamente", "Erro!", JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}
	
	public static void setSelectedBoatByID(JTextField jt, JComboBox<Boat> cb){
		jt.setBackground(Color.WHITE);
		boolean boatNotFound = true;
		int id = GUITransUtils.getIntFromJText(jt);
		for(int i = 0; i < cb.getItemCount(); i++){
			if(cb.getItemAt(i).id() == id) {
				cb.setSelectedIndex(i);
				boatNotFound = false;
			}
		}
		if(boatNotFound){
			jt.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Barco não encontrado!","ERRO!",JOptionPane.ERROR_MESSAGE);				
		}
	}
	
	public static void setSelectedSellerByID(JTextField jt, JComboBox<Seller> cb){
		jt.setBackground(Color.WHITE);
		boolean boatNotFound = true;
		int id = GUITransUtils.getIntFromJText(jt);
		for(int i = 0; i < cb.getItemCount(); i++){
			if(cb.getItemAt(i).id() == id) {
				cb.setSelectedIndex(i);
				boatNotFound = false;
			}
		}
		if(boatNotFound){
			jt.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vendedor não encontrado!","ERRO!",JOptionPane.ERROR_MESSAGE);				
		}
	}
}
