package de.hska.lat.robot.component.sensor.bmp085;



import java.util.ArrayList;


import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.sensor.bmp085.protocol.Msg_bmp085Settings;



public class Bmp085Set  extends ComponentSet<Bmp085, ComponentProtocol>
{



	
/**
	 * 
	 */
	private static final long serialVersionUID = 7626423114169877335L;

	protected Bmp085TemperatureSensorSet temperatureSensorSet;
	protected Bmp085BarometricSensorSet barometricSensorSet;
	
public Bmp085Set(ArrayList<DetectorMetaData> detectors,Bmp085Protocol protocol)
{
	
	
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Bmp085(detector,protocol));
	}
	
	this.temperatureSensorSet = new Bmp085TemperatureSensorSet(this);
	this.barometricSensorSet = new Bmp085BarometricSensorSet(this);
	
}

public Bmp085TemperatureSensorSet getTemperatureSensors()
{
	return (this.temperatureSensorSet);
}	
	

public Bmp085BarometricSensorSet getPresureSensors()
{
	return (this.barometricSensorSet);
}

/**
 * get all values of this set
 * @return
 */
	
	
	


public void processSettings(Msg_bmp085Settings remoteMessage)
{
	Bmp085 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		
		sensor.setSettings(remoteMessage.getResolution());
	
	}
}






@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_bmp085Settings)
	{
		this. processSettings((Msg_bmp085Settings) remoteMessage);
	}
	
	return(true);
}


}
