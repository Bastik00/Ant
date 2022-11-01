package de.hska.lat.robot.component.generic.compass.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;
import de.hska.lat.math.vector.FloatVector3D;

public class Msg_geomagneticField extends RemoteMessage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500019264652495992L;

	
	
	private static final int SENSOR_INDEX					= 0;
	private static final int INDEX_AXIS_X					= 1;
	private static final int INDEX_AXIS_Y					= 2;
	private static final int INDEX_AXIS_Z					= 3;

	
	protected static final String name = "msgMagneticField";
	protected static final String description = "magnetic field measured by an magnetometer sensor";
	
	
	
	
	
	
public Msg_geomagneticField()
{
	this.add(new RemoteParameterUint8("index","sensor index"));
	this.add(new RemoteParameterMagneticField("offset X","offset in x direction"));
	this.add(new RemoteParameterMagneticField("offset Y","offset in y direction"));
	this.add(new RemoteParameterMagneticField("offset Z","offset in z direction"));
}


public Msg_geomagneticField(int command)
{
	this();
	this.setId(command);
}


public String getName()
{
	return(Msg_geomagneticField.name);
}


public String getDescription()
{
	return(Msg_geomagneticField.description);
}



public void setData(int index, FloatVector3D acceleration )
{
	(( RemoteParameterUint8) this.get(Msg_geomagneticField.SENSOR_INDEX)).setValue(index);
	(( RemoteParameterMagneticField) this.get(Msg_geomagneticField.INDEX_AXIS_X)).setValue(acceleration.x);
	(( RemoteParameterMagneticField) this.get(Msg_geomagneticField.INDEX_AXIS_Y)).setValue(acceleration.y);
	(( RemoteParameterMagneticField) this.get(Msg_geomagneticField.INDEX_AXIS_Z)).setValue(acceleration.z);
	
}




public FloatVector3D getGeomagneticField()
{
	FloatVector3D acceleration;
	
	acceleration = new FloatVector3D((( RemoteParameterMagneticField) this.get(Msg_geomagneticField.INDEX_AXIS_X)).getValue(),
							(( RemoteParameterMagneticField) this.get(Msg_geomagneticField.INDEX_AXIS_Y)).getValue(),
							(( RemoteParameterMagneticField) this.get(Msg_geomagneticField.INDEX_AXIS_Z)).getValue());
	
	return (acceleration);
}


public int getIndex( )
{
	return((( RemoteParameterUint8) this.get(Msg_geomagneticField.SENSOR_INDEX)).getValue());
	
}

public static Msg_geomagneticField getCommand(int command)
{
	Msg_geomagneticField cmd;
	cmd = new Msg_geomagneticField(command);
	
	return(cmd);
}

public static Msg_geomagneticField getCommand(int command, int index, FloatVector3D acceleration)
{
	Msg_geomagneticField cmd;
	cmd = Msg_geomagneticField.getCommand(command);
	cmd.setData(index, acceleration );
	
	return(cmd);
}







}
