package de.hska.lat.robot.component.sensor.mlx90620;



import de.hska.lat.robot.component.ComponentMetaData;

import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.component.thermalCamera.ThermalCamera;
import de.hska.lat.robot.component.thermalCamera.ThermalFrame;
import de.hska.lat.robot.component.thermalCamera.protocol.ThermalCameraProtocol;


public class Mlx90620IrSensor extends ThermalCamera
{

	
	
	public Mlx90620IrSensor(ComponentMetaData metaData,
			ThermalCameraProtocol protocol)
	{
		super(metaData, protocol);
		
		this.actualMax = new TemperatureValue(this.name+" min", -305.15f, 573.15f);
		this.actualMin = new TemperatureValue(this.name+" max", -305.15f, 573.15f);
		this.actualAverage = new TemperatureValue(this.name+" average", -305.15f, 573.15f);
		

		
		this.activeFrame = new ThermalFrame(4,16);
		this.nextFrame = new ThermalFrame(4,16);
		
	}


	
	

	
	
	


}
