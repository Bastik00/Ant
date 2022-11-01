package de.hska.lat.ant.behavior.led.view;



import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.ant.behavior.led.AntLedBlinkBehavior;
import de.hska.lat.ant.behavior.led.BrightnessControl;
import de.hska.lat.ant.behavior.led.FrequencyControl;
import de.hska.lat.behavior.arbiter.view.ArbiterViewer;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;
import de.hska.lat.robot.value.FloatValue;

public class AntTailLeftLedBlinkBehaviorViewer extends ArbiterViewer<AntLedBlinkBehavior>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136329052303381549L;


	
	protected RobotSettings settings;
	
	protected JSlider brightnessSlider;
	protected JSlider frequencySlider;
	
	protected JLabel frequencyLabel;
	
	protected BrightnessControl brightnessControl;
	protected FrequencyControl frequencyControl;
	
	
	
public	AntTailLeftLedBlinkBehaviorViewer(RobotBehavior<?> behavior, AntLedBlinkBehavior arbiter, RobotSettings settings)
{
	
	super(behavior, arbiter,  settings);
	
	
	this.brightnessControl = arbiter.getBrightnessControl();
	this.frequencyControl = arbiter.getFrequencyControl();
	
	
	this.setSize(300,100);
	this.build();
}
	

protected void build()
{
	
	
	this.brightnessSlider = new JSlider (0,100);
	this.brightnessSlider.setSize(100, 20);
	this.brightnessSlider.setMajorTickSpacing(10);
	this.brightnessSlider.setMinorTickSpacing(20);
	this.brightnessSlider.setPaintTicks(true); 
	this.brightnessSlider.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			AntTailLeftLedBlinkBehaviorViewer.this.brightnessControl.setValue
						(AntTailLeftLedBlinkBehaviorViewer.this.brightnessSlider.getValue()/100.0f);
		}
		
	});
	this.add(this.brightnessSlider);

	
	
	this.frequencySlider = new JSlider (0,100);
	this.frequencySlider.setSize(100, 20);
	this.frequencySlider.setMajorTickSpacing(10);
	this.frequencySlider.setMinorTickSpacing(20);
	this.frequencySlider.setPaintTicks(true); 
	this.frequencySlider.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			AntTailLeftLedBlinkBehaviorViewer.this.frequencyControl.setValue
						(AntTailLeftLedBlinkBehaviorViewer.this.frequencySlider.getValue());
			
			
			float frequency = AntTailLeftLedBlinkBehaviorViewer.this.frequencyControl.getValue();
			
			int timebase = AntTailLeftLedBlinkBehaviorViewer.this.arbiter.getTimeBase();
			
			frequency = (frequency*timebase) / 1000.0f;
			
			AntTailLeftLedBlinkBehaviorViewer.this.frequencyLabel.setText(FloatValue.toFormatedFractionString(frequency, 2)+" hz");
		}
		
	});

	this.add(this.frequencySlider);

	
	this.frequencyLabel = new JLabel();
	this.add(this.frequencyLabel);
	
}




}
