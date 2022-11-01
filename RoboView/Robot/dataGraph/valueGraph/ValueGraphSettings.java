package de.hska.lat.robot.dataGraph.valueGraph;

import java.util.ArrayList;
import java.util.List;

public class ValueGraphSettings
{
	protected static final int DEFAULT_Y_RASTER_SIZE = 24;
	
	protected static final int DEFAULT_Y_SCALE_STEP = 1;
	protected static final float DEFAULT_Y_SCALE_BASE = 0.8f;
	
	protected static final float DEFAULT_MIN_RANGE = -100f;
	protected static final float DEFAULT_MAX_RANGE = 100f;
	
	protected static final float DEFAULT_DISPLAY_OFFSET = 0f;
	
	protected float yScale;
	protected int yScaleStep;
	protected float yScaleBase;
	
	protected int yTargetRasterSize;

	protected float maxRange;
	protected float minRange;
	protected float yOffset;
	
	protected List<ValueGraphSettingsListener> listeners;
	
	public ValueGraphSettings() {
		this.listeners = new ArrayList<ValueGraphSettingsListener>();
		
		this.yTargetRasterSize = DEFAULT_Y_RASTER_SIZE;
		this.minRange = DEFAULT_MIN_RANGE;
		this.maxRange = DEFAULT_MAX_RANGE;
		this.yScaleBase = DEFAULT_Y_SCALE_BASE;
		this.setYScaleStep(DEFAULT_Y_SCALE_STEP);
		
	}


	public float getYScale()
	{
		return yScale;
	}


	private void setYScale(float yScale)
	{
		this.yScale = yScale;
		
		this.setYOffset(this.calculateNewOffset(this.getYOffset()));
		//notify listener
		for (ValueGraphSettingsListener listener : listeners) {
			listener.yScaleChanged(this.yScale);
		}
	}
	
	public int getYScaleStep() {
		return yScaleStep;
	}
	
	public void setYScaleStep(int yScaleStep) {
		this.yScaleStep = yScaleStep;
		
		float scale = (float) Math.pow(yScaleBase, yScaleStep - 1);
		
		this.setYScale(scale);
	}

	public int getYTargetRasterSize()
	{
		return yTargetRasterSize;
	}


	public void setYTargetRasterSize(int yRasterSize)
	{
		this.yTargetRasterSize = yRasterSize;
		//notify listener
		for (ValueGraphSettingsListener listener : listeners) {
			listener.yGridSizeChanged();
		}
	}
	
	public float getMaxRange()
	{
		return maxRange;
	}


	public void setMaxRange(float maxRange)
	{
		this.maxRange = maxRange;
		//notify listener
		for (ValueGraphSettingsListener listener : listeners) {
			listener.yMaxRangeChanged();
		}
	}


	public float getMinRange()
	{
		return minRange;
	}


	public void setMinRange(float minRange)
	{
		this.minRange = minRange;
		//notify listener
		for (ValueGraphSettingsListener listener : listeners) {
			listener.yMinRangeChanged();
		}
	}
	
	public float getRange() {
		return Math.abs(this.maxRange - this.minRange);
	}


	public float getYOffset()
	{
		return yOffset;
	}


	public void setYOffset(float displayOffset)
	{
		this.yOffset = calculateNewOffset(displayOffset);
		
//		System.out.println("setYOffset " + yOffset);
		//notify listener
		for (ValueGraphSettingsListener listener : listeners) {
			listener.yOffsetChanged(this.yOffset);
		}
	}
	
	private float calculateNewOffset(float displayOffset) {
		float maxRange = this.calculateDisplayRange();
		float minOffset = (this.getRange() - maxRange) / (-2.0f);
		float maxOffset = (this.getRange() - maxRange) / (2.0f);
		
		//transform -0 to 0
		if (minOffset == 0.0f) minOffset = 0.0f;
		if (maxOffset == 0.0f) maxOffset = 0.0f;
		
		if (displayOffset > maxOffset) {
			return maxOffset;
		} else if (displayOffset < minOffset) {
			return minOffset;
		} else {
			return displayOffset;
		}
	}
	
	private float calculateDisplayRange() {
		float dataRange = Math.abs(this.maxRange - this.minRange);
		float displayRange = dataRange * this.getYScale();
		
		return displayRange;
	}
	
	public float getMiddlePoint() {
		return (Math.abs(this.maxRange + this.minRange)/2.0f);
	}

	
	public void addListener(ValueGraphSettingsListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(ValueGraphSettingsListener listener) {
		listeners.remove(listener);
	}	
}
