package de.hska.lat.behavior.control;

import java.util.ArrayList;

public class BehaviorControlSet extends ArrayList<BehaviorControl>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9182409234512343422L;

	
	
/**
 * check if one control in this set has changed 	
 * @return
 */
	
public boolean hasChanged()
{
	for (BehaviorControl control: this)
	{
		if (control.hasChanged())
			return(true);
	}
	
	return(false);
}
	
}
