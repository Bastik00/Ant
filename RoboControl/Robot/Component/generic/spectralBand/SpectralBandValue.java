package de.hska.lat.robot.component.generic.spectralBand;

import de.hska.lat.robot.value.ComponentValue;

public class SpectralBandValue extends ComponentValue<SpectralBandValue>
{

	protected int level;
	
	
	private static final String TYPE_NAME = "spectralBand";
	
	public SpectralBandValue(String name)
	{
		super(name);
		this.minRange =0.0f;
		this.maxRange =1.0f;
	}

	protected void setOverflowValue()
	{
		this.value = 0;
		this.valid = false;
		this.overflow = false;
		this.underflow = false;
	}


	protected void setUnderflowValue()
	{
		this.value = 0;
		this.valid = false;
		this.overflow = false;
		this.underflow = false;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public String getTypeName()
	{
		return(SpectralBandValue.TYPE_NAME);
	}
}
