package srcGUI;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Database.SQLDatabase;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame {
	private SQLDatabase dataBaseConnection;
	
	public MainFrame() {
		
		dataBaseConnection = new SQLDatabase("127.0.0.1","root","root");
		
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
				JOptionPane.showMessageDialog(null, "Não implementado ainda");
			}
		});
		mnRecebidos.add(mntmNewMenuItem_1);
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
	
	void launchTableReceived(){
		RecebidosMesa recebidos = new RecebidosMesa(dataBaseConnection);
		recebidos.setLocation(getLocation());
		recebidos.setVisible(true);
		
	}

}
