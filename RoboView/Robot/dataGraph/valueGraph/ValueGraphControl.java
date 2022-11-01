package de.hska.lat.robot.dataGraph.valueGraph;




import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.TitledBorder;

import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.FloatValue;
import de.hska.lat.robot.value.valueViewer.ValueGraphNotifier;


public class ValueGraphControl extends JPanel implements  ValueGraphNotifier, ValueGraphSettingsListener
{

	private static final long serialVersionUID = 5792240831737389943L;

	protected ComponentValue<?> componentValue; 
	protected ValueGraphSettings settings;
    
    protected JLabel lOffsetValue;
    protected JLabel lZoomValue;
    protected JLabel lZoomStep;
    
    
public ValueGraphControl(ComponentValue<?> componentValue, ValueGraphSettings settings) 
{
	
	this.componentValue = componentValue;
	this.settings = settings;
	this.settings.addListener(this);
	
	this.setBorder(new TitledBorder(this.componentValue.getName()));
	
	this.setLayout(new GridLayout(0,2));
	
	this.setSize(180, 100);
	
	this.buildPanel();
	this.updateZoomLabels();
	this.updateOffsetLabel();
	
}



protected void buildPanel()
{
	
	this.lZoomValue = new JLabel();
	this.lZoomValue.setHorizontalAlignment(JLabel.RIGHT);
	this.lZoomValue.setText("...");
	
	this.lZoomStep = new JLabel();
	this.lZoomStep.setHorizontalAlignment(JLabel.RIGHT);
	this.lZoomStep.setText("...");
	
	this.lOffsetValue = new JLabel();
	this.lOffsetValue.setHorizontalAlignment(JLabel.RIGHT);
	this.lOffsetValue.setText("...");

	
	this.add(new JLabel("Zoom:"));
	this.add(this.lZoomValue);
	this.add(new JLabel("Zoom Step:"));
	this.add(this.lZoomStep);
	this.add(new JLabel("Offset:"));
	this.add(this.lOffsetValue);

}

protected void updateZoomLabels() {
	
	float scale = settings.getYScale();
	long zoom = (long) (100.f / scale);
	
	this.lZoomValue.setText(String.format("%,d", zoom) + "%");
	this.lZoomValue.setToolTipText("Raw value: " + String.valueOf(scale));
	
	this.lZoomStep.setText(String.valueOf(settings.getYScaleStep()));	
}

protected void updateOffsetLabel() {
	this.lOffsetValue.setText(FloatValue.toFormatedFractionString(settings.getYOffset(), 3));
}



@Override
public void cursorMoved(int time, float value)
{
	//this.time.setText(time+"ms");
//	this.value.setText(FloatValue.toFormatedFractionString(value, 3));
}



@Override
public void onFocus()
{
	// TODO Auto-generated method stub
	
}



@Override
public void lostFocus()
{
	// TODO Auto-generated method stub
	
}



@Override
public void yScaleChanged(float scale)
{
	this.updateZoomLabels();
}


@Override
public void yOffsetChanged(float delta)
{
	this.updateOffsetLabel();
}



@Override
public void yGridSizeChanged()
{
	// TODO Auto-generated method stub
	
}



@Override
public void yMinRangeChanged()
{
	// TODO Auto-generated method stub
	
}



@Override
public void yMaxRangeChanged()
{
	// TODO Auto-generated method stub
	
}


}
