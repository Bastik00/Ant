package de.hska.lat.robot.ui.temperatureSpinner;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import de.hska.lat.robot.value.FloatValue;

public class TemperatureModel  extends SpinnerNumberModel
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private int index;
private boolean change;

public TemperatureModel()
{
	this.index=0;
}



public boolean hasChanged() 
{
	if (change)
	{
		change=false;
		return(true);
	}	
	
	return false;
}








public String outputText()
{
	String text;
	float temperature;
	
	
	temperature = index;
	temperature/= 100;
	
//	position = index;
	//position/=200;
	//position = Radiant.convertRadiantToDegree(position);
	text=FloatValue.toFormatedFractionString(temperature, 2)+"K";

	return(text);	
}


public void setIndex(int index)
{
	this.index = index;
	this.notifyListeners();
}


public Object getValue()
{
	return(outputText());

}


@Override
public Object getNextValue()
{
	if (index<50000)
	{
		index++;
		change=true;
	}
	
	return(this.outputText());
}



@Override
public Object getPreviousValue() 
{
	if (index>0)
	{
		index--;
		change=true;
	}
	
	return(this.outputText());
}


@Override
public void setValue(Object arg0) 
{
	notifyListeners();
}
	
	
private void notifyListeners()
{
	
	ChangeListener [] changeListener;
	int enumerator;


	changeListener=this.getChangeListeners();
	for (enumerator =0;enumerator < changeListener.length;enumerator++)
	{
		changeListener[enumerator].stateChanged(new ChangeEvent(this));
	}


}


public int getIndex()
{
	return(this.index);
}

}