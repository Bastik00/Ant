package de.hska.lat.robot.component.currentSensor;

import java.util.ArrayList;

import de.hska.lat.robot.component.currentSensor.protocol.Cmd_getActualCurrentDrain;
import de.hska.lat.robot.component.currentSensor.protocol.Cmd_getMaximalCurrentDrain;
import de.hska.lat.robot.component.currentSensor.protocol.Cmd_getTotalCurrentDrain;
import de.hska.lat.robot.component.currentSensor.protocol.Cmd_resetMaximalCurrentDrain;
import de.hska.lat.robot.component.currentSensor.protocol.Cmd_resetTotalCurrentDrain;
import de.hska.lat.robot.component.currentSensor.protocol.Cmd_setCurrentSettings;
import de.hska.lat.robot.component.currentSensor.protocol.Msg_currentSettings;
import de.hska.lat.robot.component.currentSensor.protocol.Stream_actualConsumption;
import de.hska.lat.robot.component.currentSensor.protocol.Stream_maxConsumption;
import de.hska.lat.robot.component.currentSensor.protocol.Stream_totalConsumption;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class CurrentSensorProtocol extends SensorProtocol
{



	public final int cmdGetTotalCurrentDrainId;
	public final int cmdResetTotalCurrentDrainId;
	public final int msgTotalCurrentConsumptionId;
	public final int streamTotalConsumptionId;
	
	public final int cmdGetMaximalCurrentDrainId;
	public final int cmdResetMaximalCurrentDrainId;
	public final int msgMaxConsumptionId;
	public final int streamMaxConsumptionId;

	public CurrentSensorProtocol(
			int deviceId, 
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId, 
			int cmdLoadDefaultsId,
			int msgSettingsId, 
			int cmdActualCurrentDrainId,
			int msgActualCurrentDrainId, 
			int streamActualConsumptionId,
			
			
			int cmdGetTotalCurrentDrainId,
			int cmdResetTotalCurrentDrainId,
			int msgTotalCurrentConsumptionId,
			int streamTotalConsumptionId,
			
			int cmdGetMaximalCurrentDrainId,
			int cmdResetMaximalCurrentDrainId,
			int msgMaxConsumptionId,
			int streamMaxConsumptionId
			 
		

		)
	{
		super(
				deviceId, 
				cmdSetSettingsId, 
				cmdGetSettingsId, 
				cmdSaveDefaultsId,
				cmdLoadDefaultsId, 
				msgSettingsId, 
				cmdActualCurrentDrainId,
				msgActualCurrentDrainId, 
				streamActualConsumptionId
				);

	

		this.cmdGetTotalCurrentDrainId = cmdGetTotalCurrentDrainId;
		this.cmdResetTotalCurrentDrainId = cmdResetTotalCurrentDrainId;
		this.msgTotalCurrentConsumptionId = msgTotalCurrentConsumptionId;
		this.streamTotalConsumptionId = streamTotalConsumptionId;
		
		this.cmdGetMaximalCurrentDrainId = cmdGetMaximalCurrentDrainId;
		this.cmdResetMaximalCurrentDrainId = cmdResetMaximalCurrentDrainId;
		this.msgMaxConsumptionId = msgMaxConsumptionId;
		this.streamMaxConsumptionId = streamMaxConsumptionId;
	}

	/**
	 * get Temperature sensor command processors
	 * 
	 * @param sensorSet
	 *            temperature sensor set
	 * @return commands processors for temperature sensor
	 */

	public ArrayList<RemoteCommandProcessor> getCommandProcessors(
			RemoteDecoder decoder)
	{

		ArrayList<RemoteCommandProcessor> commands = super
				.getCommandProcessors(decoder);

		commands.addAll(super.getCommandProcessors(decoder));

		commands.add(new RemoteCommandProcessor(new Cmd_getActualCurrentDrain(
				this.cmdGetValueId), decoder));

		commands.add(new RemoteCommandProcessor(new Cmd_getMaximalCurrentDrain(
				this.cmdGetMaximalCurrentDrainId), decoder));

		commands.add(new RemoteCommandProcessor(new Cmd_getTotalCurrentDrain(
				this.cmdGetTotalCurrentDrainId), decoder));

		commands.add(new RemoteCommandProcessor(
				new Cmd_resetMaximalCurrentDrain(
						this.cmdResetMaximalCurrentDrainId), decoder));

		commands.add(new RemoteCommandProcessor(
				new Cmd_resetTotalCurrentDrain(
						this.cmdResetTotalCurrentDrainId), decoder));
		
		commands.add(new RemoteCommandProcessor(new Cmd_setCurrentSettings(
				this.cmdSetSettingsId), decoder));

		return (commands);
	}

	/**
	 * get temperature sensor stream processors
	 * 
	 * @param sensor
	 *            temperature sensor set
	 * @return stream processors for temperature sensor
	 */

	public ArrayList<RemoteStreamProcessor> getStreamProcessors(
			RemoteDecoder decoder)
	{

		ArrayList<RemoteStreamProcessor> streams = new ArrayList<RemoteStreamProcessor>();
		streams.addAll(super.getStreamProcessors(decoder));

		streams.add(new RemoteStreamProcessor(new Stream_actualConsumption(
				this.streamValuesId), decoder));
		streams.add(new RemoteStreamProcessor(new Stream_totalConsumption(
				this.streamTotalConsumptionId), decoder));
		streams.add(new RemoteStreamProcessor(new Stream_maxConsumption(
				this.streamMaxConsumptionId), decoder));
		
		

		return (streams);

	}

	/**
	 * get temperature sensor message processors
	 * 
	 * @param sensor
	 *            temperature sensor set
	 * @return message processors for temperature sensor
	 */

	public ArrayList<RemoteMessageProcessor> getMessageProcessors(
			RemoteDecoder decoder)
	{

		ArrayList<RemoteMessageProcessor> messages = new ArrayList<RemoteMessageProcessor>();

		messages.addAll(super.getMessageProcessors(decoder));

		messages.add(new RemoteMessageProcessor(new Msg_currentSettings(
				this.msgSettingsId), decoder));


		return (messages);
	}

}
