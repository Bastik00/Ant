package de.hska.lat.behavior.behavior.manualControl2D;

import java.util.ArrayList;

import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.control.BehaviorControl;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.pose.Pose3D;

public abstract class ManualControl2D extends Behavior
{
	
	protected ManualControlControlls controls;
	
	protected VelocityControl velocity;
	protected HeadingControl heading;
	protected RotateControl rotate;
	protected XLocationControl x;
	protected YLocationControl y;
	protected ZLocationControl z;
	
	
	protected ArbiterConnection motionOutput;
	protected Pose3D pose;
	
	public ManualControl2D(Robot robot, int id, int period)
	{
		super(id, period);
		this.name = "manual control";
		
		this.controls = new ManualControlControlls();
		
		this.velocity = this.controls.getVelocityControl();
		this.heading = this.controls.getHeadingControl();
		this.rotate = this.controls.getRotateControl();
		this.x = this.controls.getXLocationControl();
		this.y = this.controls.getYLocationControl();
		this.z = this.controls.getZLocationControl();
		
		this.pose = robot.getPose();
		
		this.start();
		
		
	}

	
	
	public RotateControl getRotateControl()
	{
		return(this.controls.getRotateControl());
	}
	
	
	
	public VelocityControl getVelocityControl()
	{
		return(this.controls.getVelocityControl());
	}
	
	
	public HeadingControl getHeadingControl()
	{
		return(this.controls.getHeadingControl());
	}
	

	public XLocationControl getXLocationControl()
	{
		return this.controls.getXLocationControl();
	}

	
	public YLocationControl getYLocationControl()
	{
		return this.controls.getYLocationControl();
	}
	

	public ZLocationControl getZLocationControl()
	{
		return this.controls.getZLocationControl();
	}
	
	
	public void setOutput(ArbiterConnection motionOutput)
	{
		this.motionOutput = motionOutput;
	}
	
	protected abstract void react();
	
	
	
	@Override
	protected void behave()
	{
		
		if (controls.hasChanged())
		{
			react();
		}
		
		
	}



	public ArrayList<BehaviorControl> getControls()
	{
		
		ArrayList<BehaviorControl> controlls = new ArrayList<BehaviorControl>();
		controlls.add(velocity);
		controlls.add(heading);
		// added but not sure the specific group needs this:
		//controlls.add(rotate);
		//controlls.add(x);
		//controlls.add(y);
		//controlls.add(z);
		
		 return(controlls);
	}



	public Pose3D getPose()
	{
		return (this.pose);
	}

	
	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.motionOutput);
		
		return(list);
	}
		
	
}
