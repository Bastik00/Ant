package de.hska.lat.robot.component.generic.spectralSynthesizer.protocol;

import de.hska.lat.comm.remote.RemoteCommand;



/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Cmd_setSpectralSynthesizerGain extends RemoteCommand
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
	protected static final String description = "set gain for a spectral synthesizer";
	
	
public Cmd_setSpectralSynthesizerGain() 
{
	this.add(new RemoteParameterSpectralSynthesizerGain("gain","spectral synthesizer gain "));
}



public Cmd_setSpectralSynthesizerGain(int command) 
{
	this();
	this.setId(command);
}


public void setData(float gain)
{
	(( RemoteParameterSpectralSynthesizerGain) this.get(Cmd_setSpectralSynthesizerGain.GAIN)).setGain(gain);
}



public float getGain()
{
	return((( RemoteParameterSpectralSynthesizerGain) this.get(Cmd_setSpectralSynthesizerGain.GAIN)).getGain());
}


@Override
public String getName() 
{
	return(Cmd_setSpectralSynthesizerGain.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setSpectralSynthesizerGain.description);
}



public static Cmd_setSpectralSynthesizerGain getCommand(int command)
{	Cmd_setSpectralSynthesizerGain cmd;
	cmd = new Cmd_setSpectralSynthesizerGain(command);
	
	return(cmd);
}

public static Cmd_setSpectralSynthesizerGain getCommand(int command,float gain)
{
	Cmd_setSpectralSynthesizerGain cmd;
	cmd = Cmd_setSpectralSynthesizerGain.getCommand(command);
	cmd.setData(gain);
	
	return(cmd);
}


}
