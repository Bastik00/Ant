package de.hska.lat.robot.component.sensor.bmp085;


import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteDataTransmitter;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;

import de.hska.lat.robot.component.detector.DetectorMetaData;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureValue;
import de.hska.lat.robot.component.sensor.bmp085.protocol.Cmd_setBmp085Settings;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.barometric.BarometricValue;



public class Bmp085 extends  RobotComponent< Bpm085ChangeNotifier,ComponentSettingsChangeNotifier,Bmp085Protocol>  
{



protected Bmp085TemperatureSensor temperatureSensor;
protected Bmp085BarometricSensor barometricSensor;


protected Bmp085Resolution resolution;


public Bmp085(DetectorMetaData metaData,Bmp085Protocol protocol) 
{
	super(metaData, protocol);
	
	this.temperatureSensor = new Bmp085TemperatureSensor(metaData, protocol.temperatureProtocol);
	this.barometricSensor = new Bmp085BarometricSensor(metaData,protocol.presureProtocol);
}





public Bmp085TemperatureSensor getTemperatureSensor()
{
	return (this.temperatureSensor);
}




public Bmp085BarometricSensor getBarometricSensor()
{
	return (this.barometricSensor);
}



public BarometricValue getBarometricValue()
{
	return (this.barometricSensor.getBarometricValue());
}



public TemperatureValue getTemperatureValue()
{

	return(this.temperatureSensor.getTemperatureValue());
}



public boolean remote_setSettings(Bmp085Resolution resolution)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setBmp085Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			resolution)));
}



@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	ArrayList<ComponentValue<?>> values = super.getDataValues();
	
	values.add(this.getTemperatureValue());
	values.add(this.getBarometricValue());

	return (values);
}





public Bmp085Resolution getResolution()
{
	return (this.resolution);
}





public void setSettings(Bmp085Resolution resolution)
{
	this.resolution = resolution;
	this.notifySetupChanged();
}



/**
 * set transmitter for this component. All data will be sent thru this transmitter
 * @param transmitter transmitter
 */
@Override
public void setTransmitter(RemoteDataTransmitter transmitter)
{
	super.setTransmitter(transmitter);
	this.temperatureSensor.setTransmitter(transmitter);
	this.barometricSensor.setTransmitter(transmitter);
}




}
