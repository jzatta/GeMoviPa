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

public class ReversalTourFrame extends JFrame {
	private JTable table;
	private List<Tour> toursEnvolved;
	private SQLDatabase dataBaseConnection;
	public ReversalTourFrame(SQLDatabase dataBaseConnection, Timestamp timeFrom, Timestamp timeTo) {
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
		toursEnvolved = dataBaseConnection.loadTours(timeFrom, timeTo, null, null);
		String columnNames[] = new String[]{"ID","Data sa\u00EDda", "Nome barco", "Pass. Inteiros", "Pass. Free"};
		Object rowElements[][] = new Object[toursEnvolved.size()][columnNames.length];
		for(int i = 0; i < toursEnvolved.size(); i++){
				Tour t = toursEnvolved.get(i);
				rowElements[i][0] = t.id();
				rowElements[i][1] = ConversionUtils.getDatePortuguese(t.departureTime(), true);
				rowElements[i][2] = t.boatName();
				rowElements[i][3] = t.fullPassengers();
				rowElements[i][4] = t.freePassengers();
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
		int[] selectedRows = table.getSelectedRows();
		
		for(int i = 0; i < selectedRows.length; i++){
			int id = (Integer)table.getModel().getValueAt(selectedRows[i],0);
			for(Tour t : toursEnvolved){
				if(t.id() == id) {
					dataBaseConnection.deleteTour(t);
					break;
				}
			}			
		}
		for(int i : selectedRows) ((DefaultTableModel)table.getModel()).removeRow(i);
		JOptionPane.showMessageDialog(null, "Registros removidos!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
	}
}
