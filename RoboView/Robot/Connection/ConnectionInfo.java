package de.hska.lat.robot.connection.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.hska.lat.robot.connection.Connection;





public class ConnectionInfo extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7103632913650463528L;

	
	
	
	protected JLabel connectionName;
	protected JLabel connectionPartner;

	
public ConnectionInfo()
{
	
	
	this.setLayout(null);
	buildView();
	
	this.setSize(200, 100);
	this.setBorder(new TitledBorder("connection"));
}
	


private void buildView()
{
	
	
	
	this.connectionName=new JLabel();
	this.connectionName.setBounds(5,10,190,40);
	this.connectionName.setBorder(new TitledBorder("type"));
	this.add(this.connectionName);

	this.connectionPartner=new JLabel("");
	this.connectionPartner.setBounds(5,50,190,40);
	this.connectionPartner.setBorder(new TitledBorder("device"));
	this.add(this.connectionPartner);
	
}



public void update(Connection connection)
{

	if (connection!=null)
	{
		this.connectionName.setText(connection.getConnectionName());	
		this.connectionPartner.setText(connection.getPartnerName());
		
	}
}
	
	
}
