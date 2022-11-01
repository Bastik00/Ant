package de.hska.lat.robot.dataGraph.valueGraph;

import javax.swing.JPanel;

public abstract class ValueGraphAbstract extends JPanel implements ValueGraphSettingsListener
{

	private static final long serialVersionUID = -2302845825448534775L;

	protected final ValueGraphSettings settings;
	
	protected float displayRange;
	protected float displayMinRange;
	protected float displayMaxRange;
	
//	protected final NiceScale niceScale;
	
	public ValueGraphAbstract(ValueGraphSettings settings)
	{
		super();
		this.settings = settings;
	//	this.niceScale = new NiceScale(this.settings.getMinRange(), this.settings.getMaxRange());
	}
	
	public void calculate()
	{

		float dataRange = Math.abs(settings.getMaxRange() - settings.getMinRange()); 
		float middlepoint = settings.getMinRange() + (dataRange / 2);
		
		this.displayRange = dataRange * settings.getYScale();
		
		this.displayMinRange = (middlepoint - (this.displayRange / 2)) + settings.getYOffset();
		this.displayMaxRange = (middlepoint + (this.displayRange / 2)) + settings.getYOffset();
	}

	public void yScaleChanged(float yScale) {
		this.calculate();
		this.update();
	}

	public void yGridSizeChanged() {
		this.calculate();
		this.update();
	}
	public void yMaxRangeChanged() {
		this.calculate();
		this.update();
	}
	
	public void yMinRangeChanged() {
		this.calculate();
		this.update();
	}
	
	public void yOffsetChanged(float yOffset) {
		this.calculate();
		this.update();
	}
	
	public abstract void update();
	
	//algorithm to calculate a nice step size
    public float calcStepSize()
    {
    	NiceScale scale = new NiceScale(displayMinRange, displayMaxRange);
    	double maxTicks = this.getHeight() / settings.getYTargetRasterSize();
    	if (maxTicks < 2) {
    		maxTicks = 2;
    	}
    	scale.setMaxTicks(maxTicks);
   
    	return scale.getTickSpacing();
    }
	
	
}


