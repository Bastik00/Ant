package de.hska.lat.robot.component.generic.spectralBand.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Msg_spectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.RemoteParameterSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Msg_spectralBand;



/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_spectralBand extends RemoteMessage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6919629137151711915L;
	protected static final String name = "spectralband";
	protected static final String description = "actual audio signal from a microphone as a fourier";


	private static final int INDEX_SENSOR = 0;
	private static final int INDEX_SPECTRALBAND = 1;
	

public Msg_spectralBand() 
{
	this.add(new RemoteParameterUint8("index"," sensor index"));
	this.add(new RemoteParameterSpectralBand("spectralband","spectralband"));
}
	
	
public Msg_spectralBand(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_spectralBand.name);
}


@Override
public String getDescription() 
{
	return(Msg_spectralBand.description);
}



public void setData(int index, float temperature)
{
	(( RemoteParameterUint8) this.get(Msg_spectralBand.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterSpectralBand) this.get(Msg_spectralBand.INDEX_SPECTRALBAND)).setSpectralband(temperature);
}


/**
 * get sensor index for sensor corresponding to this message
 * @return  index of sensor in sensor set
 */
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_spectralBand.INDEX_SENSOR)).getValue());
}


/**
 * get gradient
 * @return gradient 
 */
public float getSpectralband()
{
	return((( RemoteParameterSpectralBand) this.get(Msg_spectralBand.INDEX_SPECTRALBAND)).getSpectralband());
}





public static Msg_spectralBand getCommand(int id)
{
	Msg_spectralBand cmd;
	cmd = new Msg_spectralBand(id);
	
	return(cmd);
}



public static Msg_spectralBand getCommand(int command, int index,
		float temperature)
{
	Msg_spectralBand cmd;
	cmd = Msg_spectralBand.getCommand(command);
	cmd.setData(index, temperature);
	
	return(cmd);
}


}

