package de.hska.lat.robot.component.sensor.tsl2561;



import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.sensor.tsl2561.protocol.Msg_tsl2561Data;


/**
 * Super class for TSL 2561 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public class Tsl2561Set  extends ComponentSet<Tsl2561,ComponentProtocol>
{


	
/**
	 * 
	 */
	private static final long serialVersionUID = 4314099499914272561L;

	
	
public Tsl2561Set(DetectorMetaData[] sensors, ComponentProtocol protocol)
{
	
	for (DetectorMetaData tsl2561: sensors)
	{
		this.add(new Tsl2561(tsl2561, protocol));
	}
}	
	
	
	
	
	

/**
 * pass sensor value to sensor instance
 * @param sensorDistance 
 */
public void processSensorValue(Msg_tsl2561Data remoteMessage)
{
	Tsl2561 sensor;
	int index;
	
	index=remoteMessage.getIndex();
	sensor=this.getComponentOnLocalId(index);
	if (sensor!=null)
	{
		sensor.setData(remoteMessage.getVisible(), remoteMessage.getIr());	
	}
}





}
