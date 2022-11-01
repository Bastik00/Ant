package de.hska.lat.robot.component.thermalCamera.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.ui.temperatureSpinner.TemperatureModel;
import de.hska.lat.robot.ui.temperatureSpinner.TemperatureSpinner;

public class ThermalPanelControl extends JPanel implements ChangeListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159419565486493646L;

	
	protected TemperatureModel minTemperature;
	protected TemperatureModel maxTemperature;
	



	
public ThermalPanelControl()
{

	this.buildPanel();
}


protected void buildPanel()
{
	
	this.setBorder(new TitledBorder("controll"));
	
	
	this.minTemperature = new TemperatureModel();
	this.maxTemperature = new TemperatureModel();
	
	this.minTemperature.addChangeListener(this);
	this.maxTemperature.addChangeListener(this);
	
	
/*	
	JSpinner tmpSpinner;
	
	tmpSpinner = new JSpinner(this.minTemperature);
	JTextField editor = new JTextField("test");
	//tmpSpinner.setBounds(10,10,100,40);
	tmpSpinner.setEditor(editor );
	//tmpSpinner.setSize(new Dimension(100,40));
	tmpSpinner.setBounds(10,10,100,40);
	this.add(tmpSpinner);
*/
	TemperatureSpinner ts;

	
	ts = new TemperatureSpinner();
	//ts.setBounds(150,10,100,40);
//	ts.setSize(new Dimension(100,25));
	ts.setPreferredSize(new Dimension(100,25));
	this.add(ts);
	
}



@Override
public void stateChanged(ChangeEvent event)
{
	
	Object source = event.getSource();
	

	if (source == this.minTemperature)
	{
		System.out.println("min");
	}
	else if (source == this.maxTemperature)
	{
		System.out.println("max");
	}
	

}

}