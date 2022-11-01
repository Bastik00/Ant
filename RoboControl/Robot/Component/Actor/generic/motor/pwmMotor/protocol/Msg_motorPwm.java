package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;




import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_motorPwm extends RemoteMessage
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3238174142877903652L;
	protected static final String name = "motorPwm";
	protected static final String description = "actual PWM Value for a motor";

	protected static final int  MOTOR_INDEX		= 0;
	protected static final int  PWM_INDEX		= 1;
	

public Msg_motorPwm() 
{
	this.add(new RemoteParameterUint8("motor ","index of motor"));
	this.add(new RemoteParameterPwm("actual PWM value for motor"));
}
	
	
public Msg_motorPwm(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_motorPwm.name);
}


@Override
public String getDescription() 
{
	return(Msg_motorPwm.description);
}



private void setData(int index, float pwm)
{
	(( RemoteParameterUint8) this.get(Msg_motorPwm.MOTOR_INDEX)).setValue(index);
	(( RemoteParameterPwm) this.get(Msg_motorPwm.PWM_INDEX)).setValue(pwm);
}







/**
 * get motor Pwm value
 * @param index
 * @return
 */

public float getPwm()
{
	
	return((( RemoteParameterPwm) this.get(Msg_motorPwm.PWM_INDEX)).getValue());

}




public static Msg_motorPwm getCommand(int id)
{
	Msg_motorPwm cmd;
	cmd = new Msg_motorPwm(id);
	
	return(cmd);
}



public static Msg_motorPwm getCommand(int command,int index, float pwm)
{
	Msg_motorPwm cmd;
	cmd = Msg_motorPwm.getCommand(command);
	cmd.setData(index, pwm);
	
	return(cmd);
}







}

