package de.hska.lat.robot.component.generic.temperatureSensor;

import de.hska.lat.robot.value.ComponentValue;




public class TemperatureValue extends ComponentValue<TemperatureValue>
{

	protected float granularity;
	
	private static final String TYPE_NAME = "temperature"; 

	
public TemperatureValue(String name, float minRange, float maxRange)
{
	super(name);

	this.minRange = minRange;
	this.maxRange = maxRange;
	this.notifyAllways = true;
}	






protected void setOverflowValue()
{
	this.value = 0;
	this.valid = false;
	this.overflow = false;
	this.underflow = false;
}


protected void setUnderflowValue()
{
	this.value = 0;
	this.valid = false;
	this.overflow = false;
	this.underflow = false;
}




/**
 * set granularity of this sensor value. Granularity is the minimal difference between two sensor values 
 * @param granularity
 */
public void setGranularity(float granularity)
{
	this.granularity = granularity;
}




public float getGranularity()
{
	return(this.granularity);
}





public float getAsCelsius()
{
	return(this.value-273.15f);
}


@Override
public String getTypeName()
{
	return(TemperatureValue.TYPE_NAME);
}


/**
 * convert given temperature value from Kelvin to Celsius 
 * @param kelvin temperature in kelvin
 * @return temperature in celsius
 * @throws TemperatureConversionException 
 */
public static float kelvinToCelsius(float kelvin) throws TemperatureConversionException
{

	if (kelvin <0)
	{
		throw(new TemperatureConversionException());
	}
	
	return(kelvin-273.15f);
}


/**
 * convert given temperature value from Celsius to Kelvin 
 * @param kelvin temperature in kelvin
 * @return temperature in celsius
 * @throws TemperatureConversionException 
 */
public static float celsiusToKelvin(float celsius) throws TemperatureConversionException
{
	float kelvin;
	
	kelvin = celsius+273.15f;
	
	if (kelvin <0)
	{
		throw(new TemperatureConversionException());
	}
	
	return(kelvin);
}




}
