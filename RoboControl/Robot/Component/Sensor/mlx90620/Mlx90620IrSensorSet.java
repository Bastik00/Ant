package de.hska.lat.robot.component.sensor.mlx90620;

import de.hska.lat.robot.component.thermalCamera.ThermalCameraSet;



public class Mlx90620IrSensorSet extends ThermalCameraSet//ComponentSet<Mlx90620IrSensor,Mlx90620Protocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3717988542228882512L;

	

public Mlx90620IrSensorSet(Mlx90620Set mlx90620Set)
{
	for (Mlx90620 sensor : mlx90620Set)
	{
		this.add(sensor.getIrSensor());
	}
	

}

}
