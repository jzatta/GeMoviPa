package srcGUI;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Background.CalculatorDefault;
import Database.SQLDatabase;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JLabel;


public class MainFrame extends JFrame {
	private SQLDatabase dataBaseConnection;
	
	public MainFrame() {
		
		dataBaseConnection = new SQLDatabase("10.42.0.54","root","root");
		
		try{
			dataBaseConnection.createDatabaseIfNoExist();			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		new CalculatorDefault().calculateApportionment(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setSize(new Dimension(600,400));
		
		JMenu mnRecebidos = new JMenu("Recebidos");
		menuBar.add(mnRecebidos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Recebidos Mesa");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchTableReceived();
			}
		});
		mnRecebidos.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cml Vendas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchCommissionWindow();
			}
		});
		mnRecebidos.add(mntmNewMenuItem_1);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem menuItem = new JMenuItem("Vendedores");
		mnCadastro.add(menuItem);
		
		JMenuItem mntmEmpresa = new JMenuItem("Empresa");
		mnCadastro.add(mntmEmpresa);
		
		JMenuItem mntmBarco = new JMenuItem("Barco");
		mnCadastro.add(mntmBarco);
		
		JLabel lbLog = new JLabel("");
		getContentPane().add(lbLog, BorderLayout.SOUTH);
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
	
	void launchTableReceived(){
		try{
			TableReceived recebidos = new TableReceived(dataBaseConnection);
			recebidos.setLocation(getLocation());
			recebidos.setVisible(true);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Problemas na conexão com o banco de dados", "ERRO!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	void launchCommissionWindow(){
		try{
			CommissionWindow commission = new CommissionWindow(dataBaseConnection);
			commission.setLocation(getLocation());
			commission.setVisible(true);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Problemas na conexão com o banco de dados", "ERRO!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
