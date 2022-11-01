package de.hska.lat.robot.dataGraph.valueGraph;

public interface ValueGraphSettingsListener
{	
	public void yScaleChanged(float scale);

	public void yOffsetChanged(float delta);
	
	public void yGridSizeChanged();
	
	public void yMinRangeChanged();
	
	public void yMaxRangeChanged();
}
