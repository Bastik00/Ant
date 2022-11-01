package de.hska.lat.robot.component.actor.generic.motor.velocityMotor;

import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.RpmMotor;
import de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol.Cmd_setMotorVelocity;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;
import de.hska.lat.robot.value.velocity.VelocityValue;

public class VelocityMotor extends RpmMotor
{

	
	protected VelocityValue velocity;
	protected VelocityValue velocityControlValue;
	
	
	public VelocityMotor(VelocityMotorMetaData metaData, VelocityMotorProtocol protocol)
	{
		super(metaData, protocol);
		// TODO Auto-generated constructor stub
		
		

		this.velocity = new VelocityValue(this.getComponentName());
		this.velocityControlValue = new VelocityValue(this.getComponentName() + "controll");
		
		this.velocityControlValue.addListener(new ComponentValueChangeListener()
		{
			
			@Override
			public void valueChanged(ComponentValue<?> componentValue)
			{
				VelocityMotor.this.remote_setVelocity(VelocityMotor.this.velocityControlValue.getValue());
				
			}
		});
	}
		
		

/**
 * set travel velocity of a motor
 * @param motorSpeed new velocity, velocity ranges from -1.0..1.0
 * @return true if command could be send , false otherwise
 */
public boolean remote_setVelocity(float velocity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setMotorVelocity.getCommand(this.componentProtocol.cmdSetValueId,this.localId,velocity)));
}



/**
 * return this motors rpm control value. motor can by controlled by changing this value
 * @return
 */
public VelocityValue getVelocityControlValue()
{
	return (this.velocityControlValue);
}



public void setVelocity(float velocity)
{
	this.velocity.setValue(velocity);
}

public VelocityValue getVelocity()
{
	return(this.velocity);
}		


}
