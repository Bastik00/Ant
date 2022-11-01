package de.hska.lat.robot.value.color;


import de.hska.lat.color.HsvColor;
import de.hska.lat.robot.value.ComponentValue;


public class ColorValue extends ComponentValue<ColorValue> 
{

	private static final String TYPE_NAME = "brightness";
	
	protected HsvColor color;
	
public ColorValue(String name)
{
	super(name);
	this.minRange = 0;
	this.maxRange = 1;
}


@Override
public String getTypeName()
{
	return(ColorValue.TYPE_NAME);
}


public HsvColor getColor()
{
	return (this.color);
}


public void setColor(HsvColor color)
{
	this.color = color;
	this.notifyValueChanged();
}

}
