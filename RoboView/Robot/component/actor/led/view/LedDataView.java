package de.hska.lat.robot.component.actor.led.view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;


import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.actor.led.Led;
import de.hska.lat.robot.component.actor.led.RgbLed;
import de.hska.lat.robot.component.actor.view.ActorDataView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;


public class LedDataView  extends ActorDataView<Led> implements ComponentChangeNotifier
{

	
	protected static final int width = 160;
	protected static final int height = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	protected JLabel brightness;
	
	
public LedDataView(Led led)
{
	super(led);
	

	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(LedDataView.width);
}

@Override
protected int getViewHeight()
{
	return(LedDataView.height);
}




	
public void buildView()
{
	super.buildView();
	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	
	this.brightness = new JLabel();
	this.brightness.setBounds(insets.left+65, insets.top+5, 40, 20);
	this.brightness.setBackground(Color.WHITE);
	this.add(this.brightness);
	
}
	
	



public static ComponentView createView(Led led)
{

	if (led!=null)
	{
		return(new LedDataView(led));
	}
	else
	{
		return(new MissingComponentView(RgbLed.class.getName()));
	}
}



}
