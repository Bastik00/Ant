package de.hska.lat.robot.value.muscle;

import de.hska.lat.robot.value.ComponentValue;

public class MuscleValue extends ComponentValue<MuscleValue>
{
	protected float maxRange = 1;
	protected float minRange = 0;
	
	private static final String TYPE_NAME = "muscle";

	public MuscleValue(String name)
	{
		super(name);
		this.maxRange = 1;
		this.minRange = 0;
	}
	
	public MuscleValue(String name, float value)
	{
		super(name);
		this.maxRange = 1;
		this.minRange = 0;
		
		this.setValue(value);
	}
	
	/**
	 * get type name of this value
	 * 
	 * @return type name of this value
	 */
	public String getTypeName()
	{
		return (MuscleValue.TYPE_NAME);
	}
	
}
