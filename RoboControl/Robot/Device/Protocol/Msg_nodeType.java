package de.hska.lat.robot.device.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_nodeType extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638694167468005642L;



	protected static final String name = "nodeType";
	protected static final String description = "type of a node";


	private static final int NODE_TYPE			= 0;

	

public Msg_nodeType() 
{
	this.add(new RemoteParameterUint8("type","type of a node"));
}
	
	
public Msg_nodeType(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_nodeType.name);
}


@Override
public String getDescription() 
{
	return(Msg_nodeType.description);
}



public void setData(int nodeType)
{
	(( RemoteParameterUint8) this.get(Msg_nodeType.NODE_TYPE)).setValue(nodeType);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getNodeType()
{
	return((( RemoteParameterUint8) this.get(Msg_nodeType.NODE_TYPE)).getValue());
}






public static Msg_nodeType getCommand(int id)
{
	Msg_nodeType cmd;
	cmd = new Msg_nodeType(id);
	
	return(cmd);
}



public static Msg_nodeType getCommand(int command, int nodeType)
{

	Msg_nodeType cmd;
	cmd = Msg_nodeType.getCommand(command);
	cmd.setData(nodeType);
	
	return(cmd);
}


}

