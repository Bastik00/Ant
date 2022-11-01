package de.hska.lat.robot.component.sensor.mlx90614.view;

import javax.swing.AbstractSpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Mlx90614EmissivitySpinnerModel  extends AbstractSpinnerModel
{


	
/**
	 * 
	 */
	private static final long serialVersionUID = -5379457485380528906L;
private int index;
private boolean change;

public Mlx90614EmissivitySpinnerModel()
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
	return("0x"+Integer.toHexString(index));	
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