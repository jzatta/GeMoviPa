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

public class CadastreEnterprise extends JFrame {
	private JTable table;
	private List<Enterprise> enterprises;
	private SQLDatabase dataBaseConnection;
	
	public CadastreEnterprise(SQLDatabase dataBaseConnection){
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
		enterprises = dataBaseConnection.loadEnterprises(null);
		String columnNames[] = new String[]{"ID","Nome Empresa"};
		Object rowElements[][] = new Object[enterprises.size()][columnNames.length];
		for(int i = 0; i < enterprises.size(); i++){
				Enterprise e = enterprises.get(i);
				rowElements[i][0] = String.valueOf(e.id());
				rowElements[i][1] = e.name();
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
			List<Enterprise> afterEnterprises = new ArrayList<Enterprise>();
			for(int i = 0; i < table.getRowCount(); i++){
				TableModel model = table.getModel();
				int id = Integer.parseInt((String)model.getValueAt(i,0));
				String enterpriseName = (String)model.getValueAt(i, 1);
				Enterprise e = new Enterprise(id,enterpriseName);
				afterEnterprises.add(e);
			}			
			for(int i = 0; i < enterprises.size(); i++){ //verify editions
				Enterprise beforeEnterprise = enterprises.get(i);
				Enterprise afterEnterprise = afterEnterprises.get(i);
				if(!beforeEnterprise.equals(afterEnterprise)){
					dataBaseConnection.deleteEnterprise(beforeEnterprise);
					dataBaseConnection.storeEnterprise(afterEnterprise);
				}
			}
			if(enterprises.size() < afterEnterprises.size()){ //verify addtions
				for(int i = 0; i < (afterEnterprises.size() - enterprises.size()); i++){
					Enterprise afterEnterprise = afterEnterprises.get(i + enterprises.size());
					dataBaseConnection.storeEnterprise(afterEnterprise);
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
		List<Enterprise> selectedEnterprises = new ArrayList<Enterprise>();
		int[] selectedRows = table.getSelectedRows();
		
		for(int i = 0; i < selectedRows.length; i++){
			String idCellContent = (String)table.getModel().getValueAt(selectedRows[i],0);
			if(idCellContent == null) continue;
			int id = Integer.parseInt(idCellContent);
			for(Enterprise e : enterprises){
				if(e.id() == id) {
					selectedEnterprises.add(e);
					break;
				}
			}
		}
		for(Enterprise e: selectedEnterprises) {
			dataBaseConnection.deleteEnterprise(e);
			enterprises.remove(e);
		}
		for(int i : selectedRows) ((DefaultTableModel)table.getModel()).removeRow(i);
		JOptionPane.showMessageDialog(null, "Registros removidos!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
	}
	
	void processNewButton(){
		((DefaultTableModel)table.getModel()).addRow(new Object[0]);
	}
}
