package de.hska.lat.behavior.arbiter.component.servo;


import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class ServoRotateTo extends ArbiterAction
{

	

	


	protected ServoRotationVelocity velocity = new ServoRotationVelocity();
	protected ServoRotationDestination destination = new ServoRotationDestination();
	
	private static final String name = "rotate servo to position";
	
	
public ServoRotateTo(int priority)
{
	super(priority);
}


	
	
	/**
	 * set this action destination 
	 * @param destination destination of this action in radiant
	 */
	public void setDestination(float destination)
	{
		this.destination.setDestination(destination);
	}
	
	
	
	/**
	 * set this action destination 
	 * @param velocity velocity of this action in radiant
	 */
	public float getDestination()
	{
		return(this.destination.getDestination());
	}
	
	
	/**
	 * set this action velocity 
	 * @param velocity velocity of this action in radiant/second
	 */
	
	public void setVelocity(float velocity)
	{
		this.velocity.setVelocity(velocity);
	}
	
	/**
	 * get this action velocity
	 * @return this action velocity in radiant/second
	 */
	
	public float getVelocity()
	{
		return (this.velocity.getVelocity());
	}

	
	
	
	@Override
	public String getName()
	{
		return(ServoRotateTo.name);
	}
	
	
	
	@Override
	public String toString ()
	{
		String returnString;
		
		returnString = this.getName();
		returnString += this.velocity.toString()+", ";
		returnString += this.destination.toString();	
		return(returnString);
	}

}
