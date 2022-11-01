package de.hska.lat.robot.component.actor.generic.voiceDecoder;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.actor.Actor;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_getMotorPwm;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_motorBrake;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_setMotorPwm;
import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol.Cmd_setMotorSettings;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.pwm.PwmValue;




public class VoiceDecoder extends Actor<VoiceDecoderChangeNotifier, ComponentSettingsChangeNotifier,VoiceDecoderProtocol, PwmValue > implements ComponentValueChangeListener
{
	
	
	
	

	protected PwmValue pwm;
	
	protected boolean reverse;
	
public VoiceDecoder(ComponentMetaData metaData,VoiceDecoderProtocol protocol)
{
	super(metaData, protocol);
	
	this.controlValue = new PwmValue(this.getComponentName()+"-control");
	this.controlValue.addListener(this);
	this.pwm = new PwmValue(this.getComponentName());
	
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
 * @param pwm motors revolutions per minute value
 */

public void setPwm(float pwm)
{
	this.pwm.setValue(pwm);
}


/**
 * get actual measured pwm value for this motor
 * @param spectralBandValue actual pwm 
 */
public float getPwm()
{
	return(this.pwm.getValue());
}


/**
 * return this motors pwmValue instance 
 * @return this motors pwm value
 */
public PwmValue getPwmValue()
{

	return(this.pwm);
}
/**
 * 
 * @return
 */
public boolean isReverse()
{
	return(this.reverse);
}


/**
 * turn motor on or of
 * @param status turn motor on (true) or off (false)
 * @return true if command could be send , false otherwise
 */
public boolean remote_motorOn(boolean status)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	
	return(sendData(Cmd_motorBrake.getCommand(this.componentProtocol.cmdMotorOnId,this.localId)));
}

/**
 * Brake a motor
 * @return true if command could be send , false otherwise
 */
public boolean remote_brake()
{
	if (this.componentProtocol==null)
		return(false);
	
	this.controlValue.setValue(0);
	
	return(sendData(Cmd_motorBrake.getCommand(this.componentProtocol.cmdMotorBrakeId,this.localId)));
}

/**
 * stop a motor 
 * @return true if command could be send , false otherwise
 */
public boolean remote_stop()
{
	if (this.componentProtocol==null)
		return(false);
	
	this.controlValue.setValue(0);
	
	return(sendData(Cmd_motorBrake.getCommand(this.componentProtocol.cmdMotorBrakeId,this.localId)));
}




public boolean remote_drive(float pwm)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setMotorPwm.getCommand(this.componentProtocol.cmdSetValueId,this.localId, pwm)));
}





public boolean remote_setSettings(int tickPerRevolution)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setMotorSettings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,tickPerRevolution)));
}





@Override
public void valueChanged(ComponentValue<?> componentValue)
{
	this.remote_drive(this.controlValue.getValue());
	
}


@Override
public boolean remote_getValue()
{
	{
		if (this.componentProtocol==null)
			return(false);
		
		
		return(sendData(Cmd_getMotorPwm.getCommand(this.componentProtocol.cmdGetValueId,this.localId)));
	}
}










}
