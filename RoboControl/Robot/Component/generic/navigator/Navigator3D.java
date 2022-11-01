package de.hska.lat.robot.component.generic.navigator;


import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.motion.MotionController3D;


public class Navigator3D extends Navigator<MotionController3D>  
{

	
	
	
	

	protected MotionController3D motionController;



public Navigator3D(ComponentMetaData metaData, ComponentProtocol protocol, Robot robot, MotionController3D motionController)
{
	super(metaData, protocol, robot, motionController);
}







public boolean moveTo(float x, float y, float z, float speed)
{

	return false;
}







@Override
protected boolean moveTo(NavigationPoint navigationPoint) {
	// TODO Auto-generated method stub
	return false;
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
