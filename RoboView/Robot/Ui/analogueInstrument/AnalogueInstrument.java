package de.hska.lat.robot.ui.analogueInstrument;



import de.hska.lat.robot.ui.icon.DroidIconListener;
import de.hska.lat.robot.ui.icon.DroidIcon;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.FloatValue;



public class AnalogueInstrument extends DroidIcon<DroidIconListener> implements ComponentValueChangeListener
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8319501515658803816L;

	protected String scaleUnit="V";
	protected int fraction;

	
	protected FloatValue value;
	

	protected int tickCount;
	
	
	
	protected ScaleFragment [] scale;
	

	
public AnalogueInstrument( String name)  
{
	super(name);
	
	this.value= new FloatValue(name);
	this.value.addListener(this);
}



public AnalogueInstrument(String name,float minRange,float maxRange) 
{
	super( name);
	this.value= new FloatValue(name,minRange, maxRange);
	this.value.addListener(this);
}



public void setTickCount(int newTiclCount)
{
	
	this.tickCount = newTiclCount;
}




public void setScale(ScaleFragment [] scale)
{
	this.scale=scale;
}

public void setScaleUnit(String  scaleUnit)
{
	this.scaleUnit=scaleUnit;
}



public FloatValue getValue()
{
	return(this.value);
	
}



@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	
	this.invalidate();
	this.repaint();
}



public void setFraction (int fraction)
{
	this.fraction = fraction;
}



}
