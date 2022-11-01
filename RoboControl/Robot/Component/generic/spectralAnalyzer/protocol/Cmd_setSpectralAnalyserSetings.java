package de.hska.lat.robot.component.generic.spectralAnalyzer.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setSpectralAnalyserSetings extends RemoteCommand
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3812458008959748076L;
	private static final int INDEX_SENSOR = 0;
	private static final int GAIN = 1;
	

	protected static final String name = "setSpectralAnalyzerSettings";
	protected static final String description = "set settings for an Spectral analyzer";
	
	
public Cmd_setSpectralAnalyserSetings() 
{
	this.add(new RemoteParameterUint8("index","index of level"));
	//this.add(new RemoteParameterSpectralBand("gain","gein of channel"));
}



public Cmd_setSpectralAnalyserSetings(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index, float level)
{
	(( RemoteParameterUint8) this.get(Cmd_setSpectralAnalyserSetings.INDEX_SENSOR)).setValue(index);
//	((RemoteParameterSpectralBand) this.get(Cmd_setSpectraAnalyserSetings.LEVEL)).setSpectralband(level);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setSpectralAnalyserSetings.INDEX_SENSOR)).getValue());
}
/*
public float getLevel() {
	//return ((( RemoteParameterSpectralBand) this.get(Cmd_setSpectraAnalyserSetings.LEVEL)).getSpectralband());
}
*/
@Override
public String getName() 
{
	return(Cmd_setSpectralAnalyserSetings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setSpectralAnalyserSetings.description);
}



public static Cmd_setSpectralAnalyserSetings getCommand(int command)
{	Cmd_setSpectralAnalyserSetings cmd;
	cmd = new Cmd_setSpectralAnalyserSetings(command);
	
	return(cmd);
}

public static Cmd_setSpectralAnalyserSetings getCommand(int command,int index, float level)
{
	Cmd_setSpectralAnalyserSetings cmd;
	cmd = Cmd_setSpectralAnalyserSetings.getCommand(command);
	cmd.setData(index, level);
	
	return(cmd);
}


}
