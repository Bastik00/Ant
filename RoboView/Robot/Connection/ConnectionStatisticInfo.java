package de.hska.lat.robot.connection.view;



import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.hska.lat.comm.ComStatistic;
import de.hska.lat.robot.connection.Connection;


public class ConnectionStatisticInfo extends JPanel
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2876071584664121445L;

	private static final String IO_TEXT = "I/O";
	
	private static final String TRANSMITTED_STRING = "transmited";
	private static final String RECEIVED_STRING =   "recived";
	private static final String INVALID_STRING = "invalid";
	private static final String LOST_STRING =   "lost";	
		
	
	private JLabel received;
	private JLabel transmitted;
	private JLabel invalid;
	private JLabel lost;

	
	
	
	
public ConnectionStatisticInfo()
{
	super();
	this.setLayout(null);
	this.setSize(200,200);
	
	this.setBorder(new TitledBorder(IO_TEXT));

	buildPanel();

	
//	update();
	
}
	


public void update(Connection connection)
{

	if (connection!=null)
	{
		ComStatistic statistic = connection.getStatistic();
		if (statistic != null)
		{
			this.received.setText(String.valueOf(statistic.getRecivedPacketsCount()));
			this.transmitted.setText(String.valueOf(statistic.getSendPacketsCount()));
			this.invalid.setText(String.valueOf(statistic.getErrorPacketsCount()));
			this.lost.setText(String.valueOf(statistic.getDroppedPacketsCount()));
		}
	}
}



private void buildPanel()
{

	
	this.received= new JLabel();
	this.received.setBounds(5,10,90,40);
	this.received.setBorder(new TitledBorder(RECEIVED_STRING));
	this.add(this.received);
	
	this.transmitted= new JLabel();
	this.transmitted.setBounds(105,10,90,40);
	this.transmitted.setBorder(new TitledBorder(TRANSMITTED_STRING));
	this.add(this.transmitted);
	
	this.invalid= new JLabel();
	this.invalid.setBounds(5,50,90,40);
	this.invalid.setBorder(new TitledBorder(INVALID_STRING));
	this.add(this.invalid);
	
	this.lost= new JLabel();
	this.lost.setBounds(105,50,90,40);
	this.lost.setBorder(new TitledBorder(LOST_STRING));
	this.add(this.lost);
	

}




}
