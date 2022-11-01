package de.hska.lat.robot.valueScope;

import de.hska.lat.robot.value.ComponentValue;

public interface ValueScopeScreenControl
{

	public void addValue();
	public void start();
	public void stop();
	
	
	
	
	public void moved(int delta);
	public void timeScaleChanged(int delta);
	public void sizeChanged();
	public void closeValue(ComponentValue<?> value);
}
