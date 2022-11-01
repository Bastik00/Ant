package de.hska.lat.robot.component.distanceSensor.gp2D.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getGp2DRawValue extends RemoteCommand
{
	
	

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7668727909048952420L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getGp2DRawValue";
	protected static final String description = "get raw value from an gp2D sensor ";
	
	
public Cmd_getGp2DRawValue() 
{
	this.add(new RemoteParameterUint8("index","index of sensor"));
}



public Cmd_getGp2DRawValue(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getGp2DRawValue.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getGp2DRawValue.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getGp2DRawValue.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getGp2DRawValue.description);
}



public static Cmd_getGp2DRawValue getCommand(int command)
{
	Cmd_getGp2DRawValue cmd;
	cmd = new Cmd_getGp2DRawValue(command);
	
	return(cmd);
}

public static Cmd_getGp2DRawValue getCommand(int command,int index)
{
	Cmd_getGp2DRawValue cmd;
	cmd = Cmd_getGp2DRawValue.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
