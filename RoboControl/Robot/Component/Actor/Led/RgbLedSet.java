package de.hska.lat.robot.component.actor.led;



import java.util.ArrayList;



import de.hska.lat.robot.component.ComponentMetaData;






/**
 * 
 * @author Oktavian Gniot
 *
 * 
 *
 */

public class RgbLedSet extends LedSet {

	
/**
	 * 
	 */
	private static final long serialVersionUID = -7802149314719909711L;


public RgbLedSet(ArrayList<ComponentMetaData> leds, RgbLedProtocol protocol)
{

		
	for (ComponentMetaData led: leds)
	{
		this.add(new RgbLed(led, protocol));
	}
	

	
}

/*
public void  processCurrentSettings(Msg_currentSettings currentData)
{
	CurrentSensor current;

	
	current=this.getComponentOnLocalId(currentData.getIndex());
	if (current!=null)
	{
		
		current.setThreshold(currentData.getThreshold());
		current.setWindowSize(currentData.getWindowSize());
	}

}
	


public void  processCurrentConsumption(Msg_totalCurrentDrain currentData)
{
	CurrentSensor current;

	
	current=this.getComponentOnLocalId(currentData.getIndex());
	if (current!=null)
	{
		current.setConsumption(currentData.getTotalDrain());
	}

}
	


public void  processCurrentValue(Msg_measuredCurrent currentData)
{
	CurrentSensor current;

	
	current=this.getComponentOnLocalId(currentData.getIndex());
	if (current!=null)
	{
		current.setLevel(currentData.getDrain());
	}

}

public void processCurrentValues(Stream_measuredCurrents currentData)
{
	CurrentSensor current;

	int index;


	for (index=0;index<currentData.getParameterCount(); index++)
	{
		current=this.getComponentOnLocalId(index);
		if (current!=null)
		{
			current.setLevel(currentData.getValue(index));
		}
	}


}



public void  processCurrentMaxValue(Msg_maxCurrentDrain currentData)
{
	CurrentSensor current;

	
	current=this.getComponentOnLocalId(currentData.getIndex());
	if (current!=null)
	{
		current.setMaxLevel(currentData.getMaxDrain());
	}

}


*/



}
