package de.hska.lat.robot.component.sensor.adjdS311.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import de.hska.lat.robot.component.sensor.adjdS311.AdjdS311ChannelSetings;



public class AdjdS311ChanelSettingsView extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2919509328128089011L;
	protected SpinnerNumberModel capacitatorsModel;
	protected JSpinner capacitators;
	
	protected SpinnerNumberModel integrationTimeModell;
	protected JSpinner integrationTime;
	
	
	protected SpinnerNumberModel offsetModell;
	protected JSpinner offset;
	
	protected static final String CAPACITATORS_TEXT = "capacitators";
	protected static final String INTEGRATION_TIME_TEXT = "integration time";
	protected static final String OFFSET_TEXT = "offset";
	
	
public AdjdS311ChanelSettingsView(String name)
{
	
	
	buildPannel(name);
}
	

protected void buildPannel(String name)
{
	JLabel tmpLabel;
	
	this.setLayout(null);
	

	
	
	this.setSize(180,110);
	this.setBorder(new TitledBorder(name));
	
	
	tmpLabel = new JLabel(AdjdS311ChanelSettingsView.CAPACITATORS_TEXT);
	tmpLabel.setBounds(10,20,100,25);
	this.add(tmpLabel);
	
	
	this.capacitatorsModel = new SpinnerNumberModel(15,0,15,1);
	this.capacitators= new JSpinner( this.capacitatorsModel );
	this.capacitators.setBounds(100,20,65,25);
	this.add(this.capacitators);
	
	
	
	tmpLabel = new JLabel(AdjdS311ChanelSettingsView.INTEGRATION_TIME_TEXT);
	tmpLabel.setBounds(10,45,100,25);
	this.add(tmpLabel);
	
	this.integrationTimeModell = new SpinnerNumberModel(0,0,4095,1);
	this.integrationTime= new JSpinner( this.integrationTimeModell );
	this.integrationTime.setBounds(100,45,65,25);
	
	this.add(this.integrationTime);

	
	tmpLabel = new JLabel(AdjdS311ChanelSettingsView.OFFSET_TEXT);
	tmpLabel.setBounds(10,70,100,25);
	this.add(tmpLabel);
	
	this.offsetModell = new SpinnerNumberModel(0,-128,127,1);
	this.offset= new JSpinner( this.offsetModell );
	this.offset.setBounds(100,70,65,25);
	
	this.add(this.offset);

	
}


public AdjdS311ChannelSetings getSettings()
{
	AdjdS311ChannelSetings settings = new AdjdS311ChannelSetings();
	
	settings.setCapacitators(this.capacitatorsModel.getNumber().intValue());
	settings.setIntegrationTime(this.integrationTimeModell.getNumber().intValue());
	settings.setOffset(this.offsetModell.getNumber().intValue());
	
	return(settings);
}

}
