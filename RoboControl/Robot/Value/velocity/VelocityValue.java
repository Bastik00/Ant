package de.hska.lat.robot.value.velocity;

import de.hska.lat.robot.value.ComponentValue;

public class VelocityValue extends
		ComponentValue<VelocityValue> // ComponentValueChangeListener<BrightnessValue>>
{

	private static final String TYPE_NAME = "motor speed";

	public VelocityValue(String name)
	{
		super(name);
//		this.minRange = 0;
//		this.maxRange = 1000;
	}

	@Override
	public String getTypeName()
	{
		return (VelocityValue.TYPE_NAME);
	}

}
