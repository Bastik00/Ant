package de.hska.lat.robot.component.generic.spectralBand.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_getSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_getSpectralBand;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_getSpectralBand extends RemoteCommand
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3491100628936607083L;


	private static final int INDEX_SENSOR = 0;
	

	protected static final String name = "getSpectralband";
	protected static final String description = "get measured audio signal from a microphone as a fourier";
	
	
public Cmd_getSpectralBand() 
{
	this.add(new RemoteParameterUint8("index","index of level"));
}



public Cmd_getSpectralBand(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Cmd_getSpectralBand.INDEX_SENSOR)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_getSpectralBand.INDEX_SENSOR)).getValue());
}



@Override
public String getName() 
{
	return(Cmd_getSpectralBand.name);
}


@Override
public String getDescription() 
{
	return(Cmd_getSpectralBand.description);
}



public static Cmd_getSpectralBand getCommand(int command)
{	Cmd_getSpectralBand cmd;
	cmd = new Cmd_getSpectralBand(command);
	
	return(cmd);
}

public static Cmd_getSpectralBand getCommand(int command,int index)
{
	Cmd_getSpectralBand cmd;
	cmd = Cmd_getSpectralBand.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
