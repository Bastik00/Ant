package de.hska.lat.robot.valueScope;


import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


import de.hska.lat.robot.value.ComponentValue;

public class ValueScopePanel extends JPanel implements ValueScopeScreenControl, ComponentListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3456675224644735775L;

	
	
	protected TimeScalePanel timeScalePanel;
	
	protected ValueScopeDataPanel valueScopeDataPanel;
	
	protected ValueScopeScreenData screenData = new ValueScopeScreenData();
	
	protected ValueScalePanel valueScalePanel = new ValueScalePanel();
	
	
public ValueScopePanel()
{
	this.setLayout(null);
	
	this.setBorder(new BevelBorder(BevelBorder.LOWERED));
	
	
	this.timeScalePanel = new TimeScalePanel(this.screenData,this);
	
	this.add(this.timeScalePanel);
	
	this.valueScopeDataPanel = new ValueScopeDataPanel();
	this.add(this.valueScopeDataPanel);
	
	
	this.valueScalePanel = new ValueScalePanel();
	this.add(this.valueScalePanel);
	
	
	
//	this.add(new JButton("Hallo"));
	
	
	this.addComponentListener(this);
	
	this.autoResize();
}



protected void autoResize()
{
	
	Insets insets = this.getBorder().getBorderInsets(this);
	int width = this.getWidth()- insets.right - insets.left;
	int height = this.getHeight() - insets.bottom - insets.top;
	
	
	this.valueScalePanel.setBounds(insets.left,insets.top+45, 100, height- 45);	
	int valueScalePanelWidth = this.valueScalePanel.getWidth();
	
	this.timeScalePanel.setBounds(insets.left+valueScalePanelWidth, insets.top+5, width-valueScalePanelWidth, 40);
	
	this.valueScopeDataPanel.setBounds(insets.left+valueScalePanelWidth,insets.top+45, width-valueScalePanelWidth, height- 45);

	System.out.println("set!!");
}


@Override
public void moved(int delta)
{
	// TODO Auto-generated method stub
	this.autoResize();
}


@Override
public void timeScaleChanged(int delta)
{
	// TODO Auto-generated method stub
	this.autoResize();
}


@Override
public void sizeChanged()
{
	// TODO Auto-generated method stub
	this.autoResize();
}


@Override
public void closeValue(ComponentValue<?> value)
{
	// TODO Auto-generated method stub
	
}


@Override
public void componentHidden(ComponentEvent arg0)
{
	// TODO Auto-generated method stub
	this.autoResize();
}


@Override
public void componentMoved(ComponentEvent arg0)
{
	// TODO Auto-generated method stub
	this.autoResize();
}


@Override
public void componentResized(ComponentEvent arg0)
{
	this.autoResize();
}


@Override
public void componentShown(ComponentEvent arg0)
{
	// TODO Auto-generated method stub
	this.autoResize();
}



@Override
public void addValue()
{
	// TODO Auto-generated method stub
	
}



@Override
public void start()
{
	// TODO Auto-generated method stub
	
}



@Override
public void stop()
{
	// TODO Auto-generated method stub
	
}
	
}
