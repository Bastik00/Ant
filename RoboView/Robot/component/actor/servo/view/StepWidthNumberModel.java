package de.hska.lat.robot.component.servo.view;

import javax.swing.AbstractSpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.math.Radiant;
import de.hska.lat.robot.value.FloatValue;

public class StepWidthNumberModel  extends AbstractSpinnerModel
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private int index;
private boolean change;

public StepWidthNumberModel()
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
	float position;
	
	position = index;
	position/=200;
	position = Radiant.convertRadiantToDegree(position);
	text=FloatValue.toFormatedFractionString(position, 2)+"°/sec";

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
	if (index<180)
	{
		index++;
		change=true;
	}
	
	return(outputText());
}



@Override
public Object getPreviousValue() 
{
	if (index>0)
	{
		index--;
		change=true;
	}
	
	return(outputText());
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