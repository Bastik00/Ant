package de.hska.lat.robot.component.temperatureSensor.mlx90614;





import java.util.ArrayList;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.protocol.Cmd_setMlx90614Settings;
import de.hska.lat.robot.value.ComponentValue;




public class Mlx90614 extends RobotComponent<Mlx90614ChangeNotifier,ComponentSettingsChangeNotifier,Mlx90614Protocol > 

{

	protected Mlx90614AmbientSensor ambientSensor;
	protected Mlx90614ObjectSensor objectSensor; 
	
	protected int emissivity;
	protected int i2CAddress;
	
	
public Mlx90614(ComponentMetaData metaData, Mlx90614Protocol protocol)
{
	super( metaData, protocol);

	this.ambientSensor = new Mlx90614AmbientSensor(metaData, protocol.ambientProtocol);
	this.objectSensor = new Mlx90614ObjectSensor(metaData, protocol.objectProtocol);
}


public Mlx90614AmbientSensor getAmbientSensor()
{
	return (this.ambientSensor);
}


public Mlx90614ObjectSensor getObjectSensor()
{
	return (this.objectSensor);
}



public TemperatureValue getAmbientValue()
{
	return (this.ambientSensor.getTemperatureValue());
}


public TemperatureValue getObjectValue()
{
	return (this.objectSensor.getTemperatureValue());
}




public boolean remote_setSettings(int address, int emissivity)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setMlx90614Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			address, emissivity)));
}



public void setSettings(int address, int emissivity)
{
	this.i2CAddress = address;
	this.emissivity = emissivity;
	this.notifySetupChanged();

	
}


public int getI2CAddress()
{
	return(this.i2CAddress);
}



public int getEmissivity()
{
	return(this.emissivity);
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







}
