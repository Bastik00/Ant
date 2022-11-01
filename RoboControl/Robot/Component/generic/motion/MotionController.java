package de.hska.lat.robot.component.generic.motion;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_stop;


public class MotionController<P extends MotionProtocol>  extends RobotComponent<ComponentChangeNotifier,ComponentSettingsChangeNotifier, P>
{

public MotionController(ComponentMetaData metaData,
		P protocol)
{
	super(metaData, protocol);
	// TODO Auto-generated constructor stub
}


	public static final int AXIS_X		= 0;
	public static final int AXIS_Y		= 1;

	
	
	
	public boolean stop()
	{
		return(this.remote_stop());
	}

	



public boolean setAsOrigin()
{
	// TODO Auto-generated method stub
	return false;
}

	
private boolean remote_stop()
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_stop.getCommand(this.componentProtocol.cmdStop)));
}
	
	
	


}
