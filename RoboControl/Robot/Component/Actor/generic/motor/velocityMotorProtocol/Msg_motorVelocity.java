package de.hska.lat.robot.component.actor.generic.motor.velocityMotorProtocol;




import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol.RemoteParameterMotorVelocity;






/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_motorVelocity extends RemoteMessage
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3238174142877903652L;
	protected static final String name = "motorVelocity";
	protected static final String description = "motors velocity in m/s";

	protected static final int  MOTOR_INDEX			= 0;
	protected static final int  VELOCITY_INDEX		= 1;
	

public Msg_motorVelocity() 
{
	this.add(new RemoteParameterUint8("motor ","index of motor"));
	this.add(new RemoteParameterMotorVelocity("motors velocity in m/s"));
}
	
	
public Msg_motorVelocity(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_motorVelocity.name);
}


@Override
public String getDescription() 
{
	return(Msg_motorVelocity.description);
}



private void setData(int index, float rpm)
{
	(( RemoteParameterUint8) this.get(Msg_motorVelocity.MOTOR_INDEX)).setValue(index);
	(( RemoteParameterMotorVelocity) this.get(Msg_motorVelocity.VELOCITY_INDEX)).setValue(rpm);
}







/**
 * get motor Pwm value
 * @param index
 * @return
 */

public float getVelocity()
{
	
	return((( RemoteParameterMotorVelocity) this.get(Msg_motorVelocity.VELOCITY_INDEX)).getValue());

}




public static Msg_motorVelocity getCommand(int id)
{
	Msg_motorVelocity cmd;
	cmd = new Msg_motorVelocity(id);
	
	return(cmd);
}



public static Msg_motorVelocity getCommand(int command,int index, float velocity)
{
	Msg_motorVelocity cmd;
	cmd = Msg_motorVelocity.getCommand(command);
	cmd.setData(index, velocity);
	
	return(cmd);
}







}

