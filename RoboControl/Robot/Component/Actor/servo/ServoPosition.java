package de.hska.lat.robot.component.actor.servo;

import de.hska.lat.math.Radiant;
import de.hska.lat.robot.value.FloatValue;





public class ServoPosition extends FloatValue //extends ComponentValue<ServoPosition,ServoPositionChangeNotifier> implements FloatValueChangeListener
{

//	protected FloatValue maxServoRange;
//	protected FloatValue minServoRange;
	
	protected float minRange;
	protected float maxRange;

public ServoPosition(float minRange,float maxRange)
{
	super("servo position");

	this.minRange=minRange;
	this.maxRange=maxRange;
	
//	this.minRange = this.minServoRange.getValue();
//	this.maxRange = this.maxServoRange.getValue(); 
	
//	this.minServoRange.addListener(this);
//	this.maxServoRange.addListener(this);
	
}
	
	



public float getPositionAsRadiant()
{

	 float radiant;
	
	radiant = this.value * ((float)Math.PI / 180f );
	
	return(radiant);

}


public float getPositionAsDegree()
{
	return(Radiant.convertRadiantToDegree(this.value));
}




/*
@Override
public void valueChanged(ComponentValue<FloatValue, ?> source)
{
	if (source==minServoRange)
	{
		this.minRange=source.getValue();
	}
	else if (source==maxServoRange)
	{
		this.maxRange=source.getValue();
	}
	
	this.setValue(source.getValue());
}

*/

	



}
