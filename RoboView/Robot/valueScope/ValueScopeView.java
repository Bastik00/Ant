package de.hska.lat.robot.valueScope;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.value.ComponentValue;

public class ValueScopeView extends DisplayFrame implements ValueScopeScreenControl
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7063559711700581033L;

	
	protected ArrayList<ComponentValue<?>> robotValueList = new ArrayList<ComponentValue<?>>();

	protected ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	

	protected ValueScopePanel componentPanel; 
	public ValueScopeView()
	{
		super("scope", true, true, false, false);
	
		
		
		
		this.setSize(250,160);
		this.add(new ValueScopeToolbar(this),BorderLayout.NORTH);
		
		
		
		this.componentPanel=new ValueScopePanel();

		this.add(this.componentPanel,BorderLayout.CENTER);
		
		
		this.show();
		
	//	this.timeScalePanel = new TimeScalePanel(this.screenData,this);
		
		
	//	this.add(this.timeScalePanel);
		
	}

	
	
public boolean setRobot(AbstractRobot<?,?,?> robot)
{
	System.out.println("setEmulator(RobotEmulator emulator)");
	
	

	this.robotValueList.addAll(robot.getInputValues());
	this.robotValueList.addAll(robot.getOutputValues());

	
	for (ComponentValue<?> value : robotValueList)
	{
	//	addDestinationView(value);
		System.out.println(" name : "+value.getName()+"       type "+value.getTypeName());
	}

	return(true);
	//this.rearange();
}
	
	
	
	@Override
	public void moved(int delta)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeScaleChanged(int delta)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sizeChanged()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeValue(ComponentValue<?> value)
	{
		// TODO Auto-generated method stub
		
	}

	
	

@Override
public void addValue()
{
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();

	if (this.values.size()==0)
	{
		for ( ComponentValue<?> value  : this.robotValueList)
		{
			values.add(value);
		}
	}
	else
	{
		for ( ComponentValue<?> value  : this.robotValueList)
		{
			if (value.getClass()==this.values.get(0).getClass())
			{
				values.add(value);
			}

		}
		
	}

	
	ComponentValue<?> value = (ComponentValue<?>)JOptionPane.showInputDialog(
	                    this,
	                    "chose value",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null, // icon
	                    values.toArray(),
	                    "");

	
	if (value!=null)
	{
		this.values.add(value);
	}

}



@Override
public void start()
{
	// TODO Auto-generated method stub
	
}



@Override
public void stop()
{
	// TODO Auto-generated method stub
	
}

	
	
}
