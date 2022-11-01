package de.hska.lat.robot.component.generic.spectralAnalyzer.protocol;

import de.hska.lat.comm.remote.RemoteCommand;



/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setSpectralAnalyserGain extends RemoteCommand
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4291033606739581923L;


	/**
	 * 
	 */

	private static final int GAIN = 0;
	

	protected static final String name = "setSpectralAnalyzerGain";
	protected static final String description = "set gain for a spectral analyser for an Spectral analyzer";
	
	
public Cmd_setSpectralAnalyserGain() 
{
	this.add(new RemoteParameterSpectralAnalyserGain("gain","gain"));
}



public Cmd_setSpectralAnalyserGain(int command) 
{
	this();
	this.setId(command);
}


public void setData(float gain)
{
	(( RemoteParameterSpectralAnalyserGain) this.get(Cmd_setSpectralAnalyserGain.GAIN)).setGain(gain);
}



public float getGain()
{
	return((( RemoteParameterSpectralAnalyserGain) this.get(Cmd_setSpectralAnalyserGain.GAIN)).getGain());
}


@Override
public String getName() 
{
	return(Cmd_setSpectralAnalyserGain.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setSpectralAnalyserGain.description);
}



public static Cmd_setSpectralAnalyserGain getCommand(int command)
{	Cmd_setSpectralAnalyserGain cmd;
	cmd = new Cmd_setSpectralAnalyserGain(command);
	
	return(cmd);
}

public static Cmd_setSpectralAnalyserGain getCommand(int command,float gain)
{
	Cmd_setSpectralAnalyserGain cmd;
	cmd = Cmd_setSpectralAnalyserGain.getCommand(command);
	cmd.setData(gain);
	
	return(cmd);
}


}
