package de.hska.lat.behavior.arbiter.component.led;



import java.util.ArrayList;

import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.ComponentArbiter;
import de.hska.lat.behavior.arbiter.action.ActionWait;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.robot.component.actor.led.Led;



public class LedArbiter extends ComponentArbiter
{
	
	
	protected Led led;


	protected static final int TIME_BASE 	= 100; 	
	
	
public LedArbiter(int id, Led led)
{
	super(id, LedArbiter.TIME_BASE);	
	this.led = led;
	this.name = led.getComponentName();
	this.start();
}


/**
 * execute given action
 * @param action action to be executed
 */
protected void executeAction(ArbiterAction action)
{

	
	this.activeAction = action;
	
	
	if (action instanceof LedBrightness)
	{
		this.doSetBrightness((LedBrightness) action);
	}
	if (action instanceof LedBlink)
	{
		this.doLedBlink((LedBlink) action);
	}
	else if (action instanceof ActionWait)
	{
		this.doWait();
	}
}


protected void doSetBrightness(LedBrightness action)
{
	led.remote_setBrightness(action.getBrightness());
}



protected int blinkCounter;
protected int blinkFrequency;

protected boolean status;

protected void doLedBlink(LedBlink action)
{
	this.blinkFrequency = (int) (action.getFrequency() * 500 /this.period  );
	
	
	this.blinkCounter--;
	
	if (this.blinkCounter<0)
	{
		
		status = !status;
		
		
		if (status == true)
		{
			this.led.remote_setBrightness(1.0f);
			
		}
		else
		{
			this.led.remote_setBrightness(0.0f);
		}
		
		this.blinkCounter=this.blinkFrequency;
	}
	
	
}



protected void doHold()
{
	//this.velocityControl.setValue(0);
}


protected void doWait()
{
	//this.velocityControl.setValue(0);
}




public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();

	return(list);
}






	
}
