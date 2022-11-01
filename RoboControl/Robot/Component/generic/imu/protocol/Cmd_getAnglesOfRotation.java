package de.hska.lat.robot.component.generic.imu.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;







/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getAnglesOfRotation extends RemoteCommand
{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1317524507267935047L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getAcceleration";
	protected static final String description = "get acceleration measured by a accelerometer sensor";
	
	
public Cmd_getAnglesOfRotation() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getAnglesOfRotation(int command) 
{
	this();
	this.setId(command);
}





public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getAnglesOfRotation.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getAnglesOfRotation.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getAnglesOfRotation.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getAnglesOfRotation.description);
}



public static Cmd_getAnglesOfRotation getCommand(int command)
{
	Cmd_getAnglesOfRotation cmd;
	cmd = new Cmd_getAnglesOfRotation(command);
	
	return(cmd);
}

public static Cmd_getAnglesOfRotation getCommand(int command,int index)
{
	Cmd_getAnglesOfRotation cmd;
	cmd = Cmd_getAnglesOfRotation.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
