package de.hska.lat.robot.component.distanceSensor.srf10;


import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.detector.DetectorMetaData;

import de.hska.lat.robot.component.distanceSensor.srf10.protocol.Cmd_setSrf10Settings;
import de.hska.lat.robot.component.generic.distance.*;
import de.hska.lat.robot.component.sensor.SensorProtocol;

public class Srf10 extends DistanceSensor <ComponentSettingsChangeNotifier, SensorProtocol > 
{

	
	
	public static final float SFR10_BEAM_WIDTH = 30;
	
	public static final float SRF10_MAX_RANGE	= 6000;
	public static final float SRF10_MIN_RANGE	= 40;
	public static final float SRF10_MAX_GRANULARITY = 10;
	
	
	protected int gain;
	protected int address;
	

	
public Srf10(DetectorMetaData metaData,SensorProtocol protocol)
{
	super(metaData, SRF10_MIN_RANGE, SRF10_MAX_RANGE, SFR10_BEAM_WIDTH, protocol);
	this.distance.setGranularity(SRF10_MAX_GRANULARITY);
}



public void setSetup(int distanceRange, int gain, int address)
{
	
	this.distance.setDistanceRange(distanceRange);
	this.gain = gain;
	this.address = address;
	
	this.notifySetupChanged();
}


public boolean remote_setSettings(int distanceRange, int gain, int address)
{
	
	if (this.componentProtocol==null)
		return(false);
	

	return(sendData(Cmd_setSrf10Settings.getCommand(this.componentProtocol.cmdSetSettingsId, this.localId, distanceRange, gain, address)));
}






}
