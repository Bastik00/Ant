package de.hska.lat.ant.behavior.motion.action;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class Hold extends ArbiterAction
{

	
	private static final String name = "hold";
	
public Hold(int priority)
{
	super(priority);
	
}


@Override
public String getName()
{
	return(Hold.name);
}



@Override
public String toString ()
{
	String returnString;
	
	returnString = this.getName();
	
	return(returnString);
}

}
