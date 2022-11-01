package de.hska.lat.robot.dataGraph;

public interface DataGraphTimeScaleSettingsListener
{
	public void timeOffsetChanged(int timeOffset);
	public void timeScaleChanged(float scale);
	
	public void xRasterSizeChanged(int rasterSize);
}
