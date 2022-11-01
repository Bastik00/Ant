package de.hska.lat.robot.component.generic.gyroscope.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;







/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getAngularRates extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getAcceleration";
	protected static final String description = "get acceleration measured by a accelerometer sensor";
	
	
public Cmd_getAngularRates() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getAngularRates(int command) 
{
	this();
	this.setId(command);
}





public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getAngularRates.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getAngularRates.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getAngularRates.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getAngularRates.description);
}



public static Cmd_getAngularRates getCommand(int command)
{
	Cmd_getAngularRates cmd;
	cmd = new Cmd_getAngularRates(command);
	
	return(cmd);
}

public static Cmd_getAngularRates getCommand(int command,int index)
{
	Cmd_getAngularRates cmd;
	cmd = Cmd_getAngularRates.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
