package de.hska.lat.ant.behavior.led;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.component.led.LedBrightness;
import de.hska.lat.behavior.behavior.Behavior;

public class AntLedBlinkBehavior extends Behavior
{


	protected LedBrightness action;
	protected ArbiterConnection output;

	
	protected LeftLedBlinkControlControlls controlSet;
		protected BrightnessControl brightnessControl;
	protected FrequencyControl frequencyControl;
	

	public AntLedBlinkBehavior(Ant ant)
	{
		super(ant, AntBehavoirPriority.LIGHT_AVOIDANCE_PRIORITY.getPriority(), 100);
		
		
		this.controlSet  = new LeftLedBlinkControlControlls(); 
		
		this.frequencyControl = this.controlSet.getFrequencyControl();
		this.brightnessControl = this.controlSet.getBrightnessControl();
		
		this.start();
	}


	
	public FrequencyControl getFrequencyControl()
	{
		return(this.controlSet.getFrequencyControl());
	}




	public BrightnessControl getBrightnessControl()
	{
		return(this.controlSet.getBrightnessControl());
	}
	
	
	
	public void setOutput(ArbiterConnection output)
	{
		this.output = output;
	}

	
	protected int frequencyCounter;
	protected boolean phase;

	protected void react()
	{
		
		
		this.frequencyCounter++;
		if (frequencyCounter>frequencyControl.getValue())
		{
			phase = !phase;
			frequencyCounter=0;
		}
		
		if (action == null)
		{
			this.action = new LedBrightness(this.id);
			this.output.receive(action);
		}
		
		if (phase)
		{
			this.action.setBrightness(this.brightnessControl.getValue());
		}
		else
		{
			this.action.setBrightness(0);
		}
	}

	
	
	@Override
	protected void behave()
	{
		this.react();
		//react();
	}

	
	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.output);
		
		return(list);
	}


}
