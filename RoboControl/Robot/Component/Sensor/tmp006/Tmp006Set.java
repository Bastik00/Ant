package de.hska.lat.robot.component.sensor.tmp006;


import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.sensor.tmp006.protocol.Msg_tmp006Settings;



public class Tmp006Set   extends ComponentSet<Tmp006,Tmp006Protocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4622367814469462748L;

	protected Tmp006AmbientSet ambientSensorSet;
	protected Tmp006ObjectSet objectSensorSet;
	
	
public Tmp006Set(ArrayList<DetectorMetaData> detectors, Tmp006Protocol protocol)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new Tmp006(detector, protocol));
	}

	
	this.ambientSensorSet = new Tmp006AmbientSet(this);
	this.objectSensorSet = new Tmp006ObjectSet(this);
}
	

public Tmp006Set()
{
}
	

protected void init()
{
	
	this.ambientSensorSet = new Tmp006AmbientSet(this);
	this.objectSensorSet = new Tmp006ObjectSet(this);
}


public Tmp006AmbientSet getAmbientSensors()
{
	return(this.ambientSensorSet);
}
 


public Tmp006ObjectSet getObjectSensors()
{
	return(this.objectSensorSet);
}
 


public void processSettings(Msg_tmp006Settings remoteMessage)
{
	Tmp006 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		
		sensor.setSettings(remoteMessage.getPeriod(), remoteMessage.getI2CAddres() );
	
	}
}






@Override
public boolean decodeMessage(RemoteMessage remoteMessage)
{
	if (remoteMessage instanceof Msg_tmp006Settings)
	{
		this. processSettings((Msg_tmp006Settings) remoteMessage);
	}
	else
	{
		super.decodeMessage(remoteMessage);
	}
	
	return(true);
}




}
