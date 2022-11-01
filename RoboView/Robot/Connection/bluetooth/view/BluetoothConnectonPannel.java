package de.hska.lat.robot.connection.bluetooth.view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


import javax.bluetooth.RemoteDevice;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.connection.bluetooth.BluetoothConnection;
import de.hska.lat.robot.connection.bluetooth.BluetoothListener;



public class BluetoothConnectonPannel extends JPanel implements BluetoothListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7103632913650463528L;

	
	
	BluetoothInterfaceSelector portSelector;
	JButton connector;
	JButton scan;
	
	
	protected AbstractRobot<?,?,?> robot ;
	
	 ArrayList<RemoteDevice> devices = new  ArrayList<RemoteDevice>();
	
public BluetoothConnectonPannel()
{
	
	
	this.setLayout(null);
	buildView();
	this.setPreferredSize(new Dimension(200, 150));
}
	


private void buildView()
{
	
	JLabel tmpLabel;
	
	tmpLabel=new JLabel("port");
	tmpLabel.setBounds(5,20,50,20);
	this.add(tmpLabel);


	portSelector = new BluetoothInterfaceSelector();
	portSelector.setBounds(60,20,100,20);
	portSelector.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub

		}
		
	});
	this.add(portSelector);

	
	
	

	this.connector= new JButton ("Connect");
	this.connector.setBounds(60,50,100,20);
	this.connector.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			BluetoothConnection connection = new BluetoothConnection();
			
			RemoteDevice device;
			
			device = BluetoothConnectonPannel.this.devices.get(portSelector.getSelectedIndex());
			
			connection.setDataPacketLogger(BluetoothConnectonPannel.this.robot.getDataPacketLogger());
			if (connection.connect(device, robot)==true)
			{
				robot.connect(connection);
			}
			else
			{
				try
				{
					JOptionPane.showMessageDialog(null,
						    "unable to connect to device "+device.getFriendlyName(false),
						    "error",
						    JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch ( IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		
	});
	this.add(this.connector);
	
	

	
	this.scan= new JButton ("scan");
	this.scan.setBounds(60,90,100,20);
	this.scan.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			BluetoothConnection.scan(BluetoothConnectonPannel.this.devices,BluetoothConnectonPannel.this);
			
			
	//		SerialConnection connection = new SerialConnection(portSelector.getSelectedPortName());
			
	/*		connection.setDataPacketLogger(SerialConnectonPannel.this.robot.getDataPacketLogger());
			if (connection.connect(SerialConnectonPannel.this.robot)==true)
			{
				robot.connect(connection);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
					    "unable to connect to port "+connection.getPortName(),
					    "error",
					    JOptionPane.ERROR_MESSAGE);

			}*/
		}
		
	});
	this.add(scan);
	
	
	
}



public void setRobot(AbstractRobot<?,?,?> robot)
{
	
	this.robot = robot;
}



@Override
public void scanCompleted()
{
	this.portSelector.setList(BluetoothConnectonPannel.this.devices);
	System.out.println("list set");

}




	
	
}
