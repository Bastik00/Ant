package de.hska.lat.robot.component.generic.colorSensor;


import de.hska.lat.color.HsvColor;
import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.Sensor;



public abstract class ColorSensor <S extends ComponentSettingsChangeNotifier, P extends ColorSensorProtocol>
					extends Sensor <ColorSensorChangeNotifier,S, P>
{

	
	
	protected HueValue hue;
	protected SaturationValue saturation; 
	protected ValueValue value; 
	
public ColorSensor(ComponentMetaData metaData, P protocol)
{
	super(metaData, protocol);
	this.saturation = new SaturationValue(this.name);
	this.hue = new HueValue(this.name);
	this.value = new ValueValue(this.name);
}




public void  setColor(float hue, float saturation, float value)
{
	this.saturation.setValue(saturation);
	this.hue.setValue(hue);
	this.value.setValue(value);
	
	this.notifyColorChanged();
	
}


/**
 * notify all listeners of color change
 */
private void notifyColorChanged()
{
	for (ColorSensorChangeNotifier listener: this.sensorListener)
	{
		listener.colorChanged(this);
	}
	
}




/**
 * get this sensor saturation value
 * @return  saturation
 */
public float  getSaturation()
{
	return(this.saturation.getValue());
}



public SaturationValue getSaturationValue()
{
	return (this.saturation);
}




public float getHue()
{
	return(this.hue.getValue());
}



public HueValue getHueValue()
{
	return (this.hue);
}



public float getValue()
{
	return (this.value.getValue());
}



public ValueValue getValueValue()
{
	return (this.value);
}



/**
 * get color measured by this sensor as HSV color
 * @return sensors actual color as HSV 
 */

public HsvColor getAsHsv()
{
	return(new HsvColor(this.getHue(), this.getSaturation(), this.getValue()));
}


/**
 * get this sensor color as RGB. The RGB value is calculated back from actual HSV value 
 * @return sensors actual color as RGB
 */
public RgbColor getAsRgb()
{
	return (this.getAsHsv().getAsRgb());
}





}
