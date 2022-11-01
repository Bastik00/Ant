package de.hska.lat.robot.component.sensor.tmp006;



import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteDataTransmitter;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.tmp006.protocol.Cmd_setTmp006Settings;

import de.hska.lat.robot.value.ComponentValue;




public class Tmp006 extends RobotComponent<Tmp006ChangeNotifier,ComponentSettingsChangeNotifier,Tmp006Protocol> 
{

	protected Tmp006ConversionRate conversionRate = Tmp006ConversionRate.getDefault();
	protected Tmp006Address i2cAddress = Tmp006Address.getDefault();
	
	protected Tmp006AmbientSensor ambientSensor;
	protected Tmp006ObjectSensor objectSensor;
	
	
	
public Tmp006(ComponentMetaData metaData, Tmp006Protocol protocol)
{
	super( metaData, protocol);

	this.ambientSensor = new Tmp006AmbientSensor(metaData, protocol.ambientTemperatureProtocol);
	this.objectSensor = new Tmp006ObjectSensor(metaData, protocol.objectTemperatureProtocol);
}



public boolean remote_setSettings(Tmp006Address address, boolean on, Tmp006ConversionRate conversionRate)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setTmp006Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			address,on, conversionRate)));
}


@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = this.ambientSensor.getDataValues();
	values.addAll(this.objectSensor.getDataValues());
	
	return (values);
}


@Override
public void onConnected()
{
	this.remote_getSettings();
}

/**
 * get this sensors conversion rate
 * @return this sensors conversion rate
 */

public Tmp006ConversionRate getConversionRate()
{
	return (this.conversionRate);
}

/**
 * get this sensor i2c address
 * @return i2c address of this sensor
 */
public Tmp006Address getI2CAddress()
{
	return (this.i2cAddress);
}




/**
 * set transmitter for this component. All data will be sent thru this transmitter
 * @param transmitter transmitter
 */
@Override
public void setTransmitter(RemoteDataTransmitter transmitter)
{
	super.setTransmitter(transmitter);
	
	this.ambientSensor.setTransmitter(transmitter);
	this.objectSensor.setTransmitter(transmitter);
}



public void setSettings(Tmp006ConversionRate conversionRate, Tmp006Address i2cAddres)
{
	this.conversionRate = conversionRate;
	this.i2cAddress = i2cAddres;
	this.notifySetupChanged();
	
}



public Tmp006AmbientSensor getAmbientSensor()
{
	return (this.ambientSensor);
}


public Tmp006ObjectSensor getObjectSensor()
{
	return (this.objectSensor);
}




}
