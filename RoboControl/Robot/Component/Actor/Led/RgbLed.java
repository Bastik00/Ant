package de.hska.lat.robot.component.actor.led;


import de.hska.lat.color.HsvColor;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.actor.led.protocol.Cmd_setLedColor;

public class RgbLed extends Led
{

	
	protected HsvColor color;
	
public RgbLed(ComponentMetaData metaData, RgbLedProtocol protocol)
{
	super(metaData, protocol);

}


	


public boolean remote_setColor(HsvColor color)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setLedColor.getCommand(((RgbLedProtocol)this.componentProtocol).cmdSetColorId,this.localId,color)));
}






}
