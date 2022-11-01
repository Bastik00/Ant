package de.hska.lat.behavior.behavior.manualControl2D;

import de.hska.lat.behavior.control.BehaviorControl;
import de.hska.lat.behavior.control.BehaviorControlSet;

public class ManualControlControlls extends BehaviorControlSet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5023564907135531591L;


public ManualControlControlls()
{
	this.add( new VelocityControl());
	this.add( new HeadingControl());
	this.add( new RotateControl());
	this.add( new XLocationControl());
	this.add( new YLocationControl());
	this.add( new ZLocationControl());
	
}




public VelocityControl getVelocityControl()
{
	for (BehaviorControl control : this)
	{
		if (control instanceof VelocityControl)
			return((VelocityControl)control);
	}
	
	return (null);
}




public HeadingControl getHeadingControl()
{
	for (BehaviorControl control : this)
	{
		if (control instanceof HeadingControl)
			return((HeadingControl)control);
	}
	
	return (null);
}


public RotateControl getRotateControl()
{
	for (BehaviorControl control : this)
	{
		if (control instanceof RotateControl)
			return((RotateControl)control);
	}
	
	return (null);
}


public XLocationControl getXLocationControl()
{
	for (BehaviorControl control : this)
	{
		if (control instanceof XLocationControl)
			return((XLocationControl)control);
	}
	return null;
}


public YLocationControl getYLocationControl()
{
	for (BehaviorControl control : this)
	{
		if (control instanceof YLocationControl)
			return((YLocationControl)control);
	}
	return null;
}


public ZLocationControl getZLocationControl()
{
	for (BehaviorControl control : this)
	{
		if (control instanceof ZLocationControl)
			return((ZLocationControl)control);
	}
	return null;
}

}
