package de.hska.lat.robot.component.generic.spectralBand;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.actor.Actor;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_getSpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Cmd_setSpectralBand;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;




public class SpectralBand extends Actor<SpectralBandChangeNotifier, ComponentSettingsChangeNotifier,SpectralBandProtocol, SpectralBandValue > implements ComponentValueChangeListener
{
	protected SpectralBandValue spectralBandValue;
	
public SpectralBand(ComponentMetaData metaData,SpectralBandProtocol protocol)
{
	super(metaData, protocol);
	
	this.controlValue = new SpectralBandValue(this.getComponentName()+"-control");
	this.controlValue.addListener(this);
	this.spectralBandValue = new SpectralBandValue(this.getComponentName());
	
}


/**
 * get this motor velocity value
 * @return motors velocity value
 */

/*
public VelocityValue getVelocityValue()
{

	return (this.velocity);
}
*/

/**
 * set this motors new velocity. This value comes from remote system and corresponds to the actually measured value.    
 * @param velocity new velocity of this motor
 */

/*
public void setVelocity(float velocity)
{
	this.velocity.setValue(velocity);
}
*/
/**
 * set actual measured pwm value for this motor
 * @param spectralBandValue motors revolutions per minute value
 */

public void setSpectralBandValue(float spectralBandValue)
{
	this.spectralBandValue.setValue(spectralBandValue);
}


/**
 * get actual measured pwm value for this motor
 * @param spectralBandValue actual pwm 
 */
public SpectralBandValue getSpectralBandValue()
{
	return(this.spectralBandValue);
}

@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.spectralBandValue.setValue(componentValue.getValue());
	
}

public boolean remote_setValue(float value)
{
	if (this.componentProtocol == null)
	{
		return (false);
	}
	return (sendData(Cmd_setSpectralBand.getCommand(
			this.componentProtocol.cmdSetValueId, this.localId, value)));
}

@Override
public boolean remote_getValue()
{
	{
		if (this.componentProtocol==null)
			return(false);
		
		
		return(sendData(Cmd_getSpectralBand.getCommand(this.componentProtocol.cmdGetValueId,this.localId)));
	}
}


public SpectralBand getSpectralBand()
{
	return (this);
}










}
