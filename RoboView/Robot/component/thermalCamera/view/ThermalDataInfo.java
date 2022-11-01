package de.hska.lat.robot.component.thermalCamera.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.hska.lat.robot.component.thermalCamera.ThermalFrame;
import de.hska.lat.robot.value.FloatValue;

public class ThermalDataInfo extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3983896418910757223L;

	protected JLabel max;
	protected JLabel min;
	protected JLabel average;
	
	public ThermalDataInfo()
	{

		this.buildPanel();
	}


	protected void buildPanel()
	{
		
		this.setBorder(new TitledBorder("info"));

		this.min = new JLabel("000.00K");
		this.add(this.min);
		
		
		this.max = new JLabel("000.00K");
		this.add(this.max);
		

		

		this.average = new JLabel("\u2205 :"+"000.00K");
		this.add(average);
		
	}


	public void setFrame(ThermalFrame frame)
	{
				
		this.max.setText("max : "+FloatValue.toFormatedFractionString(frame.getMax(), 2));
		this.min.setText("min : "+FloatValue.toFormatedFractionString(frame.getMin(), 2));
		this.average.setText("\u2205 : "+FloatValue.toFormatedFractionString(frame.getAverage(), 2));
	}
		
	
}
