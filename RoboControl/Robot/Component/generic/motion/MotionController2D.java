package de.hska.lat.robot.component.generic.motion;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_drive;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_move2D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_moveTo2D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_rotate2D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_rotateTo;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_stop;

public class MotionController2D extends MotionController<MotionProtocol2D>
{

	public MotionController2D(ComponentMetaData metaData, MotionProtocol2D protocol)
	{
		super(metaData, protocol);
		// TODO Auto-generated constructor stub
	}

	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;

	public boolean stop()
	{
		return (this.remote_stop());
	}

	public boolean speed(int speed)
	{
		return false;
	}

	public boolean move(float velocity, float heading)
	{
		// System.out.println("SLAM velocity: " + velocity);
		return (this.remote_move(velocity, heading));
	}

	public boolean moveTo(float xLocation, float yLocation, float velocity)
	{
		System.out.println("SLAM: MoveTo(" + xLocation + "," + yLocation + ")");
		return (this.remote_moveTo(xLocation, yLocation, velocity));

	}

	public boolean rotateTo(float x, float y, float z, float angularVelocity)
	{
		return (this.remote_rotateTo(x, y, z, angularVelocity));
	}

	public boolean rotate(float heading, float angularVelocity)
	{
		return (this.remote_rotate(heading, angularVelocity));
	}

	public boolean drive(float xVelocity, float yVelocity)
	{
		return (this.remote_drive(xVelocity, yVelocity));
	}

	public boolean setAsOrigin()
	{
		// TODO Auto-generated method stub
		return false;
	}

	private boolean remote_stop()
	{
		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_stop.getCommand(this.componentProtocol.cmdStop)));
	}

	public boolean remote_rotateTo(float x, float y, float z, float angularVelocity)
	{
		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_rotateTo.getCommand(this.componentProtocol.cmdRotateToId, x, y, z, angularVelocity)));
	}

	public boolean remote_rotate(float heading, float angularVelocity)
	{
		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_rotate2D.getCommand(this.componentProtocol.cmdRotateId, heading, angularVelocity)));
	}

	public boolean remote_moveTo(float xLocation, float yLocation, float velocity)
	{
		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_moveTo2D.getCommand(this.componentProtocol.cmdMoveToId, xLocation, yLocation, velocity)));
	}

	public boolean remote_move(float velocity, float heading)
	{
		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_move2D.getCommand(this.componentProtocol.cmdMoveId, velocity, heading)));
	}

	public boolean remote_drive(float xVelocity, float yVelocity)
	{
		if (this.componentProtocol == null)
			return (false);

		return (sendData(Cmd_drive.getCommand(this.componentProtocol.cmdDriveId, xVelocity, yVelocity, 0)));
	}

}
