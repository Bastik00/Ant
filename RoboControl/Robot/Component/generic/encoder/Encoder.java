package de.hska.lat.robot.component.generic.encoder;

import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.generic.encoder.protocol.Cmd_getEncoderTicks;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.value.ComponentValue;




public class Encoder extends Sensor<EncoderChangeNotifier, ComponentSettingsChangeNotifier,EncoderProtocol >
{
	
	
	
	protected EncoderTicksValue ticks;
	
	
public Encoder(ComponentMetaData metaData,EncoderProtocol protocol)
{
	super(metaData, protocol);
	this.ticks = new EncoderTicksValue(this.getComponentName());
	
}


/**
 * get this motor velocity value
 * @return motors velocity value
 */


public EncoderTicksValue getEncoderTicksValue()
{

	return (this.ticks);
}


/**
 * set this motors new velocity. This value comes from remote system and corresponds to the actually measured value.    
 * @param velocity new velocity of this motor
 */

public void setVelocity(float velocity)
{
	this.ticks.setValue(velocity);
}


@Override
public boolean remote_getValue()
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_getEncoderTicks.getCommand(this.componentProtocol.cmdGetValueId,this.localId)));
}

@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	
	values.add(this.getEncoderTicksValue());

	
	return (values);
}

}
