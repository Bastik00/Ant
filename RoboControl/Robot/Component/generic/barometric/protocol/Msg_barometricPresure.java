package de.hska.lat.robot.component.generic.barometric.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint24;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_barometricPresure extends RemoteMessage
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -9220265994763161827L;
	
	protected static final String name = "measuredBarometricPresure";
	protected static final String description = "actual presure measured by a presure sensor";


	private static final int INDEX_SENSOR = 0;
	private static final int INDEX_PRESURE = 1;
	

public Msg_barometricPresure() 
{
	this.add(new RemoteParameterUint8("index"," sensor index"));
	this.add(new RemoteParameterUint24("presure","presure value in millibar"));
}
	
	
public Msg_barometricPresure(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_barometricPresure.name);
}


@Override
public String getDescription() 
{
	return(Msg_barometricPresure.description);
}



public void setData(int index, float presure)
{
	(( RemoteParameterUint8) this.get(Msg_barometricPresure.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint24) this.get(Msg_barometricPresure.INDEX_PRESURE)).setValue((int) (presure*100));
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_barometricPresure.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public float getBarometricPresure()
{
	float presure;
	presure =((( RemoteParameterUint24) this.get(Msg_barometricPresure.INDEX_PRESURE)).getValue()); 
	presure /=100.0f;
	return(presure);
}





public static Msg_barometricPresure getCommand(int id)
{
	Msg_barometricPresure cmd;
	cmd = new Msg_barometricPresure(id);
	
	return(cmd);
}



public static Msg_barometricPresure getCommand(int command, int index,
		float presure)
{

	Msg_barometricPresure cmd;
	cmd = Msg_barometricPresure.getCommand(command);
	cmd.setData(index, presure);
	
	return(cmd);
}


}

