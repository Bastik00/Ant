package de.hska.lat.robot.component.currentSensor;


import java.awt.Insets;


import javax.swing.JLabel;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.currentSensor.CurrentSensor;
import de.hska.lat.robot.component.currentSensor.CurrentSensorChangeNotifier;
import de.hska.lat.robot.component.currentSensor.CurrentSensorSetupChangeNotifier;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;



public class CurrentSetupView extends ComponentSettingsView<CurrentSensor>  implements ChangeListener,  CurrentSensorChangeNotifier,CurrentSensorSetupChangeNotifier{



	/**
	 * 
	 */
	private static final long serialVersionUID = -4928022220159207582L;


	private JLabel rawValue;
	
	
	private JSpinner threshold;
	private JSpinner windowSize;

	private SpinnerNumberModel thresholdSpinerModel;
	private SpinnerNumberModel windowSizeSpinerModel;
	

	

	
	private static final String STRING_TRESHOLD = "threshold";
	private static final String STRING_WINDOW_SIZE = "window size";
	private static final String STRING_RAW_VALUE	= "value";
	
	
	protected static final int width = 225;
	protected static final int height = 105;
	
	
private CurrentSetupView(CurrentSensor current)  
{
	super(current);
	
	buildView();
	
	current.addSensorListener(this);
	current.addSetupListener(this);
	




}



protected void buildView()
{
	
	
	super.buildView();

	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	JLabel tmpLabel;

	
	tmpLabel= new JLabel(STRING_RAW_VALUE);
	tmpLabel.setBounds(insets.left+5,insets.top+5, 80, 25);
	this.add(tmpLabel);
	 
	
	this.rawValue= new JLabel("0");
	this.rawValue.setBounds(insets.left+100,insets.top+5, 80, 25);
	this.add(this.rawValue);
	
	tmpLabel= new JLabel(STRING_TRESHOLD);
	tmpLabel.setBounds(insets.left+5,insets.top+30, 80, 25);
	this.add(tmpLabel);
	 
	
	this.thresholdSpinerModel = new SpinnerNumberModel(512, 0, 65535, 1);

	this.threshold= new JSpinner(this.thresholdSpinerModel);
	this.threshold.setBounds(insets.left+75,insets.top+30, 80, 25);
	this.threshold.addChangeListener(this);
	this.add(this.threshold);
	
	
	tmpLabel= new JLabel(STRING_WINDOW_SIZE);
	tmpLabel.setBounds(insets.left+5,insets.top+55, 100, 25);
	this.add(tmpLabel);
	 
	
	this.windowSizeSpinerModel = new SpinnerNumberModel(4, 0, 65535, 1);

	this.windowSize= new JSpinner(this.windowSizeSpinerModel);
	this.windowSize.setBounds(insets.left+75,insets.top+55, 40, 25);
	this.windowSize.addChangeListener(this);
	this.add(this.windowSize);
	
	
	//windowSize
	
	this.addSetButton(insets.left+5, insets.top+80, 50, 25);
	this.addGetButton(insets.left+60, insets.top+80, 50, 25);
	
	this.addSaveButton(insets.left+115, insets.top+80, 50, 25);
	this.addLoadButton(insets.left+170, insets.top+80, 50, 25);	
	

}


@Override
protected int getViewWidth()
{
	return(CurrentSetupView.width);
}

@Override
protected int getViewHeight()
{
	return(CurrentSetupView.height);
}



@Override
public void stateChanged(ChangeEvent event) 
{
}


@Override
protected boolean setSettings()
{
	int windowSize = (Integer) this.windowSizeSpinerModel.getNumber();
	int threshold = (Integer) this.thresholdSpinerModel.getNumber();
	
	return(this.component.remote_setSettings(windowSize,threshold));
}






@Override
public void currentValueChanged(CurrentSensor current)
{
	rawValue.setText(String.valueOf(current.getActual()));
}



@Override
public void currentThresholdChanged(CurrentSensor current)
{
	this.thresholdSpinerModel.setValue(current.getThreshold());
	
}



@Override
public void currentWindowSizeChanged(CurrentSensor current)
{
	this.windowSizeSpinerModel.setValue(current.getWindowSize());
	
}


/**
 * creates new current setup view and link it given current 
 * @param servo current to be connected with created view
 * @return a new current setup view
 */

public static ComponentView createView(CurrentSensor current)
{

	if (current!=null)
	{
		return(new CurrentSetupView(current));
	}
	else
	{
		return(new MissingComponentView(CurrentSensor.class.getName()));
	}
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	// TODO Auto-generated method stub
	
}


}
