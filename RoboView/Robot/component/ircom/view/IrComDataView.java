package de.hska.lat.robot.component.ircom.view;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;



import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import de.hska.lat.robot.component.actor.generic.rc5Transciver.Rc5Transciver;
import de.hska.lat.robot.component.view.ComponentView;

public class IrComDataView extends ComponentView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5532036488174177945L;
	private Rc5Transciver transciver;
	private JComboBox<String> adressToSend;
	private JComboBox<String> adressToSendData;
	private JComboBox<String> cmdToSend;
	private JButton send;
	private JTextField data;
	private JLabel warning ;
	
	private JTabbedPane tabs;
	
	public IrComDataView(Rc5Transciver transciver, boolean embedded) {
		super(transciver.getComponentName(), embedded);
		this.transciver = transciver;
		createView();
	}
	
	void createView() {
		
		super.buildView();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBounds(10, 10, 800, 100);
		//this.setSize(600, 100);
		Insets insets = this.getBorder().getBorderInsets(this);
		
		// Defines the adresses to which messages can be sent
		String[] adress = {"00: AdressDummy 0x00", "01: AdressDummy 0x01", "02: AdressDummy 0x02", "03: AdressDummy 0x03", 
				"04: AdressDummy 0x04", "05: AdressDummy 0x05", "06: AdressDummy 0x06", "07: AdressDummy 0x07", 
				"08: AdressDummy 0x08", "09: AdressDummy 0x09", "10: AdressDummy 0x0A", "11: AdressDummy 0x0B",
				"12: AdressDummy 0x0C", "13: AdressDummy 0x0D", "14: AdressDummy 0x0E", "15: AdressDummy 0x0F", 
				"16: AdressDummy 0x10", "17: AdressDummy 0x11", "18: AdressDummy 0x12", "19: AdressDummy 0x13", 
				"20: AdressDummy 0x14", "21: AdressDummy 0x15", "22: AdressDummy 0x16", "23: AdressDummy 0x17",
				"24: AdressDummy 0x18", "25: AdressDummy 0x19", "26: AdressDummy 0x1A", "27: AdressDummy 0x1B",
				"28: AdressDummy 0x1C", "29: AdressDummy 0x1D", "30: AdressDummy 0x1E", "31: AdressDummy 0x1F"};
		
		String[] cmd = {"00: CmdDummy 0x00", "01: CmdDummy 0x01", "02: CmdDummy 0x02", "03: CmdDummy 0x03", 
				"04: CmdDummy 0x04", "05: CmdDummy 0x05", "06: CmdDummy 0x06", "07: CmdDummy 0x07", 
				"08: CmdDummy 0x08", "09: CmdDummy 0x09", "10: CmdDummy 0x0A", "11: CmdDummy 0x0B",
				"12: CmdDummy 0x0C", "13: CmdDummy 0x0D", "14: CmdDummy 0x0E", "15: CmdDummy 0x0F", 
				"16: CmdDummy 0x10", "17: CmdDummy 0x11", "18: CmdDummy 0x12", "19: CmdDummy 0x13", 
				"20: CmdDummy 0x14", "21: CmdDummy 0x15", "22: CmdDummy 0x16", "23: CmdDummy 0x17",
				"24: CmdDummy 0x18", "25: CmdDummy 0x19", "26: CmdDummy 0x1A", "27: CmdDummy 0x1B",
				"28: CmdDummy 0x1C", "29: CmdDummy 0x1D", "30: CmdDummy 0x1E", "31: CmdDummy 0x1F",
				"32: CmdDummy 0x20", "33: CmdDummy 0x21", "34: CmdDummy 0x22", "35: CmdDummy 0x23",
				"36: CmdDummy 0x24", "37: CmdDummy 0x25", "38: CmdDummy 0x26", "39: CmdDummy 0x27",
				"40: CmdDummy 0x28", "41: CmdDummy 0x29", "42: CmdDummy 0x2A", "43: CmdDummy 0x2B",
				"44: CmdDummy 0x2C", "45: CmdDummy 0x2D", "46: CmdDummy 0x2E", "47: CmdDummy 0x2F",
				"48: CmdDummy 0x30", "49: CmdDummy 0x31", "50: CmdDummy 0x32", "51: CmdDummy 0x33",
				"52: CmdDummy 0x34", "53: CmdDummy 0x35", "54: CmdDummy 0x36", "55: CmdDummy 0x37",
				"56: CmdDummy 0x38", "57: CmdDummy 0x39", "58: CmdDummy 0x3A", "59: CmdDummy 0x3B",
				"60: CmdDummy 0x3C", "61: CmdDummy 0x3D", "62: CmdDummy 0x3E", "63: CmdDummy 0x3F"};

		
		adressToSend = new JComboBox<String>(adress);
		
		//adressToSend.setBorder(this.getBorder());
		//adressToSend.setBounds(insets.left + 5, insets.top + 5, 150, 200);
		
		cmdToSend = new JComboBox<String>(cmd);
		
		send = new JButton("Send");
		
		//this.add(adressToSend);
		//this.add(cmdToSend);
		
		JPanel tab1 = new JPanel();
		tab1.add(adressToSend);
		tab1.add(cmdToSend);
		tab1.add(send);
		
		tabs = new JTabbedPane();
		tabs.addTab("RC5", tab1);
		
		JButton sendData = new JButton("Send");
		data = new JTextField(18);
		data.setEditable(true);
		
		adressToSendData = new JComboBox<String>(adress);
		warning = new JLabel("You need to enter a Valid number (Hex, Bin, Dez) and it musst not go over 1 Byte");
		warning.setVisible(false);
		
		JPanel tab2 = new JPanel();
		tab2.setLayout(new BoxLayout(tab2, BoxLayout.Y_AXIS));
		JPanel group = new JPanel();
		
		group.add(adressToSendData);
		group.add(data);
		group.add(sendData);
		group.setAlignmentX(LEFT_ALIGNMENT);
		tab2.add(group);
		tab2.add(warning);
		
		tabs.addTab("Data", tab2);
		
		// Add the Tabs to the main View
		this.add(tabs);
		
		// adds the logger view to the main view
		//this.add(buildLoggerView());
		
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		
		data.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (warning.isVisible()) {
					warning.setVisible(false);
					data.setText(null);
				}
				
			}
		});
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                
                System.out.println("Adress: " + "[Index: " +  adressToSend.getSelectedIndex() + " Description: " + adressToSend.getSelectedItem() + " ]");
                System.out.println("Command: " + "[Index: " + cmdToSend.getSelectedIndex() + " Description: " + cmdToSend.getSelectedItem() + " ]");
                int adressMask = 0x001F;
                int cmdMask = 0x07E0;
                
                int data = 0x0000;
                
                data = (cmdToSend.getSelectedIndex() << 2);
                data |= (adressToSend.getSelectedIndex() << 8);
                transciver.remote_sendCommand(data);
                
            }
        });
		
		sendData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dataToSend = -1;
				
				// check if the input string is a valid hex-number
				if (data.getText().trim().matches("\\A^0x(\\d|[A-F|a-f])+\\Z") && data.getText().trim().length() <= 6) {
					// hex-value found
					dataToSend = Integer.parseInt(data.getText().trim().substring(2), 16);
					System.out.println("Hex value found");
					
				} else if (data.getText().trim().matches("\\A[0|1]+\\Z") && data.getText().trim().length() <= 16) {
					// bin-value found
					dataToSend = Integer.parseInt(data.getText().trim(), 2);
					System.out.println("Bin value found");
					
				} else if (data.getText().trim().matches("\\A\\d+\\Z") && Integer.parseInt(data.getText().trim(), 10) <= 65535) {
					
					// dezimal-value found
					dataToSend = Integer.parseInt(data.getText().trim(), 10);
					System.out.println("Dez value found");
					
				} else {
					// Turn on waring text
					warning.setVisible(true);
				}
				
				System.out.println("Data to send: " + dataToSend);
			}
		});
		
		
		//this.add(send);
	}
	
	private Component buildLoggerView() {
		IrComTableModel tableModel = new IrComTableModel();
		
		JTable table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
		
		scrollPane.setViewportView(table); // makes the table scrollable
		
		return scrollPane;
	}
	
	

}
