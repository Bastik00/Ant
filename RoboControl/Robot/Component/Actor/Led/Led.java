package de.hska.lat.robot.component.actor.led;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.actor.Actor;
import de.hska.lat.robot.component.actor.led.protocol.Cmd_setLedBrightness;
import de.hska.lat.robot.component.actor.led.protocol.Cmd_getLedBrightness;
import de.hska.lat.robot.value.brightness.BrightnessValue;


public class Led extends Actor<ComponentChangeNotifier,ComponentSettingsChangeNotifier , LedProtocol, BrightnessValue>
{

	protected BrightnessValue brightness;
	
public Led(ComponentMetaData metaData, LedProtocol protocol)
{
	super(metaData, protocol);

	this.brightness = new BrightnessValue(this.getComponentName());
}

public boolean remote_setBrightness(float brightness)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setLedBrightness.getCommand(this.componentProtocol.cmdSetValueId,this.localId,brightness)));
}


@Override
public boolean remote_getValue()
{
	if (this.componentProtocol==null)
		return(false);

	return(sendData(Cmd_getLedBrightness.getCommand(this.componentProtocol.cmdGetValueId,this.localId)));
}








}
