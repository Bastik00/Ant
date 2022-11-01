package de.hska.lat.robot.component.generic.compass.protocol;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.math.vector.FloatVector3D;

public class Stream_geomagneticField extends RemoteStream
{

	private static final long serialVersionUID = 2795308331426270012L;

	public static final int X_AXIS =0 ;
	public static final int Y_AXIS =1 ;
	public static final int Z_AXIS =2 ;
	
	protected static final String name = "compassData";
	protected static final String description = "compass data (magnetic field values) for robot";
	
	
	public Stream_geomagneticField()
	{
		this.add(new RemoteParameterMagneticField("X","value on x axis"));
		this.add(new RemoteParameterMagneticField("Y","value on y axis"));
		this.add(new RemoteParameterMagneticField("Z","value on z axis"));
	}
	
	public Stream_geomagneticField(int command)
	{
		this();
		this.setId(command);
	}

	
	@Override
	public String getName() 
	{
		return(Stream_geomagneticField.name);
	}


	@Override
	public String getDescription() 
	{
		return(Stream_geomagneticField.description);
	}
	
	public void setValues(float[] values)
	{
				(( RemoteParameterMagneticField) this.get(X_AXIS)).setValue(values[X_AXIS]);
				(( RemoteParameterMagneticField) this.get(Y_AXIS)).setValue(values[Y_AXIS]);
				(( RemoteParameterMagneticField) this.get(Z_AXIS)).setValue(values[Z_AXIS]);
		
	}
	
	
	
	public FloatVector3D getGeomagneticField(int index)
	{
		FloatVector3D acceleration;
		
		acceleration = new FloatVector3D((( RemoteParameterMagneticField) this.get(Stream_geomagneticField.X_AXIS)).getValue(),
								(( RemoteParameterMagneticField) this.get(Stream_geomagneticField.Y_AXIS)).getValue(),
								(( RemoteParameterMagneticField) this.get(Stream_geomagneticField.Z_AXIS)).getValue());
		
		return (acceleration);
	}

	

	
	public static Stream_geomagneticField getCommand(int command)
	{
		Stream_geomagneticField cmd;
		cmd = new Stream_geomagneticField(command);
		
		return(cmd);
	}


	public static Stream_geomagneticField getCommand(int command, float[] values)
	{
		Stream_geomagneticField cmd;
		cmd = Stream_geomagneticField.getCommand(command);
		cmd.setValues( values);
		
		return(cmd);
	}
	
	
}
