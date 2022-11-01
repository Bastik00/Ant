package de.hska.lat.robot.component.distanceSensor.gp2D;

import javax.swing.AbstractSpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DistanceNumberModel  extends AbstractSpinnerModel
{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private int index;
	private boolean change;
	private float maxDistance;

public DistanceNumberModel(int maxDistance)
{
//	this.index=0;
	this.maxDistance=maxDistance;
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

	text=maxDistance+" Cm";

	return(text);	
}
	
public Object getValue()
{
	return(outputText());

}


@Override
public Object getNextValue()
{
	
	maxDistance++;
	change=true;
	
	
	return(outputText());
}


@Override
public Object getPreviousValue() 
{
	if (maxDistance>0)
	{
		maxDistance--;
		change=true;
	}
	
	return(outputText());
}



public void setMaxDistance(float maxDistance) 
{
	this.maxDistance = maxDistance;
	notifyListeners();
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


public float getDistance()
{
	return(this.maxDistance);
}

}