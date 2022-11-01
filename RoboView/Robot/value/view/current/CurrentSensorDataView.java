package de.hska.lat.robot.value.view.current;

import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import de.hska.lat.robot.component.currentSensor.CurrentSensor;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;
import de.hska.lat.robot.component.view.SensorDataView;
import de.hska.lat.robot.value.view.ValueView;


public class CurrentSensorDataView extends SensorDataView<CurrentSensor> 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3244786375824253493L;
	
	
	protected static final int width = 160;
	protected static final int height = 85;
	
	private static final String RESET_TOTAL_TEXT	= "Reset Total";
	private static final String RESET_MAX_TEXT	= "Reset Max";
	private static final String CMD_RESET_TOTAL = "cmdResetTotal";
	private static final String CMD_RESET_MAX = "cmdResetMax";
	

private CurrentSensorDataView(CurrentSensor sensor) 
{
	super(sensor);
	
	this.buildView();


}


protected void buildView() 
{
	
	super.buildView();

	ValueView<?> viewActual, viewTotal, viewMax;
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	viewActual = CurrentValueView.createView(this.sensor.getActual(), true , "actual");
	viewTotal = CurrentValueView.createView(this.sensor.getTotal(), true, "total" );
	viewMax = CurrentValueView.createView(this.sensor.getMax(), true, "max" );
		
	viewActual.setLocation(10,insets.top+2);
	viewTotal.setLocation(10,insets.top+29);
	viewMax.setLocation(10,insets.top+56);
	
	this.add(viewActual);
	this.add(viewTotal);
	this.add(viewMax);
	
}

@Override
protected int getViewWidth()
{
	return(CurrentSensorDataView.width);
}

@Override
protected int getViewHeight()
{
	return(CurrentSensorDataView.height);
}

@Override
protected void makePopupMenu()
{
	super.makePopupMenu();

	this.contextMenue.add(new JSeparator());
	this.addMenuItem(this.contextMenue , CurrentSensorDataView.RESET_TOTAL_TEXT, CurrentSensorDataView.CMD_RESET_TOTAL);
	this.addMenuItem(this.contextMenue , CurrentSensorDataView.RESET_MAX_TEXT, CurrentSensorDataView.CMD_RESET_MAX);

}

@Override
public void actionPerformed(ActionEvent actionEvent)
{
	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(CurrentSensorDataView.CMD_RESET_TOTAL))
	{
		this.sensor.remote_resetTotal();
	}	
	else if (cmd.equals(CurrentSensorDataView.CMD_RESET_MAX))
	{
		this.sensor.remote_resetMax();
	}else
	{
		super.actionPerformed(actionEvent);
	}
	
	

}

public static ComponentView createView(CurrentSensor sensor)
{

	if (sensor!=null)
	{
		return(new CurrentSensorDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(CurrentSensor.class.getName()));
	}
}




}
