package de.hska.lat.robot.device.generic.dataHub;


import de.hska.lat.robot.component.text.TextProtocol;
import de.hska.lat.robot.component.text.protocol.Msg_textFragment;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteMessageProcessor;
import de.hska.lat.robot.device.protocol.DeviceProtocol;



public class DataHubProtocol extends DeviceProtocol{

	public static final byte MSG_NODE_TYPE 			= 0x2;
	public static final byte CMD_GET_NODE_TYPE 		= 0x5;
	
	
	
	private static final int MSG_TEXT_FRAGMENT		= 0x20;
	
	private static final int CMD_GET_TEXT			= 0x20;
	
	
	
public DataHubProtocol(DataHub device)
{
	super(device);
	
	
	this.messageList.add(new RemoteMessageProcessor(new Msg_textFragment(DataHubProtocol.MSG_TEXT_FRAGMENT), device.getTexts()));
	
}









public static TextProtocol getTextProtocol(int deviceId)
{
	TextProtocol protocol = new TextProtocol(
			0 ,
			0 , // cmdSetSettingsId,
			0 , // cmdGetSettingsId
			0 , // cmdSaveDefaultsId
			0 , // cmdLoadDefaultsId
			0 , // msgSettingsId
			DataHubProtocol.CMD_GET_TEXT, // cmdGetText
			0 	// msgTextFragment
			);
	
	
	return (protocol);
}


/*
public static Msg_nodeType msg_nodeType()
{
	Msg_nodeType msgNodeType;
	
	msgNodeType= new Msg_nodeType(MSG_NODE_TYPE);
	msgNodeType.setNodeType(Connection.REMOTE_CHANEL_ID);
	
	return(msgNodeType);
}
*/

}
