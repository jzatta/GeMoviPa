package srcGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Background.Boat;
import Background.ConversionUtils;
import Background.Enterprise;
import Background.Sale;
import Background.Tour;
import Database.SQLDatabase;

public class CadastreBoat extends JFrame{
	private JTable table;
	private List<Boat> boats;
	private SQLDatabase dataBaseConnection;
	
	public CadastreBoat(SQLDatabase dataBaseConnection){
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
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processOKButton();
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processDelButton();
				
			}
		});
		jPButtons.add(btnExcluir);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processNewButton();				
			}
		});
		jPButtons.add(btnNovo);
		jPButtons.add(btnNewButton);
				
		table = new JTable();
		boats = dataBaseConnection.loadBoats(null,null);
		String columnNames[] = new String[]{"ID","Nome Barco", "Nome Empresa","Capacidade", "Preço passeio"};
		Object rowElements[][] = new Object[boats.size()][columnNames.length];
		for(int i = 0; i < boats.size(); i++){
				Boat b = boats.get(i);
				rowElements[i][0] = String.valueOf(b.id());
				rowElements[i][1] = b.name();
				rowElements[i][2] = b.enterpriseName();
				rowElements[i][3] = String.valueOf(b.capacity());
				rowElements[i][4] = String.valueOf(b.tourCost());
		}
		
		table.setModel(new DefaultTableModel(
			rowElements,
			columnNames
		));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);	
	}
	
	void processOKButton(){
		try{
			List<Boat> afterBoats = new ArrayList<Boat>();
			List<String> enterpriseNames = new ArrayList<String>();
			for(Enterprise e : dataBaseConnection.loadEnterprises(null)) enterpriseNames.add(e.name());
			for(int i = 0; i < table.getRowCount(); i++){
				TableModel model = table.getModel();
				int id = Integer.parseInt((String)model.getValueAt(i,0));
				String boatName = (String)model.getValueAt(i, 1);
				String enterpriseName = (String)model.getValueAt(i, 2);
				int capacity = Integer.parseInt((String)model.getValueAt(i, 3));
				double tourCost = Double.parseDouble((String)model.getValueAt(i, 4));
				Boat b = new Boat(id,boatName,enterpriseName,capacity,tourCost);
				afterBoats.add(b);
			}			
			for(int i = 0; i < boats.size(); i++){ //verify editions
				Boat beforeBoat = boats.get(i);
				Boat afterBoat = afterBoats.get(i);
				if(!beforeBoat.equals(afterBoat)){
					if(!enterpriseNames.contains(afterBoat.enterpriseName())){
						JOptionPane.showMessageDialog(null, "Empresa não encontrada","ERRO!",JOptionPane.ERROR_MESSAGE);
						break;
					}
					dataBaseConnection.deleteBoat(beforeBoat);
					dataBaseConnection.storeBoat(afterBoat);
					System.out.println("Edited bef: " + beforeBoat.name() +
									   " After: " + afterBoat.name());
				}
			}
			if(boats.size() < afterBoats.size()){ //verify addtions
				for(int i = 0; i < (afterBoats.size() - boats.size()); i++)
					dataBaseConnection.storeBoat(afterBoats.get(i + boats.size()));
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Problema nos dados","ERRO!",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro fatal","ERRO!",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	void processDelButton(){
		List<Boat> selectedBoats = new ArrayList<Boat>();
		int[] selectedRows = table.getSelectedRows();
		
		for(int i = 0; i < selectedRows.length; i++){
			String idCellContent = (String)table.getModel().getValueAt(selectedRows[i],0);
			if(idCellContent == null) continue;
			int id = Integer.parseInt(idCellContent);
			for(Boat b : boats){
				if(b.id() == id) {
					selectedBoats.add(b);
					break;
				}
			}
		}
		for(Boat b : selectedBoats) {
			dataBaseConnection.deleteBoat(b);
			boats.remove(b);
		}
		for(int i : selectedRows) ((DefaultTableModel)table.getModel()).removeRow(i);
		JOptionPane.showMessageDialog(null, "Registros removidos!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
	}
	
	void processNewButton(){
		((DefaultTableModel)table.getModel()).addRow(new Object[0]);
	}
}
