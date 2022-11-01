package de.hska.lat.behavior.behavior;

import java.util.ArrayList;
import java.util.ListIterator;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.control.BehaviorControl;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.abstractRobot.RobotConnectionListener;

public class RobotBehavior<R extends Robot> implements RobotConnectionListener
{

	protected R robot;
	protected ArrayList<Arbiter> arbiters = new ArrayList<Arbiter>();
	
	
	
public RobotBehavior(R robot)
{
	this.robot = robot;
	this.robot.addConnectionListener(this);
}


/**
 * add a new arbiter 
 * @param arbiter
 */
protected void addArbiter(Arbiter arbiter)
{
	this.arbiters.add(arbiter);
}


public ListIterator<Arbiter> getArbiters()
{
	
	// TODO Auto-generated method stub
	return (this.arbiters.listIterator());
}



public ArrayList<BehaviorControl> getControls()
{
	
	ArrayList<BehaviorControl> controls = new ArrayList<BehaviorControl>();
	
	for (Arbiter arbiter : this.arbiters)
	{
		controls.addAll(arbiter.getControls());
	}
	
	return (controls);
}


public Arbiter getArbiterOnId(int id)
{
	for (Arbiter arbiter: this.arbiters)
	{
		if (arbiter.getId() == id)
		{
			return(arbiter);
		}
	}
	
	
	return(null);
}


@Override
public void connected(AbstractRobot<?, ?, ?> robot)
{
	// TODO Auto-generated method stub
	//this.arbiters.
}


@Override
public void disconnected(AbstractRobot<?, ?, ?> robot)
{
	// TODO Auto-generated method stub
	
}

}
