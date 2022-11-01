package de.hska.lat.robot.component.sensor.adjdS311;


import de.hska.lat.robot.component.generic.colorSensor.ColorSensorProtocol;
import de.hska.lat.robot.component.generic.colorSensor.ColorSensorSet;




public class AdjdS311ColorSensorSet extends ColorSensorSet<AdjdS311ColorSensor,ColorSensorProtocol>
{

/**
	 * 
	 */
	private static final long serialVersionUID = -7910103723894552188L;

	
	
public AdjdS311ColorSensorSet(AdjdS311Set adjdS311Set)
{
	for (AdjdS311 sensor : adjdS311Set)
	{
		this.add(sensor.getColorSensor());
	}
}

}
