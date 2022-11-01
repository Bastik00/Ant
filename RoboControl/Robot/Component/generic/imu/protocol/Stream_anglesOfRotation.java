package de.hska.lat.robot.component.generic.imu.protocol;




import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.AngularVector3D;



public class Stream_anglesOfRotation extends RemoteStream
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;


	
	protected static final String name = "imuData";
	protected static final String description = "imu data (angles) for robot";
	
	
	
	public static final int INDEX_PITCH					= 0;
	public static final int INDEX_ROLL					= 1;
	public static final int INDEX_YAW					= 2;
	
	
public Stream_anglesOfRotation()
{
	this.add(new RemoteParameterImuRate("X","rotation on x axis"));
	this.add(new RemoteParameterImuRate("Y","rotation on y axis"));
	this.add(new RemoteParameterImuRate("Z","rotation on z axis"));
}


public Stream_anglesOfRotation(int command)
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Stream_anglesOfRotation.name);
}


@Override
public String getDescription() 
{
	return(Stream_anglesOfRotation.description);
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



public AngularVector3D getAnglesOfRotation(int index)
{
	AngularVector3D anglesOfRotation;
	
	index *=3;
	
	
	anglesOfRotation = new AngularVector3D((( RemoteParameterImuRate) this.get(index++)).getValue(),
							(( RemoteParameterImuRate) this.get(index++)).getValue(),
							(( RemoteParameterImuRate) this.get(index++)).getValue());
	
	return (anglesOfRotation);
}





public static Stream_anglesOfRotation getCommand(int command)
{
	Stream_anglesOfRotation cmd;
	cmd = new Stream_anglesOfRotation(command);
	
	return(cmd);
}



public static Stream_anglesOfRotation getCommand(int command, AngularVector3D anglesOfRotation)
{
	Stream_anglesOfRotation cmd;
	cmd = Stream_anglesOfRotation.getCommand(command);
	cmd.setData(anglesOfRotation);
	
	return(cmd);
}




}
