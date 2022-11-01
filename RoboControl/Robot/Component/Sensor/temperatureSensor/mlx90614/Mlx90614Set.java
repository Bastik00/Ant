package de.hska.lat.robot.component.temperatureSensor.mlx90614;

import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.protocol.Msg_mlx90614Settings;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.value.ComponentValue;


public class Mlx90614Set  extends ComponentSet<Mlx90614,Mlx90614Protocol>
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5046089855261722857L;
	
	Mlx90614AmbientSensorSet ambientSensorSet;
	Mlx90614ObjectSensorSet objectSensorSet;
	
	
public Mlx90614Set()
{
}
		
	
	
public Mlx90614Set(ArrayList<DetectorMetaData> detectors, Mlx90614Protocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Mlx90614(detector, protocol));
	}

	this.ambientSensorSet = new Mlx90614AmbientSensorSet(this);
	this.objectSensorSet = new Mlx90614ObjectSensorSet(this);
	
}



protected void init()
{
	this.ambientSensorSet = new Mlx90614AmbientSensorSet(this);
	this.objectSensorSet = new Mlx90614ObjectSensorSet(this);
	
}



public RemoteDecoder getAmbientSensors()
{
	return (this.ambientSensorSet);
}
		
	
public RemoteDecoder getObjectSensors()
{
	return (this.objectSensorSet);
}
	
	




public void processSettings(Msg_mlx90614Settings remoteMessage)
{
	Mlx90614 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		
		sensor.setSettings(remoteMessage.getAddress(), remoteMessage.getEmissivity());
	
	}
}






@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_mlx90614Settings)
	{
		this. processSettings((Msg_mlx90614Settings) remoteMessage);
	}
	else
	{
		super.decodeMessage(remoteMessage);
	}
	
	return(true);
}



@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	ArrayList<ComponentValue<?>> values = super.getDataValues();
	
	for (Mlx90614 sensor: this)
	{
		values.add(sensor.getAmbientValue());
		values.add(sensor.getObjectValue());
	}

	return (values);
}	



}
