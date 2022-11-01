package de.hska.lat.robot.component.actor.generic.rc5Transciver.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;


public class Msg_rc5Test extends RemoteMessage {
	
	private static final long serialVersionUID = 2638694167468005642L;

	protected static final String name = "Data";
	protected static final String description = "Is send if the irTransciver added a Send Cmd to the Buffer";

	private static final int INDEX_DATA = 0;
	

	public Msg_rc5Test() {
		this.add(new RemoteParameterUint16("data-frame","rc5"));
	}
	
	
	public Msg_rc5Test(int command)	{
		this();
		this.setId(command);
	}


	@Override
	public String getName() {
		return(Msg_rc5Test.name);
	}
	
	
	@Override
	public String getDescription() {
		return(Msg_rc5Test.description);
	}
	
	
	
	public void setData(int data) {
		(( RemoteParameterUint16) this.get(Msg_rc5Test.INDEX_DATA)).setValue(data);
	
	}
	
	
	/**
	 * get sensor index for sensor corresponding to this message
	 * @return  index of sensor in sensor set
	 */
	public int getData() {
		return((( RemoteParameterUint16) this.get(Msg_rc5Test.INDEX_DATA)).getValue());
	}
	
	public static Msg_rc5Test getCommand(int id) {
		Msg_rc5Test cmd;
		cmd = new Msg_rc5Test(id);
		
		return(cmd);
	}
	
	public static Msg_rc5Test getCommand(int command, int data, int speed){
		Msg_rc5Test cmd;
		cmd = Msg_rc5Test.getCommand(command);
		cmd.setData(data);
		
		return(cmd);
	}
}
