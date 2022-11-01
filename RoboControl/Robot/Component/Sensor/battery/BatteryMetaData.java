package de.hska.lat.robot.component.battery;

import de.hska.lat.robot.component.ComponentMetaData;

public class BatteryMetaData extends ComponentMetaData
{

	/**
	 * minimal voltage of this battery 
	 */
	public final float maxVoltage;
	
	/**
	 * maximal voltage of this battery 
	 */
	public final float minVoltage;
	
	/**
	 * 
	 * @param metaData 
	 * @param minVoltage this battery minimal Voltage
	 * @param maxVoltage this battery maximal voltage
	 */
	public BatteryMetaData(ComponentMetaData metaData, float minVoltage, float maxVoltage)
	{
		super(metaData);
	
		this.minVoltage = minVoltage;
		this.maxVoltage = maxVoltage;
	}

	
	
}
