package de.hska.lat.robot.dataGraph;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.valueViewer.ValueGraphNotifier;


public class DataGraphStatusBar extends JToolBar implements ValueGraphNotifier
{

	private static final long serialVersionUID = 1318266789894418735L;

	protected int time;
	protected float value;
	protected boolean isOnFocus;
	
	protected JLabel lTime;
	protected JLabel lValue;
	
	public DataGraphStatusBar()
	{
		super();
//		this.setName(device.getName()+STATUS_TEXT);
		this.setBorder(new LineBorder(Color.black));
		buildView();
	}
	
	protected void buildView() {
		this.setLayout(new FlowLayout());
		
		this.lTime = new JLabel();
		this.lValue = new JLabel();
		
		this.add(new JLabel("Time:"));
		this.add(lTime);
		this.add(new JLabel("Value:")); 
		this.add(lValue);
		
		this.calculate();
	}
	
	public void update() {
		this.revalidate();
		this.repaint();
	}

	protected void calculate() {
		if (isOnFocus)
		{
			this.lTime.setText("" + time);
			this.lValue.setText(FloatValue.toFormatedFractionString(value, 3));	
		}
		else
		{
			this.lTime.setText("-");
			this.lValue.setText("-");
		}
	}
	

	@Override
	public void cursorMoved(int time, float value)
	{
		this.time = time;
		this.value = value;
		this.calculate();
		this.update();
	}

	@Override
	public void onFocus()
	{
		this.isOnFocus = true;
		this.calculate();
		this.update();
	}

	@Override
	public void lostFocus()
	{
		this.isOnFocus = false;
		this.calculate();
		this.update();
	}
	
	
}
