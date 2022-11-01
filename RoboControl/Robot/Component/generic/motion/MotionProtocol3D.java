package de.hska.lat.robot.component.generic.motion;

import java.util.ArrayList;

import de.hska.lat.robot.component.generic.motion.protocol.Cmd_drive;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_move2D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_moveTo2D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_rotate2D;
import de.hska.lat.robot.component.generic.motion.protocol.Cmd_rotateTo2D;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteCommandProcessor;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteDecoder;

public class MotionProtocol3D extends MotionProtocol
{

	

	
	
	
	
	public MotionProtocol3D(int deviceId, 
			int cmdSetSettingsId,
			int cmdGetSettingsId, 
			int cmdSaveDefaultsId,
			int cmdLoadDefaultsId,
			int msgSettings,
			int cmdStop,
			int cmdRotateId,
			int cmdRotateToId,
			int cmdMoveId,
			int cmdMoveToId,
			int cmdDriveId
		)
	{
		super(deviceId,
				cmdSetSettingsId,
				cmdGetSettingsId,
				cmdSaveDefaultsId,
				cmdLoadDefaultsId,
				msgSettings,
				cmdStop,
				cmdRotateId,
				cmdRotateToId,
				cmdMoveId,
				cmdMoveToId,
				cmdDriveId);


	}

	
public ArrayList<RemoteCommandProcessor> getCommandProcessors(RemoteDecoder decoder)	
{
	
	ArrayList<RemoteCommandProcessor> commands = super.getCommandProcessors(decoder);
	
	commands.addAll(super.getCommandProcessors(decoder));
	
	 commands.add(new RemoteCommandProcessor(
			 new Cmd_rotate2D(this.cmdRotateId), 
			 decoder));
	 
	 commands.add(new RemoteCommandProcessor(
			 new Cmd_rotateTo2D(this.cmdRotateToId), 
			 decoder));
	 
	 commands.add(new RemoteCommandProcessor(
			 new Cmd_move2D(this.cmdMoveId), 
			 decoder));
	 
	 commands.add(new RemoteCommandProcessor(
			 new Cmd_moveTo2D(this.cmdMoveToId), 
			 decoder));
	 
	 commands.add(new RemoteCommandProcessor(
			 new Cmd_drive(this.cmdDriveId), 
			 decoder));
	
	return(commands);
}
		
	
	
	
}
