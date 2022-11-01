package de.hska.lat.robot.component.actor.generic.motor.rpmMotor;




import de.hska.lat.robot.component.actor.generic.motor.pwmMotor.PwmMotor;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.Cmd_setMotorRpm;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;

import de.hska.lat.robot.value.rpm.RpmValue;

public class RpmMotor extends PwmMotor
{

	protected RpmValue rpm;
	protected RpmValue rpmControlValue;
	private int tickPerRevolution;
	private int wheelDiameter;

	
	
	public RpmMotor(RpmMotorMetaData metaData, RpmMotorProtocol protocol)
	{
		super(metaData, protocol);
		this.rpm = new RpmValue(this.getComponentName(),metaData.getRpmRange());
		this.rpmControlValue = new RpmValue(this.getComponentName() + "controll",metaData.getRpmRange());
		
		this.rpmControlValue.addListener(new ComponentValueChangeListener(){

			@Override
			public void valueChanged(ComponentValue<?> componentValue)
			{
				RpmMotor.this.remote_setRpm(RpmMotor.this.rpmControlValue.getValue());
			}
			
		});
		
	}


	
	
/**
 * set actual measured value for this motors revolutions per minute
 * @param rpm motors revolutions per minute value
 */

public void setRpm(float rpm)
{
	this.rpm.setValue(rpm);
}


/**
 * get actual measured value for this motors revolutions per minute
 * @param rpm actual revolutions per minute 
 */
public float getRpm()
{
	return(this.rpm.getValue());
}



/**
 * return this motors RpmValue instance for revolutions per minute (rpm)
 * @return this motors rpm value
 */
public RpmValue getRpmValue()
{

	return(this.rpm);
}



/**
 * set travel velocity of a motor
 * @param motorSpeed new velocity, velocity ranges from -1.0..1.0
 * @return true if command could be send , false otherwise
 */
public boolean remote_setRpm(float velocity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setMotorRpm.getCommand(this.componentProtocol.cmdSetValueId,this.localId,velocity)));

}




/**
 * return this motors rpm control value. motor can by controlled by changing this value
 * @return
 */
public RpmValue getRpmControlValue()
{
	return (this.rpmControlValue);
}



public void setSettingsRpmMotor( int tickPerRevolution, int wheelDiameter,int maxRpm )

{
	this.setTickPerRevolution(tickPerRevolution);
	this.setWheelDiameter(wheelDiameter);
	this.setMaxRpm(maxRpm);
	System.out.println(tickPerRevolution);
	System.out.println(wheelDiameter);
	System.out.println(maxRpm);
	this.notifySetupChanged();
}
public int getTickPerRevolution()
{
	return tickPerRevolution;
}



public void setTickPerRevolution(int tickPerRevolution)
{
	this.tickPerRevolution = tickPerRevolution;
}



public int getWheelDiameter()
{
	return wheelDiameter;
}



public void setWheelDiameter(int wheelDiameter)
{
	this.wheelDiameter = wheelDiameter;
}



public int getMaxRpm()
{
	return ((int) this.rpm.getMaxRange());
}



public void setMaxRpm(int maxRpm)
{
	this.rpm.setRange(-maxRpm, maxRpm);
}
}