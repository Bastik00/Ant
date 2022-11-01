package de.hska.lat.robot.component.actor.generic.rc5Transciver;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.actor.Actor;
import de.hska.lat.robot.component.actor.generic.rc5Transciver.protocol.Cmd_rc5SendCommand;
import de.hska.lat.robot.component.actor.generic.rc5Transciver.protocol.Msg_rc5Test;
import de.hska.lat.robot.value.ComponentValue;


public class Rc5Transciver extends  Actor<ComponentChangeNotifier, ComponentSettingsChangeNotifier,
											Rc5TransciverProtocol, ComponentValue<?>> {

	public Rc5Transciver(ComponentMetaData metaData, Rc5TransciverProtocol protocol) {
		super(metaData, protocol);
	}

	@Override
	public boolean remote_getValue() {
		return false;
	}

	public boolean remote_sendCommand(final int command) {
		if (this.componentProtocol==null) {
			return false;
		}

		return(sendData(Cmd_rc5SendCommand.getCommand(this.componentProtocol.cmdSetValueId, command)));
	}
	
	
	
	
	protected void processtestMessage(Msg_rc5Test testMessage)
	{
		int adressMask = 0x00FF;
        int cmdMask = 0x1F00;
        int cmdData = 0;
        int adressData = 0;
		
        int data = testMessage.getDataPacket().getInt16(0);
        cmdData = ((cmdMask & data) >> 8);
        adressData = ((adressMask & data) >> 2);
        
        System.out.println(" ======== Message ==========");
        System.out.println("Comand: " + Integer.toHexString(cmdData));
        System.out.println("Adress: " + Integer.toHexString(adressData));
        System.out.println(" ===========================");
        
        

        //this.n
        //this.sensorListener
	}
	
	
	@Override
	public boolean decodeMessage(final RemoteMessage remoteData) {

        
        
        if (remoteData instanceof Msg_rc5Test)
        {
        	
        }
        
        

		return true;
	}
	
	
}
