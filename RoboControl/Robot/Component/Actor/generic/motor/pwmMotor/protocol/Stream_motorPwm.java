package de.hska.lat.robot.component.actor.generic.motor.pwmMotor.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;







/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Stream_motorPwm extends RemoteStream
{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 3238174142877903652L;
	protected static final String name = "motorsPwm";
	protected static final String description = "actual PWM Value for motors";


public Stream_motorPwm() 
{
}
	
	
public Stream_motorPwm(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_motorPwm.name);
}


@Override
public String getDescription() 
{
	return(Stream_motorPwm.description);
}



public void setData(float... pwm)
{
	int enumerator;
	RemoteParameterPwm parameter;
	
	enumerator = 0;
	
	for (float value : pwm)
	{
		parameter = new RemoteParameterPwm("actual PWM value for motor "+enumerator);
		parameter.setValue(value);
		this.add(parameter);
	}
}





@Override
public void parseDataPacketData(RemoteDataPacket packet)
{
	int dataIndex;
	int enumerator;
	ByteBuffer dataBuffer;
	RemoteParameterPwm parameter;
	
	dataIndex=0;
	
	dataBuffer = packet.getDataBuffer();
	enumerator = 0;
	

	
	for (dataIndex = 0; dataIndex<dataBuffer.capacity()-1;enumerator++)
	{
		parameter = new RemoteParameterPwm("actual back emf value for motor "+enumerator);
		dataIndex+=parameter.parseFromBuffer(dataBuffer, dataIndex);
		this.add(parameter);
	}
	

	
	
}

/**
 * get motor Pwm value
 * @param index
 * @return
 */

public float getPwm(int index)
{
	return((( RemoteParameterPwm) this.get(index)).getValue());
}




public static Stream_motorPwm getCommand(int id)
{
	Stream_motorPwm cmd;
	cmd = new Stream_motorPwm(id);
	
	return(cmd);
}



public static Stream_motorPwm getCommand(int command, float...pwm)
{
	Stream_motorPwm cmd;
	cmd = Stream_motorPwm.getCommand(command);
	cmd.setData(pwm);
	
	return(cmd);
}







}

