package de.hska.lat.robot.component.generic.motion;

import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_stop;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;

public class MotionProtocol extends ComponentProtocol
{

	protected final int cmdMoveToId;
	protected final int cmdMoveId;
	
	protected final int cmdRotateId;
	protected final int cmdRotateToId;
	
	protected final int cmdDriveId;
	protected final int cmdStop;
	
	public MotionProtocol(int deviceId,
			int cmdSetSettingsId,
			int cmdGetSettingsId,
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettingsId,
			int cmdStop,
			int cmdRotateId,
			int cmdRotateToId,
			int cmdMoveId,
			int cmdMoveToId,
			int cmdDriveId)
	{
		super(deviceId,
				cmdSetSettingsId,
				cmdGetSettingsId, 
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettingsId);

		this.cmdStop = cmdStop;
		this.cmdRotateId = cmdRotateId;
		this.cmdRotateToId = cmdRotateToId;

		this.cmdMoveId = cmdMoveId;
		this.cmdMoveToId = cmdMoveToId;
		this.cmdDriveId = cmdDriveId;
	}

	

public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);
	
	commands.addAll(super.getCommandProcessors(decoder));
	
	 commands.add(new RemoteCommandProcessor(
			 new Cmd_stop(this.cmdStop), 
			 decoder));
	 

	
	return(commands);
}
	
	
	
}
