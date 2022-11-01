package de.hska.lat.robot.dataGraph;


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;




public class DataGraphTimeResolution extends JPanel 
{



	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8606243016692713732L;

	
	protected static final String RESOLUTION_TEXT = "res";
	
	
	//protected DataGraphScreenControl control;
	
	protected DataGraphTimeScaleSettings timeScaleSettings;



	protected JSpinner resolutionSpinner;
	
public DataGraphTimeResolution(DataGraphTimeScaleSettings timeScaleSettings)
{
	this.timeScaleSettings = timeScaleSettings;

	this.setLayout(new FlowLayout());
	this.buildPanel();
}

protected void buildPanel()
{
	
	JLabel tmpLabel;
	tmpLabel = new JLabel(DataGraphTimeResolution.RESOLUTION_TEXT);
	this.add(tmpLabel);
	
	this.resolutionSpinner = new  JSpinner () ;
	this.resolutionSpinner.setPreferredSize(new Dimension(80,25));
	this.add(this.resolutionSpinner);
}






	
	
}
