package de.hska.lat.robot.component.sensor.tmp102;


import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorSet;
import de.hska.lat.robot.component.sensor.tmp102.protocol.Msg_tmp102Settings;



public class Tmp102Set   extends TemperatureSensorSet<Tmp102,TemperatureSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4622367814469462748L;

	
	
public Tmp102Set(ArrayList<DetectorMetaData> detectors, TemperatureSensorProtocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Tmp102(detector, protocol));
	}

}
	



public void processSettings(Msg_tmp102Settings remoteMessage)
{
	Tmp102 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		
		sensor.setSettings(remoteMessage.getPeriod(), remoteMessage.getI2CAddres(),remoteMessage.isExtended() );
	
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
