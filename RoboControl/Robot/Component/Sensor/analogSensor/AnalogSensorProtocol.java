package de.hska.lat.robot.component.analogSensor;

import de.hska.lat.robot.component.analogDetector.AnalogDetectorProtocol;

public class AnalogSensorProtocol extends AnalogDetectorProtocol
{

	public final int getAnalogSensorValueCommandId;	
	public final int getAnalogSensorStatisticCommandId;
	public final int streamAnalogValuesId;

	
	public AnalogSensorProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId, 
			int cmdLoadDefaultsId, 
			int msgSettingsId,
			
			int getAnalogSensorValueCommandId,
			int getAnalogSensorStatisticCommandId,
			
			int streamAnalogValuesId)
	
	{
		super(deviceId, 
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId);
		
		
		this.getAnalogSensorValueCommandId= getAnalogSensorValueCommandId;
		this.getAnalogSensorStatisticCommandId = getAnalogSensorStatisticCommandId;
		this.streamAnalogValuesId = streamAnalogValuesId;
	}

}
