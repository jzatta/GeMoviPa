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

import Background.Enterprise;
import Background.Seller;
import Database.SQLDatabase;

public class CadastreSeller extends JFrame {
	private JTable table;
	private List<Seller> sellers;
	private SQLDatabase dataBaseConnection;
	
	public CadastreSeller(SQLDatabase dataBaseConnection){
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
		sellers = dataBaseConnection.loadSellers(null,null);
		String columnNames[] = new String[]{"ID","Nome Vendedor", "Nome Empresa"};
		Object rowElements[][] = new Object[sellers.size()][columnNames.length];
		for(int i = 0; i < sellers.size(); i++){
				Seller s = sellers.get(i);
				rowElements[i][0] = String.valueOf(s.id());
				rowElements[i][1] = s.name();
				rowElements[i][2] = s.enterpriseName();
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
			List<Seller> afterSellers = new ArrayList<Seller>();
			List<String> enterpriseNames = new ArrayList<String>();
			for(Enterprise e : dataBaseConnection.loadEnterprises(null)) enterpriseNames.add(e.name());
			for(int i = 0; i < table.getRowCount(); i++){
				TableModel model = table.getModel();
				int id = Integer.parseInt((String)model.getValueAt(i,0));
				String boatName = (String)model.getValueAt(i, 1);
				String enterpriseName = (String)model.getValueAt(i, 2);
				Seller s = new Seller(id,boatName,enterpriseName);
				afterSellers.add(s);
			}			
			for(int i = 0; i < sellers.size(); i++){ //verify editions
				Seller beforeSeller = sellers.get(i);
				Seller afterSeller = afterSellers.get(i);
				if(!beforeSeller.equals(afterSeller)){
					if(!enterpriseNames.contains(afterSeller.enterpriseName())){
						throw new Exception("Empresa não encontrada na edição, ID vendedor: " + afterSeller.id());
					}
					dataBaseConnection.deleteSeller(beforeSeller);
					dataBaseConnection.storeSeller(afterSeller);
					System.out.println("Edited bef: " + beforeSeller.name() +
									   " After: " + afterSeller.name());
				}
			}
			if(sellers.size() < afterSellers.size()){ //verify addtions
				for(int i = 0; i < (afterSellers.size() - sellers.size()); i++){
					Seller afterSeller = afterSellers.get(i + sellers.size());
					if(!enterpriseNames.contains(afterSeller.enterpriseName())){
						throw new Exception("Empresa não encontrada na inserção, ID vendedor: " + afterSeller.id());
					}
					dataBaseConnection.storeSeller(afterSeller);
				}					
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Problema nos dados","ERRO!",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			if(e.getMessage().contains("Empresa não encontrada")){
				JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO!",JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Erro fatal","ERRO!",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	void processDelButton(){
		List<Seller> selectedSellers = new ArrayList<Seller>();
		int[] selectedRows = table.getSelectedRows();
		
		for(int i = 0; i < selectedRows.length; i++){
			String idCellContent = (String)table.getModel().getValueAt(selectedRows[i],0);
			if(idCellContent == null) continue;
			int id = Integer.parseInt(idCellContent);
			for(Seller s : sellers){
				if(s.id() == id) {
					selectedSellers.add(s);
					break;
				}
			}
		}
		for(Seller s: selectedSellers) {
			dataBaseConnection.deleteSeller(s);
			sellers.remove(s);
		}
		for(int i : selectedRows) ((DefaultTableModel)table.getModel()).removeRow(i);
		JOptionPane.showMessageDialog(null, "Registros removidos!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
	}
	
	void processNewButton(){
		((DefaultTableModel)table.getModel()).addRow(new Object[0]);
	}
}
