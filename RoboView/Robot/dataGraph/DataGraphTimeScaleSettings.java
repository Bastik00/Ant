package de.hska.lat.robot.dataGraph;

import java.util.ArrayList;
import java.util.List;


public class DataGraphTimeScaleSettings
{
	protected static final int DEFAULT_X_RASTER_SIZE = 50;
	
	protected final List<DataGraphTimeScaleSettingsListener> listeners;
	
	int timeScale = 10 ;
	int timeOffset = 0;

	private int xRasterSize;
	
	
	public DataGraphTimeScaleSettings() {
		this.listeners = new ArrayList<DataGraphTimeScaleSettingsListener>();
		this.xRasterSize = DEFAULT_X_RASTER_SIZE;
	}
		
	
	public int getTimeScale()
	{
		return this.timeScale;
	}
	
	
	public void scaleTimeScale(int delta)
	{
		this.timeScale -= delta;
		
		if (this.timeScale < 10)
		{
			this.timeScale = 10;
		}
		
		for (DataGraphTimeScaleSettingsListener listener : listeners) {
			listener.timeScaleChanged(this.timeScale);
		}
	}
	
	
	
	public int getTimeOffset()
	{
		return(this.timeOffset);
	}
		
	
	
	public void moveXOffset(int delta)
	{
		
		this.timeOffset += this.timeScale * delta;
		
		
		if (this.timeOffset < 0)
		{
			this.timeOffset = 0;
		}
	
		for (DataGraphTimeScaleSettingsListener listener : listeners) {
			listener.timeOffsetChanged(timeOffset);
		}
		
		System.out.println("this.timeOffset " + this.timeOffset);
	}
	
	
	public void setxRasterSize(int xRasterSize)
	{
		this.xRasterSize = xRasterSize;
		//notify listener
		for (DataGraphTimeScaleSettingsListener listener : listeners) {
			listener.xRasterSizeChanged(xRasterSize);
		}
	}
	
	
	public int getxRasterSize()
	{
		return xRasterSize;
	}
	
	public void addListener(DataGraphTimeScaleSettingsListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(DataGraphTimeScaleSettingsListener listener) {
		listeners.remove(listener);
	}

}
