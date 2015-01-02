package srcGUI;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.List;

import Background.Boat;
import Background.Enterprise;
import Database.SQLDatabase;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class EnterpriseDialog extends JDialog {
	private JTextField tFEnterpriseID;
	JComboBox<Enterprise> cBEnterpriseName;
	
	private String selectedEnterpriseName;
	public EnterpriseDialog(Frame window, boolean modal,SQLDatabase dataBaseConnection) {
		super(window,modal);
		JPanel mainPanel = new JPanel();
		setSize(new Dimension(300, 200));
		setLocation(window.getLocation().x + (window.getSize().width - this.getSize().width)/2,
				window.getLocation().y + (window.getSize().height - this.getSize().height)/2);
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel jPInfo = new JPanel();
		mainPanel.add(jPInfo, BorderLayout.CENTER);
		
		tFEnterpriseID = new JTextField();
		tFEnterpriseID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUITransUtils.setSelectedEnterpriseByID(tFEnterpriseID, cBEnterpriseName);
			}
		});
		jPInfo.add(tFEnterpriseID);
		tFEnterpriseID.setColumns(10);
		
		cBEnterpriseName = new JComboBox<Enterprise>();
		cBEnterpriseName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateFields();
			}
		});
		jPInfo.add(cBEnterpriseName);
		
		JPanel jPButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jPButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(jPButtons, BorderLayout.SOUTH);
		
		JButton button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPButtons.add(button);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processOKButton();
			}
		});
		jPButtons.add(btnOk);
		
		List<Enterprise> enterprises = dataBaseConnection.loadEnterprises(null);
		for(Enterprise e : enterprises) cBEnterpriseName.addItem(e);
		
	}
	
	void updateFields(){
		Enterprise selectedEnterprise = (Enterprise)cBEnterpriseName.getSelectedItem();
		tFEnterpriseID.setText(String.valueOf(selectedEnterprise.id()));
	}
	
	void processOKButton(){
		try{
			selectedEnterpriseName = cBEnterpriseName.getSelectedItem().toString();	
			dispose();
		}catch(IllegalArgumentException e){
		}
	}
	
	String getEnterpriseName(){
		return this.selectedEnterpriseName;
	}
}
