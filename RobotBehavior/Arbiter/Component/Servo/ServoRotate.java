package de.hska.lat.behavior.arbiter.component.servo;


import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class ServoRotate extends ArbiterAction
{



	protected ServoRotationVelocity velocity = new ServoRotationVelocity();
	
	private static final String name = "rotate servo";

	
	


public ServoRotate(int priority)
{
	super(priority);
}


public void setVelocity(float velocity)
{
	this.velocity.setVelocity(velocity);
}


public float getVelocity()
{
	return (this.velocity.getVelocity());
}



@Override
public String getName()
{
	return(ServoRotate.name);
}



@Override
public String toString ()
{
	String returnString;
	
	returnString = this.getName();
	returnString += this.velocity.toString();
	
	return(returnString);
}



}
