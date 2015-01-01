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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JLabel;


public class MainFrame extends JFrame {
	private SQLDatabase dataBaseConnection;
	
	public MainFrame() {
		
		File dbAddress;
		FileReader fR;
		StringBuilder address = new StringBuilder();
		int charReaded;
		try {
			dbAddress = new File("dbAddress");
			fR = new FileReader(dbAddress);
			while( (charReaded = fR.read()) > 0) address.append((char)charReaded);
			fR.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("DB address: " + address.toString());
		dataBaseConnection = new SQLDatabase(address.toString(),"root","root");
		
		try{
			dataBaseConnection.createDatabaseIfNoExist();			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
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
		
		JMenu mnEstornos = new JMenu("Estornos");
		menuBar.add(mnEstornos);
		
		JMenuItem mntmPa = new JMenuItem("Mesa");
		mntmPa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processReversalTourMenu();
			}
		});
		mnEstornos.add(mntmPa);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processReversalSalesMenu();
			}
		});
		mnEstornos.add(mntmVendas);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem menuItem = new JMenuItem("Vendedores");
		mnCadastro.add(menuItem);
		
		JMenuItem mntmEmpresa = new JMenuItem("Empresa");
		mnCadastro.add(mntmEmpresa);
		
		JMenuItem mntmBarco = new JMenuItem("Barco");
		mnCadastro.add(mntmBarco);
		
		JMenu mnRegistros = new JMenu("Relatórios");
		menuBar.add(mnRegistros);
		
		JMenuItem mntmRateio = new JMenuItem("Rateio");
		mntmRateio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processApportionmentMenu();
			}
		});
		mnRegistros.add(mntmRateio);
		
		JMenuItem mntmComisses = new JMenuItem("Comissões");
		mntmComisses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processCommissionMenu();
			}
		});
		mnRegistros.add(mntmComisses);
		
		JMenuItem mntmMovimentoGeral = new JMenuItem("Movimento Geral");
		mntmMovimentoGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processMovGeneralMenu();
				
			}
		});
		mnRegistros.add(mntmMovimentoGeral);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lbLog = new JLabel("");
		panel.add(lbLog);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
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
	
	void processApportionmentMenu(){
		TimesampDialog tm = new TimesampDialog(this,true);
		tm.setVisible(true);
		
		final Timestamp timeFrom = tm.getTimestampFrom();
		final Timestamp timeTo = tm.getTimestampTo();
		
		if(timeFrom != null && timeTo != null){
			final ProgressDialog p = new ProgressDialog(this, "Calculando Rateio!");
			p.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					new CalculatorDefault().calculateApportionment(dataBaseConnection, timeFrom, timeTo);
					p.dispose();
				}
			});
			JOptionPane.showMessageDialog(null, "Verifique arquivo ResultadoRateio.pdf");
		}
		
	}
	
	void processCommissionMenu(){
		TimesampDialog tm = new TimesampDialog(this,true);
		tm.setVisible(true);
		
		final Timestamp timeFrom = tm.getTimestampFrom();
		final Timestamp timeTo = tm.getTimestampTo();
		
		if(timeFrom != null && timeTo != null){
			final ProgressDialog p = new ProgressDialog(this, "Calculando comissões!");
			p.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					new CalculatorDefault().calculateCommission(dataBaseConnection, timeFrom, timeTo);
					p.dispose();
				}
			});				
			JOptionPane.showMessageDialog(null, "Verifique arquivo ResultadoComissões.csv");
		}
		
	}
	
	void processMovGeneralMenu(){
		TimesampDialog tm = new TimesampDialog(this,true);
		tm.setVisible(true);
		
		final Timestamp timeFrom = tm.getTimestampFrom();
		final Timestamp timeTo = tm.getTimestampTo();
		
		if(timeFrom != null && timeTo != null){
			final ProgressDialog p = new ProgressDialog(this, "Calculando movimento geral!");
			p.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					new CalculatorDefault().calculateTotalMov(dataBaseConnection, timeFrom, timeTo);
					p.dispose();
				}
			});			
			JOptionPane.showMessageDialog(null, "Verifique arquivo ResultadoMovimentoGeral.pdf");
		}
	}
	
	void processReversalTourMenu(){
		TimesampDialog tm = new TimesampDialog(this,true);
		tm.setVisible(true);
		
		Timestamp timeFrom = tm.getTimestampFrom();
		Timestamp timeTo = tm.getTimestampTo();
		if(timeFrom != null && timeTo != null){
			ReversalTourFrame reversalTour = new ReversalTourFrame(dataBaseConnection,timeFrom,timeTo);
			reversalTour.setLocation(getLocation());
			reversalTour.setVisible(true);
		}
	}
	
	void processReversalSalesMenu(){
		TimesampDialog tm = new TimesampDialog(this,true);
		tm.setVisible(true);
		
		Timestamp timeFrom = tm.getTimestampFrom();
		Timestamp timeTo = tm.getTimestampTo();
		if(timeFrom != null && timeTo != null){
			ReversalSaleFrame reversalSale = new ReversalSaleFrame(dataBaseConnection,timeFrom,timeTo);
			reversalSale.setLocation(getLocation());
			reversalSale.setVisible(true);
		}
	}

}
