package de.hska.lat.robot.value;

import java.util.ArrayList;



public abstract class ComponentValue<v>
{

	protected ArrayList<ComponentValueChangeListener> listeners = new ArrayList<ComponentValueChangeListener>(10);

	private static String TYPE_NAME = "generic";
	private static String TYPE_DESCRIPTION = "generic";
	
	
	protected String name = "generic";

	protected float value;
	protected float minRange = Float.NEGATIVE_INFINITY;
	protected float maxRange = Float.POSITIVE_INFINITY;

	protected boolean overflow;
	protected boolean underflow;

	protected boolean notifyAllways = true;

	protected boolean valid;

	
	
	public ComponentValue(String name)
	{

		this.name = name;
	}

	public ComponentValue(String name, float minRange, float maxRange)
	{
		this.name = name;
		this.minRange = minRange;
		this.maxRange = maxRange;

	}

	/**
	 * set new name for this value
	 * 
	 * @param name
	 *            new values name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * set minimum und maximum range of this float value
	 * 
	 * @param minValue
	 *            new minimum range
	 * @param maxValue
	 *            new maximum range
	 */

	public void setRange(float minRange, float maxRange)
	{
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	/**
	 * get actual minimum range of this numeric value
	 * 
	 * @return minimum range
	 */
	public float getMinRange()
	{
		return (this.minRange);
	}

	/**
	 * get actual maximum range of this numeric value
	 * 
	 * @return maximum range
	 */

	public float getMaxRange()
	{
		return (this.maxRange);
	}


	/**
	 * get type name of this value
	 * 
	 * @return type name of this value
	 */
	public String getTypeName()
	{
		return (ComponentValue.TYPE_NAME);
	}

	
	
	/**
	 * get description of this type	
	 * @return description of this value
	 */
	public String getTypeDescription()
	{
		return (ComponentValue.TYPE_DESCRIPTION);
	}

	
	
	
	/**
	 * get name of this value
	 * 
	 * @return name of this value
	 */
	public String getName()
	{
		return (this.name);
	}

	
	
	
	
	
	public void addListener(ComponentValueChangeListener listener)
	{
		this.listeners.add(listener);
	}

	/**
	 * remove value change listener
	 * 
	 * @param listener
	 */
	public void removeListener(ComponentValueChangeListener listener)
	{
		this.listeners.remove(listener);
	}

	/**
	 * check if this value has this name
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasName(String nameToCheck)
	{
		if (name.equals(nameToCheck))
			return (true);

		return false;
	}

	public float getValue()
	{
		return (value);
	}

	/**
	 * set new value of this instance. If value out of range it will be
	 * corrected to range.
	 * 
	 * @param value
	 *            new value of this instance
	 * @return actual value
	 */

	public float setValue(float value)
	{
		boolean valueChanged = false;

		if (this.notifyAllways == true)
		{
			valueChanged = true;
		} else if (value != this.value)
		{
			valueChanged = true;
		}

		if (value > this.maxRange)
		{
			this.setOverflowValue();
		} else if (value < this.minRange)
		{
			this.setUnderflowValue();
		} else
		{
			this.overflow = false;
			this.underflow = false;
			this.value = value;
			this.valid = true;
		}

		if (valueChanged == true)
		{
			this.notifyValueChanged();
		}

		return (this.value);
	}

	protected void setOverflowValue()
	{
		value = this.maxRange;
		this.overflow = true;
		this.underflow = false;
		this.valid = true;
	}

	protected void setUnderflowValue()
	{
		value = this.minRange;
		this.overflow = false;
		this.underflow = true;
		this.valid = true;
	}

	protected void notifyValueChanged()
	{
		for (ComponentValueChangeListener listener : this.listeners)
		{
			listener.valueChanged(this);
		}
	}

	/**
	 * check if this value is valid
	 * 
	 * @return
	 */

	public boolean isValid()
	{
		return (this.valid);
	}

	@Override
	public String toString()
	{
		return (this.name);
	}

	
	
	
public boolean actualize()
{
	
	return(false);
}
	
	/**
	 * format given float value to a string with
	 * 
	 * @param value
	 * @param fraction
	 * @return
	 */

	public static String toFormatedFractionString(float value, int fraction)
	{
		int fractionSize;
		String valueAsString;

		valueAsString = String.valueOf(value);
		fractionSize = valueAsString.length() - valueAsString.indexOf('.');

		if (fractionSize > fraction)
			fractionSize = fraction + 1;

		valueAsString = valueAsString.substring(0, valueAsString.indexOf('.')
				+ fractionSize);

		return (valueAsString);
	}


}
