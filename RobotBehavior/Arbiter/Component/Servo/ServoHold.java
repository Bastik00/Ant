package de.hska.lat.behavior.arbiter.component.servo;


import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class ServoHold extends ArbiterAction
{

	

	

	
	private static final String name = "hold servo";

	
	


public ServoHold(int priority)
{
	super(priority);
}



@Override
public String getName()
{
	return(ServoHold.name);
}



@Override
public String toString ()
{
	String returnString;
	
	returnString = this.getName();
	
	return(returnString);
}



}
