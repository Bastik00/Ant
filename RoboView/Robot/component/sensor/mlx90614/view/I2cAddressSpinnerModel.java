package de.hska.lat.robot.component.sensor.mlx90614.view;

import javax.swing.AbstractSpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class I2cAddressSpinnerModel  extends AbstractSpinnerModel
{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 45555662213838431L;
	
	
private int index;
private boolean change;

public I2cAddressSpinnerModel()
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
	if (index<127)
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