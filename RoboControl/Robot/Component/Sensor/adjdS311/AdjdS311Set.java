package de.hska.lat.robot.component.sensor.adjdS311;


import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.generic.colorSensor.ColorSensorProtocol;
import de.hska.lat.robot.component.sensor.SensorSet;
import de.hska.lat.robot.component.sensor.tmp102.protocol.Msg_tmp102Settings;



public class AdjdS311Set   extends SensorSet<AdjdS311, ColorSensorProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4622367814469462748L;

	protected AdjdS311ColorSensorSet	colorSensorSet;
	
public AdjdS311Set(ArrayList<DetectorMetaData> detectors, AdjdS311Protocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new AdjdS311(detector, protocol));
	}

	this.colorSensorSet = new AdjdS311ColorSensorSet(this);
	
}
	



public AdjdS311ColorSensorSet getColorSensors()
{
	return(this.colorSensorSet);
}



public void processSettings(Msg_tmp102Settings remoteMessage)
{
	AdjdS311 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		
		//sensor.setSettings(remoteMessage.getPeriod(), remoteMessage.getI2CAddres(),remoteMessage.isExtended() );
	
	}
}






@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_tmp102Settings)
	{
		this. processSettings((Msg_tmp102Settings) remoteMessage);
	}
	
	return(true);
}




}
