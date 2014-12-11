import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame {
	public MainFrame() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setSize(new Dimension(600,400));
		
		JMenu mnRecebidos = new JMenu("Recebidos");
		menuBar.add(mnRecebidos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Recebidos Mesa");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RecebidosMesa().setVisible(true);
			}
		});
		mnRecebidos.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cml Vendas");
		mnRecebidos.add(mntmNewMenuItem_1);
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

}
