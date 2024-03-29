
from Devices.AntDeviceId import AntDeviceConfig
from RoboControl.Robot.AbstractRobot.AbstractComponentList import AbstractComponentList
from RoboControl.Robot.AbstractRobot.Config.ComponentConfig import ComponentConfig



#************* Leg Sensors ******************

FRONT_LEFT_LEG_LED = { "name": "front left",
						"local_id" :0,
						"global_id" : 0
						 }

FRONT_RIGHT_LEG_LED = { "name": "front right",
						"local_id" :1,
						"global_id" : 0
						 }

CENTER_LEFT_LEG_LED = { "name": "center left",
						"local_id" :2,
						"global_id" : 0
						 }

CENTER_RIGHT_LEG_LED = { "name": "center right",
						"local_id" :3,
						"global_id" : 0
						 }

BACK_LEFT_LEG_LED = { "name": "back left",
						"local_id" :4,
						"global_id" : 0
						 }

BACK_RIGHT_LEG_LED = { "name": "back right",
						"local_id" :5,
						"global_id" : 0
						 }

# light sensors
FRONT_LEFT_LEG_VCNL4020 ={ "name": "center left",
						"local_id" :0,
						"global_id" : 0
						 }

FRONT_RIGHT_LEG_VCNL4020 ={ "name": "front right",
						"local_id" :1,
						"global_id" : 0
						 }

CENTER_LEFT_LEG_VCNL4020 ={ "name": "center left",
						"local_id" :2,
						"global_id" : 0
						 }


CENTER_RIGHT_LEG_VCNL4020 ={ "name": "center right",
						"local_id" :3,
						"global_id" : 0
						 }


BACK_LEFT_LEG_VCNL4020 ={ "name": "back left",
						"local_id" :4,
						"global_id" : 0
						 }


BACK_RIGHT_LEG_VCNL4020 ={ "name": "back right",
						"local_id" :5,
						"global_id" : 0
						 }


#************* Head ******************





HEAD_VCNL_4000_LEFT_SIDE = { "name": "left side",
						"local_id" :0,
						"global_id" : 0
						 }

HEAD_VCNL_4000_LEFT = { "name": "left",
						"local_id" :1,
						"global_id" : 0
						 }


HEAD_VCNL_4000_CENTER = { "name": "center",
						"local_id" :2,
						"global_id" : 0
						 }
HEAD_VCNL_4000_RIGHT = { "name": "right",
						"local_id" :3,
						"global_id" : 0
						 }
HEAD_VCNL_4000_RIGHT_SIDE ={ "name": "right side",
						"local_id" :4,
						"global_id" : 0
						 }




LEG_CONTROLLER_LEFT_SERVO ={ "name": "left",
						"local_id" : 0,
						"global_id" : 0
						 }
LEG_CONTROLLER_CENTER_SERVO ={ "name": "center",
						"local_id" : 1,
						"global_id" : 0
						 }
LEG_CONTROLLER_RIGHT_SERVO ={ "name": "right",
						"local_id" : 2,
						"global_id" : 0
						 }
LEG_CONTROLLER_HEAD_SERVO ={ "name": "head",
						"local_id" : 3,
						"global_id" : 0
						 }						 




LEFT_SERVO_CURRENT={ "name": "left",
						"local_id" : 0,
						"global_id" : 0
						 }
CENTER_SERVO_CURRENT={ "name": "center",
						"local_id" : 1,
						"global_id" : 0
						 }
RIGHT_SERVO_CURRENT={ "name": "right",
						"local_id" : 2,
						"global_id" : 0
						 }						 						 





class AntComponents:
	def __init__(self):

		pass	
"""
	



package de.hska.lat.ant_IIIb.metaData;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.device.generic.dataHub.DataHub;
import de.hska.lat.ant_IIIb.devices.headSensors.HeadSensorsConfiguration;
import de.hska.lat.ant_IIIb.devices.irCom.Rc5TransciverConfiguration;
import de.hska.lat.ant_IIIb.devices.legController.LegControllerConfiguration;
import de.hska.lat.ant_IIIb.devices.legSensors.components.LegSensorsConfiguration;
import de.hska.lat.ant_IIIb.devices.pixyController.PixyControllerConfiguration;
import de.hska.lat.ant_IIIb.devices.tailBoard.TailBoardConfiguration;

public enum AntComponents {
	
	ROBOT_NAME(DataHub.ROBOT_NAME,0,DataHub.ID),
	ROBOT_NAME2("",1,DataHub.ID),
	
	LEFT_SERVO (LegControllerConfiguration.LEFT_SERVO),
	CENTER_SERVO (LegControllerConfiguration.CENTER_SERVO),
	RIGHT_SERVO (LegControllerConfiguration.RIGHT_SERVO),
	HEAD_SERVO (LegControllerConfiguration.HEAD_SERVO),

	LEFT_TEMPERATURE_SENSOR (LegControllerConfiguration.LEFT_TEMPERATURE_SENSOR),
	CENTER_TEMPERATURE_SENSOR (LegControllerConfiguration.CENTER_TEMPERATURE_SENSOR),
	RIGHT_TEMPERATURE_SENSOR (LegControllerConfiguration.RIGHT_TEMPERATURE_SENSOR),
	REGULATOR_TEMPERATURE_SENSOR (LegControllerConfiguration.REGULATOR_TEMPERATURE_SENSOR),
	
	

	

	ANT_MPU9150 (TailBoardConfiguration.ANT_MPU9150),
	ANT_IMU (TailBoardConfiguration.ANT_IMU),
	ANT_LOCATOR (TailBoardConfiguration.ANT_LOCATOR ),
	ANT_HEADING (TailBoardConfiguration.ANT_HEADING),
	
	TAIL_VCNL4020_LEFT(TailBoardConfiguration.TAIL_VCNL4020_LEFT),
	TAIL_VCNL4020_CENTER(TailBoardConfiguration.TAIL_VCNL4020_CENTER),
	TAIL_VCNL4020_RIGHT(TailBoardConfiguration.TAIL_VCNL4020_RIGHT),
	
	TAIL_TMP006_LEFT(TailBoardConfiguration.TAIL_TMP006_LEFT),
	TAIL_TMP006_CENTER(TailBoardConfiguration.TAIL_TMP006_CENTER),
	TAIL_TMP006_RIGHT(TailBoardConfiguration.TAIL_TMP006_RIGHT),
		

	TAIL_LEFT_LED (TailBoardConfiguration.TAIL_LEFT_LED),
	TAIL_RIGHT_LED (TailBoardConfiguration.TAIL_RIGHT_LED),
	
	 MOTION_CONTROLLER("motion controller",0,AntDeviceId.LEG_CONTROLLER.getId()),
		
		
			
	HEAD_LEFT_MLX90614 (HeadSensorsConfiguration.HEAD_LEFT_MLX90614),
	HEAD_RIGHT_MLX90614 (HeadSensorsConfiguration.HEAD_RIGHT_MLX90614),
	
			
	HEAD_VCNL4000_LEFT_SIDE (HeadSensorsConfiguration.HEAD_VCNL4000_LEFT_SIDE),
	HEAD_VCNL4000_LEFT(	HeadSensorsConfiguration.HEAD_VCNL4000_LEFT),
	HEAD_VCNL4000_CENTER(HeadSensorsConfiguration.HEAD_VCNL4000_CENTER),
	HEAD_VCNL4000_RIGHT(HeadSensorsConfiguration.HEAD_VCNL4000_RIGHT),
	HEAD_VCNL4000_RIGHT_SIDE(HeadSensorsConfiguration.HEAD_VCNL4000_RIGHT_SIDE),
			
		
	

	TEMPERATURE_0(
			HeadSensorsConfiguration.TEMPERATURE_0.getName(),
			HeadSensorsConfiguration.TEMPERATURE_0.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),

	TEMPERATURE_1(
			HeadSensorsConfiguration.TEMPERATURE_1.getName(),
			HeadSensorsConfiguration.TEMPERATURE_1.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),

	TEMPERATURE_2(
			HeadSensorsConfiguration.TEMPERATURE_2.getName(),
			HeadSensorsConfiguration.TEMPERATURE_2.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),

	TEMPERATURE_3(
			HeadSensorsConfiguration.TEMPERATURE_3.getName(),
			HeadSensorsConfiguration.TEMPERATURE_3.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),

			
	PRESURE_0(
			HeadSensorsConfiguration.PRESURE_0.getName(),
			HeadSensorsConfiguration.PRESURE_0.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),

	PRESURE_1(
			HeadSensorsConfiguration.PRESURE_1.getName(),
			HeadSensorsConfiguration.PRESURE_1.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),			

	PRESURE_2(
			HeadSensorsConfiguration.PRESURE_2.getName(),
			HeadSensorsConfiguration.PRESURE_2.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()),

	PRESURE_3(
			HeadSensorsConfiguration.PRESURE_3.getName(),
			HeadSensorsConfiguration.PRESURE_3.getLocalId(),
			AntDeviceId.HEAD_SENSORS.getId()), 					
				





	
	RC5_TRANSCIVER (Rc5TransciverConfiguration.RC5_TRANSCIVER),
	
	PIXY_CAM_1 (PixyControllerConfiguration.PIXY_CAM_1),

			;

	
	
	/**
	 * @uml.property  name="localId"
	 */
	private final int localId;
	/**
	 * @uml.property  name="deviceId"
	 */
	private final int deviceId;
	/**
	 * @uml.property  name="name"
	 */
	private final String name;
	
	
	
	
private AntComponents(String name,int localId, int deviceId)
{
	this.localId=localId;
	this.deviceId = deviceId;
	this.name=name;

	
}
	

private AntComponents(HeadSensorsConfiguration configuration)
{
	this.localId = configuration.getLocalId();
	this.deviceId = AntDeviceId.HEAD_SENSORS.getId();
	this.name = configuration.getName();

	
}

private AntComponents(TailBoardConfiguration configuration)
{
	this.localId = configuration.getLocalId();
	this.deviceId = AntDeviceId.TAIL_BOARD.getId();
	this.name = configuration.getName();

}	


private AntComponents(LegSensorsConfiguration configuration)
{
	this.localId = configuration.getLocalId();
	this.deviceId = AntDeviceId.LEG_SENSORS.getId();
	this.name = configuration.getName();

}	


private AntComponents(LegControllerConfiguration configuration)
{
	this.localId = configuration.getLocalId();
	this.deviceId = AntDeviceId.LEG_CONTROLLER.getId();
	this.name = configuration.getName();

}

private AntComponents(PixyControllerConfiguration configuration)
{
	this.localId = configuration.getLocalId();
	this.deviceId = AntDeviceId.PIXY_CONTROLLER.getId();
	this.name = configuration.getName();
}

private AntComponents(Rc5TransciverConfiguration configuration)
{
	this.localId = configuration.getLocalId();
	this.deviceId = AntDeviceId.IR_COM.getId();
	this.name = configuration.getName();
}


/**
 * @return
 * @uml.property  name="localId"
 */
public int getLocalId()
{
	return(this.localId);
}

public int getGlobalId()
{
	//return(this.globalId);
	return(this.ordinal());
}
		
/**
 * @return
 * @uml.property  name="name"
 */
public String getName()
{
	return(this.name);
}


public ComponentMetaData getMetaData()
{
	return(new ComponentMetaData(this.name,this.name(),this.ordinal(),this.localId, this.deviceId));
}
	
}
"""