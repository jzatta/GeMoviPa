package srcGUI;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JLabel;

public class ProgressDialog extends JDialog {
	public ProgressDialog(Frame window,String text) {
		super(window,true);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		setSize(new Dimension(350,150));
		setLocation(window.getLocation().x + (window.getSize().width - this.getSize().width)/2,
				window.getLocation().y + (window.getSize().height - this.getSize().height)/2);
		getContentPane().add(progressBar, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel(text);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
	}
	
	public void execute(Runnable task){
		new Thread(task).start();
		this.setVisible(true);
	}

}
