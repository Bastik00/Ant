package de.hska.lat.behavior.behavior;


import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.information.BehaviorInformations;


public abstract class Behavior  extends Arbiter 
{

	
	protected BehaviorInformations informations = new BehaviorInformations();
	protected int period;
	
	
public Behavior( int id, int period)
{
	super(id, period);
	
}


protected abstract void behave();


protected void process()
{
	Behavior.this.informations.capture();
	Behavior.this.behave();

}






}
