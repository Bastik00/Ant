package de.hska.lat.robot.component.generic.compass;



import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;

import de.hska.lat.robot.component.generic.compass.protocol.Msg_geomagneticField;
import de.hska.lat.robot.component.generic.compass.protocol.Stream_geomagneticField;






/**
 * Super class for Sharp Gp2 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public abstract class CompassSet<T extends Compass<?,?>,P extends CompassProtocol>  extends ComponentSet<T,P>
	{


	
/*
public DistanceSensorSet(ArrayList<DetectorMetaData> detectors)
{
	for (DetectorMetaData detector: detectors)
	{
		this.add(new T(detector));
	}
	
}
*/

/**
	 * 
	 */
	private static final long serialVersionUID = -2319328805472528668L;




public void processGeomagneticFields(Stream_geomagneticField geomagneticFields) 
{
	T sensor;
	int index;
	

	for (index=0;index<geomagneticFields.getParameterCount(); index++)
	{
		sensor=this.getComponentOnLocalId(index);

		if (sensor!=null)
		{
			sensor.setGeomagneticField(geomagneticFields.getGeomagneticField(index));
		}

	}
}



public void processGeomagneticField(Msg_geomagneticField geomagneticField)
{
	T sensor;
	int index;
	
	index = geomagneticField.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setGeomagneticField(geomagneticField.getGeomagneticField());

	}
	
}




@Override
public boolean decodeMessage(RemoteMessage remoteData)
{
	if (remoteData instanceof Msg_geomagneticField)
	{
		processGeomagneticField((Msg_geomagneticField)remoteData);
	}
	else
	{
		super.decodeMessage(remoteData);		
	}

	
	return false;
}



@Override
public boolean decodeStream(RemoteStream remoteData)
{
	if (remoteData instanceof Stream_geomagneticField)
	{
		processGeomagneticFields((Stream_geomagneticField)remoteData);

	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return(true);	
}



}
