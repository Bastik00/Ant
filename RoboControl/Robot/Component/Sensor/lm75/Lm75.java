package de.hska.lat.robot.component.sensor.lm75;





import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensor;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.component.sensor.lm75.protocol.Cmd_setLm75Settings;





public class Lm75 extends TemperatureSensor<ComponentSettingsChangeNotifier,TemperatureSensorProtocol>  
{

	protected Lm75Address i2cAddress = Lm75Address.getDefault();
	protected boolean extendedMode = false;
	
public Lm75(ComponentMetaData metaData, TemperatureSensorProtocol protocol)
{
	super( metaData, protocol);

	this.temperature = new TemperatureValue(this.getComponentName(),233.15f,398.15f); 
	
}



public boolean remote_setSettings(Lm75Address address, boolean on, boolean extended)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setLm75Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			address,on, extended)));
}





@Override
public void onConnected()
{
	this.remote_getSettings();
}



/**
 * get this sensor i2c address
 * @return i2c address of this sensor
 */
public Lm75Address getI2CAddress()
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



public void setSettings( Lm75Address i2cAddres,boolean extendedMode)
{
	this.i2cAddress = i2cAddres;
	this.extendedMode = extendedMode;
	this.notifySetupChanged();
	
}




}
