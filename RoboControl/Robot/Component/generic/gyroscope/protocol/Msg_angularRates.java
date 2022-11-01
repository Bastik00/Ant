package de.hska.lat.robot.component.generic.gyroscope.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.math.vector.AngularVector3D;

public class Msg_angularRates extends RemoteMessage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	private static final int SENSOR_INDEX					= 0;
	private static final int INDEX_AXIS_X					= 1;
	private static final int INDEX_AXIS_Y					= 2;
	private static final int INDEX_AXIS_Z					= 3;

	
	protected static final String name = "angularRates";
	protected static final String description = "angular rates from a gyroscope sensor";
	
	
	
	
	
	
public Msg_angularRates()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterAngularRate("X","angular rate on x axis"));
	this.add(new RemoteParameterAngularRate("Y","angular rate on y axis"));
	this.add(new RemoteParameterAngularRate("Z","angular rate on z axis"));
}


public Msg_angularRates(int command)
{
	this();
	this.setId(command);
}



public String getName()
{
	return(Msg_angularRates.name);
}


public String getDescription()
{
	return(Msg_angularRates.description);
}

public void setData(int index, AngularVector3D angularRates)
{
	(( RemoteParameterUint8) this.get(Msg_angularRates.SENSOR_INDEX)).setValue(index);
	(( RemoteParameterAngularRate) this.get(Msg_angularRates.INDEX_AXIS_X)).setValue(angularRates.x);
	(( RemoteParameterAngularRate) this.get(Msg_angularRates.INDEX_AXIS_Y)).setValue(angularRates.y);
	(( RemoteParameterAngularRate) this.get(Msg_angularRates.INDEX_AXIS_Z)).setValue(angularRates.z);
	
}


public int getIndex()
{
	return ((( RemoteParameterUint8) this.get(Msg_angularRates.SENSOR_INDEX)).getValue());
}




public AngularVector3D getAngularRates()
{
	
	AngularVector3D angularRates;
	
	angularRates = new AngularVector3D((( RemoteParameterAngularRate) this.get(Msg_angularRates.INDEX_AXIS_X)).getValue(),
					(( RemoteParameterAngularRate) this.get(Msg_angularRates.INDEX_AXIS_Y)).getValue(),
					(( RemoteParameterAngularRate) this.get(Msg_angularRates.INDEX_AXIS_Z)).getValue());
	
	return (angularRates);
}


public static Msg_angularRates getCommand(int command)
{
	Msg_angularRates cmd;
	cmd = new Msg_angularRates(command);
	
	return(cmd);
}



public static Msg_angularRates getCommand(int command,int index, AngularVector3D angularRates)
{
	Msg_angularRates cmd;
	cmd = Msg_angularRates.getCommand(command);
	cmd.setData(index, angularRates);
	
	return(cmd);
}






}
