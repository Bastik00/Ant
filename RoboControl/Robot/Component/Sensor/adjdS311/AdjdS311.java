package de.hska.lat.robot.component.sensor.adjdS311;



import java.util.ArrayList;


import de.hska.lat.comm.remote.RemoteDataTransmitter;
import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.component.sensor.adjdS311.protocol.Cmd_setAdjdS311Settings;
import de.hska.lat.robot.value.ComponentValue;



public class AdjdS311 extends Sensor<ComponentChangeNotifier, ComponentSettingsChangeNotifier, AdjdS311Protocol>  
{



	protected AdjdS311ChannelSetings redChannelSettings = new AdjdS311ChannelSetings();
	protected AdjdS311ChannelSetings greenChannelSettings = new AdjdS311ChannelSetings();
	protected AdjdS311ChannelSetings blueChannelSettings = new AdjdS311ChannelSetings();
	protected AdjdS311ChannelSetings clearChannelSettings = new AdjdS311ChannelSetings();
	
	protected AdjdS311ColorSensor colorSensor;
	
public AdjdS311(ComponentMetaData metaData, AdjdS311Protocol protocol)
{
	super( metaData, protocol );
	
	this.colorSensor = new AdjdS311ColorSensor(metaData, protocol.colorProtocol);
	
}






public boolean remote_setSettings(AdjdS311Setings settings)
{
	if (this.componentProtocol==null)
		return(false);
	
	
	return(sendData(Cmd_setAdjdS311Settings.getCommand(this.componentProtocol.cmdSetSettingsId,this.localId,
			settings)));

}


@Override
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();
	
//	values.add(this.getTemperatureValue());

	
	return (values);
}


@Override
public void onConnected()
{
	this.remote_getSettings();
}


public void setSettings(AdjdS311ChannelSetings redChannelSettings, 
			AdjdS311ChannelSetings greenChannelSettings,
			AdjdS311ChannelSetings blueChannelSettings,
			AdjdS311ChannelSetings clearChannelSettings)
{
	this.redChannelSettings = redChannelSettings;
	this.greenChannelSettings = greenChannelSettings;
	this.blueChannelSettings = blueChannelSettings;
	this.clearChannelSettings = clearChannelSettings;
	
	this.notifySetupChanged();
	
}






public AdjdS311ColorSensor getColorSensor()
{
	
	return (this.colorSensor);
}

/**
 * set transmitter for this component. All data will be sent thru this transmitter
 * @param transmitter transmitter
 */
@Override
public void setTransmitter(RemoteDataTransmitter transmitter)
{
	super.setTransmitter(transmitter);
	this.colorSensor.setTransmitter(transmitter);
	//this.barometricSensor.setTransmitter(transmitter);
}

}
