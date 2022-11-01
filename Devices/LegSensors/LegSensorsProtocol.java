

class LegSensorsProtocol(DeviceProtocol): 
	def __init__(self)

	def get_led_protocol():
		pass

"""
public static LedProtocol getLedProtocol(int deviceId)
{
	
	LedProtocol ledProtocol = new LedProtocol(
			deviceId,
			0, //cmdSetSettingsId
			0, //cmdGetSettingsId
			0, // cmdSaveDefaultsId
			0, // cmdLoadDefaultsId
			0, // msgSettings
			LegSensorsProtocol.CMD_LED_SET_BRIGHTNESS, // cmdSetBrightnessId
			LegSensorsProtocol.CMD_LED_GET_BRIGHTNESS, // cmdGetBrightnessId
			LegSensorsProtocol.MSG_LED_BRIGHTNES, // msgBrightnessId
			LegSensorsProtocol.STREAM_LED_BRIGHTNESS // streamBrightnessId
			);

	return (ledProtocol);
}
"""

"""package de.hska.lat.ant_IIIb.devices.legSensors;


import de.hska.lat.robot.component.actor.led.LedProtocol;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000Protocol;


import de.hska.lat.robot.device.protocol.DeviceProtocol;



public class LegSensorsProtocol extends DeviceProtocol
{
	

	

	
	
	
	

	
	public static final byte CMD_VCNL4000_SET_SETTINGS				= 0x20;
	public static final byte CMD_VCNL4000_GET_SETTINGS				= 0x21;
	public static final byte CMD_VCNL4000_SAVE_DEFAULTS				= 0x22;
	public static final byte CMD_VCNL4000_LOAD_DEFAULTS				= 0x23;
	public static final byte CMD_VCNL4000_GET_LUX					= 0x24;
	public static final byte CMD_VCNL4000_GET_DISTANCE				= 0x25;

	
	public static final byte CMD_VCNL4000_GET_RAW_PROXIMITY 		= 0x26;
	
	public static final byte CMD_VCNL4000_SET_DISTANCE_TABLE		= 0x27;
	public static final byte CMD_VCNL4000_GET_DISTANCE_TABLE		= 0x28;
	
	
	public static final byte CMD_LED_SET_BRIGHTNESS					= 0x30;
	public static final byte CMD_LED_GET_BRIGHTNESS					= 0x31;




	
	
	public static final byte MSG_VCNL4000_SETTINGS					= 0x20;
	public static final byte MSG_VCNL4000_DISTANCE					= 0x21;
	public static final byte MSG_VCNL4000_DISTANCE_TABLE			= 0x22;
	public static final byte MSG_VCNL4000_LUX						= 0x23;
	public static final byte MSG_VCNL4000_RAW_PROXIMITY				= 0x24; 
	
	public static final byte MSG_LED_BRIGHTNES						= 0x25;
	
	public static final byte STREAM_VCNL4000LUX_VALUES				= 0x20;
	public static final byte STREAM_VCNL4000DISTANCE_VALUES			= 0x21;

	public static final byte STREAM_LED_BRIGHTNESS 					= 0x22;
	
	
	

	public LegSensorsProtocol(LegSensors device) 
	{
		
		super(device);
		
	

		 this.streamList.addAll(LegSensorsProtocol.getVcnl4000Protocol(device.getId()).getStreamProcessors(device.getVcnl4000Set()));
		 this.messageList.addAll(LegSensorsProtocol.getVcnl4000Protocol(device.getId()).getMessageProcessors(device.getVcnl4000Set()));
		 
					 
				 
		


		 
	}



	public static Vcnl4000Protocol getVcnl4000Protocol(int deviceId)
	{
		Vcnl4000Protocol protocol;

		protocol = new Vcnl4000Protocol(deviceId,
				LegSensorsProtocol.CMD_VCNL4000_SET_SETTINGS,
				LegSensorsProtocol.CMD_VCNL4000_GET_SETTINGS,
				LegSensorsProtocol.CMD_VCNL4000_SAVE_DEFAULTS,
				LegSensorsProtocol.CMD_VCNL4000_LOAD_DEFAULTS,
				LegSensorsProtocol.MSG_VCNL4000_SETTINGS,
				
				LegSensorsProtocol.CMD_VCNL4000_GET_LUX,
				LegSensorsProtocol.MSG_VCNL4000_LUX,
				LegSensorsProtocol.STREAM_VCNL4000LUX_VALUES,
				
				LegSensorsProtocol.CMD_VCNL4000_GET_DISTANCE,
				LegSensorsProtocol.MSG_VCNL4000_DISTANCE,
				LegSensorsProtocol.STREAM_VCNL4000DISTANCE_VALUES,
				
				LegSensorsProtocol.CMD_VCNL4000_GET_RAW_PROXIMITY, 
				LegSensorsProtocol.MSG_VCNL4000_RAW_PROXIMITY, 
				LegSensorsProtocol.CMD_VCNL4000_SET_DISTANCE_TABLE,
				LegSensorsProtocol.CMD_VCNL4000_GET_DISTANCE_TABLE,
				LegSensorsProtocol.MSG_VCNL4000_DISTANCE_TABLE
				);
		
		return (protocol);
	}



}


"""