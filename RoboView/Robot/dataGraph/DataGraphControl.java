package de.hska.lat.robot.dataGraph;

import de.hska.lat.robot.value.ComponentValue;

public interface DataGraphControl
{

	public void addValue();
	public void play();
	public void stop();
	public void record();
	
	public void save();
	public void load();
	
	public void removeValue(ComponentValue<?> value);
	
	
}
