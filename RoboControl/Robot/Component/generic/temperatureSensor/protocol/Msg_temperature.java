package de.hska.lat.robot.component.generic.temperatureSensor.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_temperature extends RemoteMessage
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -9220265994763161827L;
	
	protected static final String name = "temperature";
	protected static final String description = "actual temperature measured by a temperature sensor";


	private static final int INDEX_SENSOR = 0;
	private static final int INDEX_TEMPERATUREE = 1;
	

public Msg_temperature() 
{
	this.add(new RemoteParameterUint8("index"," sensor index"));
	this.add(new RemoteParameterTemperature("temperature","temperature value in kelvin"));
}
	
	
public Msg_temperature(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_temperature.name);
}


@Override
public String getDescription() 
{
	return(Msg_temperature.description);
}



public void setData(int index, float temperature)
{
	(( RemoteParameterUint8) this.get(Msg_temperature.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterTemperature) this.get(Msg_temperature.INDEX_TEMPERATUREE)).setTemperature(temperature);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_temperature.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public float getTemperature()
{
	return((( RemoteParameterTemperature) this.get(Msg_temperature.INDEX_TEMPERATUREE)).getTemperature());
}





public static Msg_temperature getCommand(int id)
{
	Msg_temperature cmd;
	cmd = new Msg_temperature(id);
	
	return(cmd);
}



public static Msg_temperature getCommand(int command, int index,
		float temperature)
{

	Msg_temperature cmd;
	cmd = Msg_temperature.getCommand(command);
	cmd.setData(index, temperature);
	
	return(cmd);
}


}

