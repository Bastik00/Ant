package de.hska.lat.behavior.information;

import java.util.ArrayList;

public class BehaviorInformations extends ArrayList<InformationSet>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1211469598405905457L;

	boolean changed;

public boolean hasChanged() 
{

	
	return(this.changed);
}
	
	
/**
 * capture all informations 	
 */
	
public void capture()
{
	boolean changed = false;
	
	for (InformationSet informationSet : this)
	{
		changed |= informationSet.hasChanged();
	}
	
	this.changed = changed;
	
	
	// filters needs to be refreshed !!!
	
	for (InformationSet informationSet : this)
	{
		informationSet.capture();
	}
}
	
}
