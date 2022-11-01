package de.hska.lat.robot.component.sensor.tmp102;





import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.component.sensor.tmp102.protocol.Cmd_setTmp102Settings;





public class Tmp102 extends TemperatureSensor<ComponentSettingsChangeNotifier,TemperatureSensorProtocol>  
{

	protected Tmp102ConversionRate conversionRate = Tmp102ConversionRate.getDefault();
	protected Tmp102Address i2cAddress = Tmp102Address.getDefault();
	protected boolean extendedMode = false;
	
public Tmp102(DetectorMetaData metaData, TemperatureSensorProtocol protocol)
{
	super( metaData, protocol);

	this.temperature = new TemperatureValue(this.getComponentName(),233.15f,398.15f); 
	
}



public boolean remote_setSettings(Tmp102Address address, boolean on, boolean extended, Tmp102ConversionRate conversionRate)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setTmp102Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			address,on, extended, conversionRate)));
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

public Tmp102ConversionRate getConversionRate()
{
	return (this.conversionRate);
}

/**
 * get this sensor i2c address
 * @return i2c address of this sensor
 */
public Tmp102Address getI2CAddress()
{
	return (this.i2cAddress);
}






/**
 * return status of the sensors extended mode state. In extended Mode sensor can measure up to 150°C 
 * in standard mode (TMP75 emulation) up to 128°C
 * @return
 */
public boolean isInExtendedMode()
{
	return(this.extendedMode);
}



public void setSettings(Tmp102ConversionRate conversionRate, Tmp102Address i2cAddres,boolean extendedMode)
{
	this.conversionRate = conversionRate;
	this.i2cAddress = i2cAddres;
	this.extendedMode = extendedMode;
	this.notifySetupChanged();
	
}




}
