package de.hska.lat.robot.connection.lan.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import de.hska.lat.comm.remote.RemoteDataPacket;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.connection.lan.LanSlaveConnection;


public class LanSlaveConnectionPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PORT = "1666";
	public static final String DEFAULT_HOST = "localhost";
	private JTextField inputPort;
	private JTextField inputHost;
	private JButton connect, discoverServer;
	private LanSlaveConnection slave;
	private AbstractRobot<?, ?, ?>  emulator;

	public LanSlaveConnectionPanel() {
		slave = new LanSlaveConnection();
		slave.addListener(new Listener(){
			@Override
			public void disconnected(Connection connection) {
				connect.setText("Connect");
				slave.disconnect();
				System.out.println("Disconnected from host");
				
			}
			
			@Override
			public void received(Connection connection, Object receivedObject) {
				if(receivedObject instanceof RemoteDataPacket){
					RemoteDataPacket dataPacket = (RemoteDataPacket) receivedObject;
					System.out.println("Client Recieved RDP: "+receivedObject);
					emulator.receive(dataPacket);

				}else{
					System.out.println("Client Recieved Object: "+receivedObject);
				}
			}
		});
		initComponents();

	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel inputGrid = new JPanel(new GridLayout(4, 1));
		add(inputGrid, BorderLayout.NORTH);
		inputGrid.add(new JLabel("Host"));
		inputHost = new JTextField();
		inputHost.setText(DEFAULT_HOST);
		inputGrid.add(inputHost);
		inputGrid.add(new JLabel("Port"));
		inputPort = new JTextField(5);
		inputPort.setText("1666");
		inputGrid.add(inputPort);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		connect = new JButton("Connect");
		connect.addActionListener(this);
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		buttonPanel.add(connect);

		discoverServer = new JButton("Server Lookup");
		buttonPanel.add(discoverServer);
		discoverServer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int port = Integer.parseInt(inputPort.getText());
		if (e.getSource().equals(connect)) {
			if (slave.isConnected()) {
				slave.disconnect();
				connect.setText("Connect");
			} else {
				String host = inputHost.getText();
				slave.start();
				if (slave.connect(host, port)) {
					emulator.connect(slave);
					connect.setText("Disconnect");
					System.out.println("Connected to Host " + host
							+ " on Port " + port);
					
				} else {
					System.out.println("Could't connect to Host " + host
							+ " on Port " + port);

				}
			}
		} else if (e.getSource().equals(discoverServer)) {
			String foundServerHost = slave.discoverServer(port);
			inputHost.setText(foundServerHost);
			System.out.println("Found server: " + foundServerHost );
		}
	}
	
	public void setEmulator(AbstractRobot<?, ?, ?>  emulator) {
		this.emulator = emulator;
		emulator.connect(slave);
//		emulator.setConnection(slave);
//		emulator.setTransmitter();
	}

}
