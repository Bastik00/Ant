package de.hska.lat.robot.component.generic.motion;

import de.hska.lat.robot.component.ComponentMetaData;

import de.hska.lat.robot.component.generic.motion.protocol.Cmd_drive;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_move;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_moveTo3D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_rotate3D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_rotateTo;


public class MotionController3D  extends MotionController <MotionProtocol3D>
{

public MotionController3D(ComponentMetaData metaData,
		MotionProtocol3D protocol)
{
	super(metaData, protocol);
	// TODO Auto-generated constructor stub
}


	public static final int AXIS_X		= 0;
	public static final int AXIS_Y		= 1;
	public static final int AXIS_Z		= 2;
	
	
	

	
public boolean move(float x,float y, float z, float velocity)
{
	return(this.remote_move(x, y, z, velocity));
}


public boolean moveTo(float x,float y, float z, float velocity)
{
	return(this.remote_moveTo(x, y, z, velocity));
	
}


	
public boolean rotateTo(float x,float y, float z, float angularVelocity)
{
	return(this.remote_rotateTo( x, y,  z,  angularVelocity));
}


public boolean rotate(float x,float y, float z, float angularVelocity)
{
	return(this.remote_rotate( x, y,  z,  angularVelocity));
}


public boolean drive(float xVelocity,float yVelocity, float zVelocit)
{
	return(this.remote_drive( xVelocity, yVelocity,  zVelocit));
}




	
	
public boolean remote_rotateTo(float x,float y, float z, float angularVelocity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_rotateTo.getCommand(this.componentProtocol.cmdRotateToId, x, y, z,  angularVelocity)));
}
	
	


public boolean remote_rotate(float x,float y, float z, float angularVelocity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_rotate3D.getCommand(this.componentProtocol.cmdRotateId, x, y, z,  angularVelocity)));
}
	
	


public boolean remote_moveTo(float x,float y, float z, float velocity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_moveTo3D.getCommand(this.componentProtocol.cmdMoveToId, x, y, z,  velocity)));
}


public boolean remote_move(float x,float y, float z, float velocity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_move.getCommand(this.componentProtocol.cmdMoveId, x, y, z,  velocity)));
}





public boolean remote_drive( float xVelocity,float yVelocity, float zVelocit)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_drive.getCommand(this.componentProtocol.cmdDriveId, xVelocity, yVelocity, zVelocit)));
}



}
