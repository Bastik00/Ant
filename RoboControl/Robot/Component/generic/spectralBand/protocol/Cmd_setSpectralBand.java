package de.hska.lat.robot.component.generic.spectralBand.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_setSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_setSpectralBand;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setSpectralBand extends RemoteCommand
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3812458008959748076L;
	private static final int INDEX_SENSOR = 0;
	private static final int LEVEL = 1;
	

	protected static final String name = "getSpectralband";
	protected static final String description = "get measured audio signal from a microphone as a fourier";
	
	
public Cmd_setSpectralBand() 
{
	this.add(new RemoteParameterUint8("index","index of level"));
	this.add(new RemoteParameterSpectralBand("level","level of level"));
}



public Cmd_setSpectralBand(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float level)
{
	(( RemoteParameterUint8) this.get(Cmd_setSpectralBand.INDEX_SENSOR)).setValue(index);
	((RemoteParameterSpectralBand) this.get(Cmd_setSpectralBand.LEVEL)).setSpectralband(level);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setSpectralBand.INDEX_SENSOR)).getValue());
}

public float getLevel() {
	return ((( RemoteParameterSpectralBand) this.get(Cmd_setSpectralBand.LEVEL)).getSpectralband());
}

@Override
public String getName() 
{
	return(Cmd_setSpectralBand.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setSpectralBand.description);
}



public static Cmd_setSpectralBand getCommand(int command)
{	Cmd_setSpectralBand cmd;
	cmd = new Cmd_setSpectralBand(command);
	
	return(cmd);
}

public static Cmd_setSpectralBand getCommand(int command,int index, float level)
{
	Cmd_setSpectralBand cmd;
	cmd = Cmd_setSpectralBand.getCommand(command);
	cmd.setData(index, level);
	
	return(cmd);
}


}
