package de.hska.lat.robot.connection.lan.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


import de.hska.lat.comm.remote.RemoteData;
import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.connection.lan.LanMasterConnection;


public class LanMasterConnectionPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PORT = "1666";
	public static final String DEFAULT_HOST = "localhost";
	private JTextField inputPort;
	private JTextField inputHost;
	private JButton connect;
	private DefaultListModel<Connection> connectedClients = new DefaultListModel<Connection>();
	private AbstractRobot<?,?,?> robot;
	private String localHost;
	private LanMasterConnection masterConnection;

	public LanMasterConnectionPanel() {
		initComponents();
		masterConnection = LanMasterConnection.getInstance();

		masterConnection.addConnectionListener(new Listener() {
			@Override
			public void connected(Connection connection) {

				String connectionName = connection.getID() + ":"
						+ connection.getRemoteAddressTCP().getHostName();

				connection.setName("• "+connectionName);
				connectedClients.addElement(connection);

				System.out.println("Client connected " + connectionName);
			}

			@Override
			public void disconnected(Connection connection) {
				connectedClients.removeElement(connection);
			}

			@Override
			public void received(Connection connection, Object reveivedObject) {
				if (reveivedObject instanceof RemoteDataPacket && robot != null) {
					RemoteDataPacket dataPacket = (RemoteDataPacket) reveivedObject;
					robot.receive(dataPacket);
					System.out.println("Master received RDP: " + dataPacket);
				} else if(reveivedObject instanceof RemoteData){
					RemoteData remoteData = (RemoteData) reveivedObject;
					masterConnection.transmitt(remoteData);
					System.out.println("Master received RD: " + remoteData);
				}else{
					System.out.println("Master received some object: " + reveivedObject);
					
				}

			}
		});
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel inputGrid = new JPanel(new GridLayout(5, 1));
		add(inputGrid, BorderLayout.NORTH);
		inputGrid.add(new JLabel("Host"));
		inputHost = new JTextField();
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			localHost = DEFAULT_HOST;
		}
		inputHost.setText(localHost);
		inputHost.setEditable(false);
		inputGrid.add(inputHost);
		inputGrid.add(new JLabel("Port"));
		inputPort = new JTextField(5);
		inputPort.setText("1666");
		inputGrid.add(inputPort);
		inputGrid.add(new JLabel("Connected Clients"));

		JList<Connection> clients = new JList<Connection>(connectedClients);

		add(new JScrollPane(clients));

		connect = new JButton("Start Server");
		connect.addActionListener(this);
		add(connect, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		startConnection();
	}

	public void setRobot(AbstractRobot<?,?,?> robot) {
		this.robot = robot;
	}

	public void startConnection() {

		int port = Integer.parseInt(inputPort.getText());

		if (masterConnection.isStarted()) {
			masterConnection.stop();
			connect.setText("Start Server");
		} else if (masterConnection.start(port)) {
			System.out.println("Started Server on Host+ " + localHost
					+ " on Port " + port);
			connect.setText("Stop Server");
			robot.connect(masterConnection);
		} else {
			System.out.println("Could't start server");
		}
	}

}
