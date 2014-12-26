package srcGUI;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.Timestamp;
import java.text.ParseException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimesampDialog extends JDialog {
	private JFormattedTextField tFFDateFrom;
	private JFormattedTextField tFFHourFrom;
	private JFormattedTextField tFFDateTo;
	private JFormattedTextField tFFHourTo;
	
	private Timestamp timeFrom;
	private Timestamp timeTo;
	public TimesampDialog(Frame window, boolean modal) {
		super(window,modal);
		
		setSize(new Dimension(300, 200));
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JPanel jPInfo = new JPanel();
		mainPanel.add(jPInfo, "2, 2, fill, fill");
		jPInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JLabel lblDataConsultaIni = new JLabel("Data consulta início");
		jPInfo.add(lblDataConsultaIni, "2, 2, left, default");
		
		
		try {
			tFFDateFrom = new JFormattedTextField(new MaskFormatter("##/##/####"));
			tFFDateFrom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tFFHourFrom.requestFocus();
				}
			});
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		jPInfo.add(tFFDateFrom, "4, 2, fill, default");
		
		JLabel lblHoraConsulta = new JLabel("Hora consulta início");
		jPInfo.add(lblHoraConsulta, "2, 4, left, default");
		
		try {
			tFFHourFrom = new JFormattedTextField(new MaskFormatter("##:##"));
			tFFHourFrom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tFFDateTo.requestFocus();
				}
			});
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		jPInfo.add(tFFHourFrom, "4, 4, fill, default");
		
		JLabel lblDataConsultaFim = new JLabel("Data consulta final");
		jPInfo.add(lblDataConsultaFim, "2, 6, left, default");
		
		try {
			tFFDateTo = new JFormattedTextField(new MaskFormatter("##/##/####"));
			tFFDateTo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tFFHourTo.requestFocus();
				}
			});
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		jPInfo.add(tFFDateTo, "4, 6, fill, default");
		
		JLabel lblHoraConsultaFim = new JLabel("Hora consulta final");
		jPInfo.add(lblHoraConsultaFim, "2, 8, left, default");
		
		try {
			tFFHourTo = new JFormattedTextField(new MaskFormatter("##:##"));
			tFFHourTo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					processGenButton();
				}
			});
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		jPInfo.add(tFFHourTo, "4, 8, fill, default");
		
		JPanel jPButtons = new JPanel();
		mainPanel.add(jPButtons, "2, 4, right, fill");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPButtons.add(btnCancelar);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processGenButton();
			}
		});
		jPButtons.add(btnGerar);
	}
	
	void processGenButton(){
		try{
			timeFrom = GUITransUtils.getDepartureTimestamp(tFFHourFrom, tFFDateFrom);
			timeTo = GUITransUtils.getDepartureTimestamp(tFFHourTo, tFFDateTo);			
			dispose();
		}catch(IllegalArgumentException e){
		}
	}
	
	Timestamp getTimestampFrom(){
		return timeFrom;
	}
	
	Timestamp getTimestampTo(){
		return timeTo;
	}

	

}
