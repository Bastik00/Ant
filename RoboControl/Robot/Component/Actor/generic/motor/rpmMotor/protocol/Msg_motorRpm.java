package de.hska.lat.robot.component.actor.generic.motor.rpmMotor.protocol;




import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_motorRpm extends RemoteMessage
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3238174142877903652L;
	protected static final String name = "motorRpm";
	protected static final String description = "revolvings per minute for motor";

	protected static final int  MOTOR_INDEX		= 0;
	protected static final int  RPM_INDEX		= 1;
	

public Msg_motorRpm() 
{
	this.add(new RemoteParameterUint8("motor ","index of motor"));
	this.add(new RemoteParameterMotorRpm("revolvings per minute for motor"));
}
	
	
public Msg_motorRpm(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_motorRpm.name);
}


@Override
public String getDescription() 
{
	return(Msg_motorRpm.description);
}



private void setData(int index, float rpm)
{
	(( RemoteParameterUint8) this.get(Msg_motorRpm.MOTOR_INDEX)).setValue(index);
	(( RemoteParameterMotorRpm) this.get(Msg_motorRpm.RPM_INDEX)).setValue(rpm);
}







/**
 * get motor Pwm value
 * @param index
 * @return
 */

public float getRpm()
{
	
	return((( RemoteParameterMotorRpm) this.get(Msg_motorRpm.RPM_INDEX)).getValue());

}




public static Msg_motorRpm getCommand(int id)
{
	Msg_motorRpm cmd;
	cmd = new Msg_motorRpm(id);
	
	return(cmd);
}



public static Msg_motorRpm getCommand(int command,int index, float rpm)
{
	Msg_motorRpm cmd;
	cmd = Msg_motorRpm.getCommand(command);
	cmd.setData(index, rpm);
	
	return(cmd);
}







}

