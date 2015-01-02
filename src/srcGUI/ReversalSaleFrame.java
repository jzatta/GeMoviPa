package srcGUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Background.ConversionUtils;
import Background.Sale;
import Background.Tour;
import Database.SQLDatabase;

import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReversalSaleFrame extends JFrame {
	private JTable table;
	private List<Sale> salesEnvolved;
	private SQLDatabase dataBaseConnection;
	public ReversalSaleFrame(SQLDatabase dataBaseConnection, Timestamp timeFrom, Timestamp timeTo) {
		this.dataBaseConnection = dataBaseConnection;
		
		JPanel mainPanel = new JPanel();
		setSize(new Dimension(488, 390));
		setContentPane(mainPanel);
		
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel jPButtons = new JPanel();
		mainPanel.add(jPButtons, BorderLayout.SOUTH);
		FlowLayout fl_jPButtons = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		jPButtons.setLayout(fl_jPButtons);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		jPButtons.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Estornar Seleção");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processReversal();
			}
		});
		jPButtons.add(btnNewButton);
				
		table = new JTable();
		salesEnvolved = dataBaseConnection.loadSales(timeFrom, timeTo, null, null,null,null);
		String columnNames[] = new String[]{"ID","Data sa\u00EDda", "Nome Vendedor","Nome barco", "Qtde pass."};
		Object rowElements[][] = new Object[salesEnvolved.size()][columnNames.length];
		for(int i = 0; i < salesEnvolved.size(); i++){
				Sale s = salesEnvolved.get(i);
				rowElements[i][0] = 0;//s.id();
				rowElements[i][1] = ConversionUtils.getDatePortuguese(s.departureTime(), true);
				rowElements[i][2] = s.sellerName();
				rowElements[i][3] = s.boatName();
				rowElements[i][4] = s.payingPassengers();
		}
		
		table.setModel(new DefaultTableModel(
			rowElements,
			columnNames
		));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);		
	}
	
	void processReversal(){
		List<Sale> selectedSales = new ArrayList<Sale>();
		
		int[] selectedRows = table.getSelectedRows();
		
		for(int i = 0; i < selectedRows.length; i++){
			int id = (Integer)table.getModel().getValueAt(selectedRows[i],0);
			for(Sale s : salesEnvolved){
				/*if(s.id() == id) {
					selectedSales.add(s);
					break;
				}*/
			}
		}
		/*for(Tour t : selectedTours) {
			dataBaseConnection.deleteTour(t);
		}*/
		JOptionPane.showMessageDialog(null, "Registros removidos!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
