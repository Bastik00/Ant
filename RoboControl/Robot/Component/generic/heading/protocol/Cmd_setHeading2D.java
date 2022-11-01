package de.hska.lat.robot.component.generic.heading.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Cmd_setHeading2D extends RemoteCommand
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296824683538183051L;


	private static final int INDEX_SENSOR 		= 0;
	private static final int INDEX_RANGE		= 1;
	private static final int INDEX_GAIN 		= 2;
	private static final int INDEX_ADDRESS		= 3;
	
	
	protected static final String name = "setHeading";
	protected static final String description = "set a new Heading";
	
	

public Cmd_setHeading2D() 
{
	this.add(new RemoteParameterUint8("index","sfr 10 sensor index"));
	this.add(new RemoteParameterUint8("range","sfr 10 sensor range"));
	this.add(new RemoteParameterUint8("gain","sfr 10 sensor gain"));
	this.add(new RemoteParameterUint8("addres","sfr 10 sensor addres"));
}





	
	
public Cmd_setHeading2D(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Cmd_setHeading2D.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setHeading2D.description);
}




public void setData(float heading)
{
/*	(( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_RANGE)).setValue(range);
	(( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_GAIN)).setValue(gain);
	(( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_ADDRESS)).setValue(address);
*/
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_SENSOR)).getValue());
}

public int getRange()
{
	return((( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_RANGE)).getValue());
}


public int getGain()
{
	return((( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_GAIN)).getValue());
}


public int getAddress()
{
	return((( RemoteParameterUint8) this.get(Cmd_setHeading2D.INDEX_ADDRESS)).getValue());
}



public static Cmd_setHeading2D getCommand(int id)
{
	Cmd_setHeading2D cmd;
	cmd = new Cmd_setHeading2D(id);
	
	return(cmd);
}







public static Cmd_setHeading2D getCommand(int command, float heading)
{
	
	Cmd_setHeading2D cmd;
	cmd = Cmd_setHeading2D.getCommand(command);
	cmd.setData(heading);
	
	return(cmd);
	
	
}




}

