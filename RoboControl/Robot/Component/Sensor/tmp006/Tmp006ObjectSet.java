package de.hska.lat.robot.component.sensor.tmp006;


import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;



public class Tmp006ObjectSet   extends TemperatureSensorSet<Tmp006ObjectSensor,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4622367814469462748L;


	
	
public Tmp006ObjectSet(Tmp006Set tmp006Set)
{
	for (Tmp006 sensor: tmp006Set)
	{
		this.add(sensor.getObjectSensor());
	}

}
	

}
