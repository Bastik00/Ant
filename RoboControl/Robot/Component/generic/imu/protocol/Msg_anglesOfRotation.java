package de.hska.lat.robot.component.generic.imu.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.math.vector.AngularVector3D;



public class Msg_anglesOfRotation extends RemoteMessage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	public static final int INDEX_PITCH					= 0;
	public static final int INDEX_ROLL					= 1;
	public static final int INDEX_YAW					= 2;

	
	protected static final String name = "anglesOfRotation";
	protected static final String description = "imu data (angles) ";
	
	
	
	
	
	
public Msg_anglesOfRotation()
{
	this.add(new RemoteParameterImuRate("X","rotation on x axis"));
	this.add(new RemoteParameterImuRate("Y","rotation on y axis"));
	this.add(new RemoteParameterImuRate("Z","rotation on z axis"));
}


public Msg_anglesOfRotation(int command)
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_anglesOfRotation.name);
}


@Override
public String getDescription() 
{
	return(Msg_anglesOfRotation.description);
}



public void setData(AngularVector3D rates)
{
			(( RemoteParameterImuRate) this.get(Msg_anglesOfRotation.INDEX_PITCH)).setValue(rates.x);
			(( RemoteParameterImuRate) this.get(Msg_anglesOfRotation.INDEX_ROLL)).setValue(rates.y);
			(( RemoteParameterImuRate) this.get(Msg_anglesOfRotation.INDEX_YAW)).setValue(rates.z);
	
}


public AngularVector3D getAnglesOfRotation()
{
	AngularVector3D rates = new AngularVector3D(
				((RemoteParameterImuRate) this.get(Msg_anglesOfRotation.INDEX_PITCH)).getValue(),
				((RemoteParameterImuRate) this.get(Msg_anglesOfRotation.INDEX_ROLL)).getValue(),
				((RemoteParameterImuRate) this.get(Msg_anglesOfRotation.INDEX_YAW)).getValue());
	
	return (rates);
}


public static Msg_anglesOfRotation getCommand(int command)
{
	Msg_anglesOfRotation cmd;
	cmd = new Msg_anglesOfRotation(command);
	
	return(cmd);
}



public static Msg_anglesOfRotation getCommand(int command, AngularVector3D rates)
{
	Msg_anglesOfRotation cmd;
	cmd = Msg_anglesOfRotation.getCommand(command);
	cmd.setData(rates);
	
	return(cmd);
}






}
