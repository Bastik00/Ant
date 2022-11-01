package de.hska.lat.robot.component.generic.navigator;

import de.hska.lat.math.vector.FloatVector2D;
import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.motion.MotionController;
import de.hska.lat.robot.pose.Pose3D;
import de.hska.lat.robot.pose.PoseListener;



public abstract class Navigator<M extends MotionController<?>> extends RobotComponent<NavigatorChangeNotifier,ComponentSettingsChangeNotifier,ComponentProtocol > implements PoseListener  
{
	protected NavigationPath navigationPath;

	protected M motionController;
	
	protected boolean active;
	
	protected int index;
	
	protected Robot robot;
	


public Navigator(ComponentMetaData metaData, ComponentProtocol protocol, Robot robot, M motionController)
{
	super(metaData, protocol);
	this.robot = robot;
	robot.getPose().addPoseListener(this);
	active = false;
}




public boolean addNavPoint(float x, float z, float speed)
{
	NavigationPoint point = new NavigationPoint(new FloatVector3D(x, 0, z));
	point.setVelocity(speed);
	return navigationPath.add(point);
}





public boolean isActive()
{
	return active;
}




public boolean clearNavPointList()
{
	navigationPath.clear();
	return navigationPath.size() == 0;
}





public boolean gotoNavPoint(byte index)
{
	return moveTo(navigationPath.get(index));
}




abstract protected boolean moveTo(NavigationPoint navigationPoint);




public boolean selectNavPoint(byte index)
{
	// TODO Auto-generated method stub
	return false;
}




public void executePath()
{
	this.active = true;
	this.index = 0;
}




public void executePath(NavigationPath navigationPath, int index)
{
	this.active = true;
	this.navigationPath = navigationPath;
	this.index = index;
}



public boolean moveTo(float xPos, float yPos, float speed)
{
	// TODO Auto-generated method stub
	return false;
}




@Override
public void poseChanged(Pose3D pose)
{
	// TODO Auto-generated method stub
	
}




public void updateLocation(FloatVector2D position)
{
	// TODO Auto-generated method stub
	
}


/*	

@Override	
public void decodeCommand(DataPacket dataPacket)
{
	int index;
	switch(dataPacket.getCommand())
	{
	case NavigatorProtocol.MSG_NAV_POINT_REACHED:
		
		index=dataPacket.getDataInt8(NavigatorProtocol.MSG_NAV_POINT_REACHED_INDEX);
	
		if (this.navigationPath!=null)
		{
			this.navigationPath.reached(index);
		}
		
		System.out.println("MSG_NAV_POINT_REACHED_INDEX :"+index);
		break;
	
		
	default:
		super.decodeCommand(dataPacket);
		break;	
	}

	
}	
	
	
	
	

@Override
public boolean moveTo(float xPos, float yPos, float speed)
{
	
	DataPacket dataPacket;
	
	dataPacket = new DataPacket (address,NavigatorProtocol.CMD_MOVE_TO,NavigatorProtocol.CMD_MOVE_TO_SIZE);
	
	dataPacket.setDataFloat(NavigatorProtocol.CMD_MOVE_TO_XPOS, xPos);
	dataPacket.setDataFloat(NavigatorProtocol.CMD_MOVE_TO_YPOS, yPos);
	dataPacket.setDataFloat(NavigatorProtocol.CMD_MOVE_TO_SPEED, speed);
	
	return(RemoteDataOutput.sendPacket(dataPacket));

}	
	

@Override
public boolean addNavPoint(float x, float z, float speed)
{
	DataPacket dataPacket;
	
	dataPacket = new DataPacket (address,NavigatorProtocol.CMD_ADD_NAV_POINT,NavigatorProtocol.CMD_ADD_NAV_POINT_SIZE);
	

	dataPacket.setDataFloat(NavigatorProtocol.CMD_ADD_NAV_POINT_X, x);
	dataPacket.setDataFloat(NavigatorProtocol.CMD_ADD_NAV_POINT_Z, z);
	dataPacket.setDataFloat(NavigatorProtocol.CMD_ADD_NAV_POINT_SPEED, speed);
	
	return(RemoteDataOutput.sendPacket(dataPacket));
}


@Override
public boolean clearNavPointList()
{
	DataPacket dataPacket;
	
	dataPacket = new DataPacket (address,NavigatorProtocol.CMD_CLEAR_NAV_POINT_LIST,NavigatorProtocol.CMD_CLEAR_NAV_POINT_LIST_SIZE);
	
	return(RemoteDataOutput.sendPacket(dataPacket));
}




@Override
public boolean gotoNavPoint(byte index)
{
	DataPacket dataPacket;
	
	dataPacket = new DataPacket (address,NavigatorProtocol.CMD_GOTO_NAV_POINT,NavigatorProtocol.CMD_GOTO_NAV_POINT_SIZE);
	

	dataPacket.setDataInt8(NavigatorProtocol.CMD_GOTO_NAV_POINT_INDEX, index);
	
	return(RemoteDataOutput.sendPacket(dataPacket));
}



@Override
public boolean selectNavPoint(byte index)
{
	DataPacket dataPacket;
	
	dataPacket = new DataPacket (address,NavigatorProtocol.CMD_SELECT_NAV_POINT,NavigatorProtocol.CMD_SELECT_NAV_POINT_SIZE);
	

	dataPacket.setDataInt8(NavigatorProtocol.CMD_SELECT_NAV_POINT_INDEX, index);
	
	return(RemoteDataOutput.sendPacket(dataPacket));
}


@Override
public boolean executePath()
{
	DataPacket dataPacket;
	
	dataPacket = new DataPacket (address,NavigatorProtocol.CMD_EXECUTE_PATH,NavigatorProtocol.CMD_EXECUTE_PATH_SIZE);
	
	return(RemoteDataOutput.sendPacket(dataPacket));
}





@Override
public void executePath(NavigationPath navigationPath, int index) 
{

	NavigationPoint navPoint;
	
	int enumerator;
	this.navigationPath = navigationPath;
	
	this.clearNavPointList();
	
	for (enumerator=0;enumerator<this.navigationPath.size() ;enumerator++)
	{
		navPoint=this.navigationPath.getNavpoint(enumerator);
		this.addNavPoint(navPoint.getXPosition(), navPoint.getZPosition(), navPoint.getSpeed());
	}
	
	this.selectNavPoint((byte)index);
	this.executePath();
	
}


*/
	
	

}
