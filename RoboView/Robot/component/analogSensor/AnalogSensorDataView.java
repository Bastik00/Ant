package de.hska.lat.robot.component.analogSensor;

import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

import de.hska.lat.robot.component.view.ComponentView;





public class AnalogSensorDataView extends ComponentView 
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String STRING_MIN = "min :";
	private static final String STRING_MAX = "max :";
	
	
	JLabel presure;
	JLabel treshold;
	JProgressBar presureBar;
	JSlider tresholdPoint;
	
	JLabel maxValue;
	JLabel minValue;
	
public AnalogSensorDataView(String name, int range) 
{
	super(name, false);

	buildView(range);
	this.setSize(210, 100);
}



private void buildView(int range)
{
	
	
	JLabel tmpLabel;
	
	tmpLabel= new JLabel(STRING_MIN);
	tmpLabel.setBounds(10, 55, 40, 15);
	this.add(tmpLabel);
	

	minValue = new JLabel();
	minValue.setBounds(50,55,100,15);
	this.add(minValue);
	
	
	tmpLabel= new JLabel(STRING_MAX);
	tmpLabel.setBounds(120, 55, 40, 15);
	this.add(tmpLabel);
	
	maxValue = new JLabel();
	maxValue.setBounds(160,55,100,15);
	this.add(maxValue);
	
	
	
	presure = new JLabel();
	presure.setBounds(5,5,20,20);
	//this.add(presure);
	
	
	presureBar = new JProgressBar(0,range);
	presureBar.setStringPainted(true);
	presureBar.setForeground(Color.GREEN);
	presureBar.setBackground(Color.RED);
	presureBar.setBounds(10,25,100,15);

	this.add(presureBar);

	tresholdPoint = new JSlider(0,range);
	tresholdPoint.setEnabled(false);
	tresholdPoint.setBounds(10,15,100,10);
	tresholdPoint.setPaintTrack(false);
	
	this.add(tresholdPoint);
	
	treshold = new JLabel();
	presure.setBounds(125,10,20,40);
	this.add(treshold);
	
	update();
}




public void update()
{
/*	treshold.setText(String.valueOf(((AnalogSensor) data).getTreshold()));
	presureBar.setString(String.valueOf(((AnalogSensor) data).getValue()));
	presureBar.setValue(((AnalogSensor) data).getValue());
	tresholdPoint.setValue(((AnalogSensor) data).getTreshold());
	*/
}




}
