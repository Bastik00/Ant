package de.hska.lat.robot.component.sensor.lm75;


import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;
import de.hska.lat.robot.component.sensor.tmp102.protocol.Msg_tmp102Settings;



public class Lm75Set   extends TemperatureSensorSet<Lm75,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632114081873764626L;






	/**
	 * 
	 */

	

public Lm75Set()
{
}
	
	
public Lm75Set(ArrayList<DetectorMetaData> detectors, TemperatureSensorProtocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Lm75(detector, protocol));
	}

}
	



public void processSettings(Msg_tmp102Settings remoteMessage)
{
	Lm75 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		
	//	sensor.setSettings(remoteMessage.getPeriod(), remoteMessage.getI2CAddres(),remoteMessage.isExtended() );
	
	}
}






@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_tmp102Settings)
	{
		this. processSettings((Msg_tmp102Settings) remoteMessage);
	}
	else
	{
		super.decodeMessage(remoteMessage);
	}
	
	return(true);
}




}
