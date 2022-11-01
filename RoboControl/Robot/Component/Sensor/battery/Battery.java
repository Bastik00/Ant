package de.hska.lat.robot.component.battery;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.value.voltage.VoltageValue;

/**
 * Implements an battery as an RoboView component 
 * @author tavin
 *
 */
public class Battery extends RobotComponent<BatteryChangeNotifier, ComponentSettingsChangeNotifier,  ComponentProtocol>
{
	
	
	protected VoltageValue voltage;
	
	protected float lowBatteryPoint; 
	protected float criticalBatteryPoint;

	
public Battery(BatteryMetaData metaData,ComponentProtocol protocol)
{
	super(metaData, protocol);
	
	this.voltage = new VoltageValue(metaData.getName(), metaData.minVoltage, metaData.maxVoltage);
}



/**
 * Set the point below them the battery is considered nearly empty
 * @param lowBatteryPoint point below them the battery is considered nearly empty
 */
public void setBatteryLowPoint(float lowBatteryPoint)
{
	this.lowBatteryPoint=lowBatteryPoint;
}


/**
 * Set the point below them the battery is considered empty
 * @param criticalBatteryPoint point below them the battery is considered empty
 */

public void setBatteryCriticalPoint(float criticalBatteryPoint)
{
	this.criticalBatteryPoint=criticalBatteryPoint;
}


/**
 * return the voltage an which the battery is considered to be nearly empty
 * @return current battery low point
 */
public float getBatteryLowPoint()
{
	return(this.lowBatteryPoint);
}


/**
 * return the voltage an which the battery is considered to be empty
 * @return current battery critical point
 */
public float getBatteryCriticalPoint()
{
	return(this.criticalBatteryPoint);
}


public boolean setVoltage(int voltage)
{
	if (this.sensorListener==null)
		return(false);

	for (int index=0;index<this.sensorListener.size();index++)
	{
		this.sensorListener.get(index).voltageChanged(this.globalId, voltage);
	}
	return true;
}


/**
 * return the maximal voltage of full loaded battery
 * @return maximal voltage for battery
 */
public float getMaxVoltage()
{
	return (this.voltage.getMaxRange());
}



/**
 * return voltage considered when the battery is empty  
 * @return voltage of empty battery
 */

public float getMinVoltage()
{
	return (this.voltage.getMinRange());
}



/**
 * get voltage value for this battery. The VoltageValue contains the measured value in volts.   
 * @return VoltageValue of this battery
 */
public VoltageValue getVoltageValue()
{
	return (this.voltage);
}


	
	
	
/*
public void processCritical(Msg_batteryCritical remoteData)
{
	this.setCritical(remoteData.getStatus());
}



public void processVoltage(Msg_batteryVoltage remoteData)
{
	this.setVoltage(remoteData.getStatus());
}



public void processMaxVoltage(Msg_batteryMaxVoltage remoteData)
{
	this.setMaxVoltage(remoteData.getStatus());
}



public void processMinVoltage(Msg_batteryMinVoltage remoteData)
{
	this.setMinVoltage(remoteData.getStatus());
	
}
*/
	
}
