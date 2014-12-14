package srcGUI;

import java.awt.Color;
import java.sql.Timestamp;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
}
