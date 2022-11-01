package de.hska.lat.robot.component.sensor.mlx90620;

import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;


public class Mlx90620AmbientSensorSet extends TemperatureSensorSet<Mlx90620AmbientSensor,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3717988542228882512L;

	
	
public 	Mlx90620AmbientSensorSet(ArrayList<ComponentMetaData> sensors, TemperatureSensorProtocol protocol)
{
	
	
	for (ComponentMetaData sensor : sensors)
	{
		this.add(new Mlx90620AmbientSensor(sensor,protocol));
	}
	
}



public Mlx90620AmbientSensorSet(Mlx90620Set mlx90620Set)
{
	for (Mlx90620 sensor : mlx90620Set)
	{
		this.add(sensor.getAmbientSensor());
	}
}



	
}
