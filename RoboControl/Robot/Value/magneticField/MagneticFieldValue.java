package de.hska.lat.robot.value.magneticField;

import de.hska.lat.robot.value.ComponentValue;


public class MagneticFieldValue extends ComponentValue<MagneticFieldValue>

{

	public MagneticFieldValue(String name)
	{
		super(name);
		this.minRange = -1200;
		this.maxRange = +1200;
	}
	
	public MagneticFieldValue(String name, float tRange){
		super(name);
		setMagneticFieldRange(tRange);
	}
	
	public void setMagneticFieldRange(float tRange) {
		this.minRange = -tRange;
		this.maxRange = +tRange;
	}
	
}
