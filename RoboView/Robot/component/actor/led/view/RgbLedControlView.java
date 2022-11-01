package de.hska.lat.robot.component.actor.led.view;

import java.awt.Color;
import java.awt.Insets;



import javax.swing.JLabel;

import javax.swing.JSlider;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.color.HsvColor;
import de.hska.lat.color.RgbColor;
import de.hska.lat.robot.component.actor.led.Led;
import de.hska.lat.robot.component.actor.led.RgbLed;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;

import de.hska.lat.robot.ui.settings.UiSettings;



public class RgbLedControlView extends ComponentView implements ChangeListener, ColorSelectorListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238144556537574802L;

	
	
	protected final static String COLOR_TEXT = "color";
	protected final static String HUE_TEXT = "hue";
	protected final static String SATURATION_TEXT = "satur.";
	protected final static String VALUE_TEXT = "value";
	
	protected static final int width = 160;
	protected static final int height = 100;


	protected static final String colorKey = ".color";
	
	
	protected RgbLed led;
	
	protected HsvColor hsvColor;
	
	protected JSlider hue;
	protected JSlider satuaration;
	protected JSlider value;
	
	protected ColorSelector colorSelector;
	
	
	
	
public RgbLedControlView(RgbLed led)
{
	super(led.getComponentName(), false);


	this.led = led;
	buildView();

	
	this.recoverSettings();	
}


@Override
protected void buildView()
{
	
	
	super.buildView();

	
	Insets insets = this.getBorder().getBorderInsets(this);
	
	JLabel hueLabel = new JLabel(RgbLedControlView.HUE_TEXT);
	hueLabel.setBounds(insets.left+5, insets.top+35, 100, 20);;
	this.add(hueLabel);
	
	JLabel satuarationLabel = new JLabel(RgbLedControlView.SATURATION_TEXT);
	satuarationLabel.setBounds(insets.left+5, insets.top+55, 100, 20);;
	this.add(satuarationLabel);
	
	JLabel brightnessLabel = new JLabel(RgbLedControlView.VALUE_TEXT);
	brightnessLabel.setBounds(insets.left+5, insets.top+75, 100, 20);;
	this.add(brightnessLabel);
	
	
	this.hue = new JSlider(0,255);
	this.hue.setBounds(insets.left+50, insets.top+35, 100, 20);
	this.hue.addChangeListener(this);
	this.add(this.hue);
	
	this.satuaration = new JSlider(0,255);
	this.satuaration.setBounds(insets.left+50, insets.top+55, 100, 20);
	this.satuaration.addChangeListener(this);
	this.add(this.satuaration);
	
	this.value = new JSlider(0,255);
	this.value.setBounds(insets.left+50, insets.top+75, 100, 20);
	this.value.addChangeListener(this);
	this.add(this.value);
	
	
	JLabel colorSelectorLabel = new JLabel(RgbLedControlView.COLOR_TEXT);
	colorSelectorLabel.setBounds(insets.left+5, insets.top+5, 100, 20);;
	this.add(colorSelectorLabel);
	

	this.colorSelector = new ColorSelector();
	this.colorSelector.setBounds(insets.left+65, insets.top+5, 40, 20);
	this.colorSelector.setBackground(Color.WHITE);
	this.colorSelector.setColorListener(this);
	this.add(this.colorSelector);
}



protected  void recoverSettings()
{
	
	
	Color color = new Color(UiSettings.recoverInt(this.settingsKey+RgbLedControlView.colorKey,0x00ffff));
	
	
	RgbColor rgbColor = new RgbColor(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);
	this.hsvColor = rgbColor.getAsHsv();
	
	this.colorSelector.setColor(color);
		
    this.hue.getModel().setValue((int) (this.hsvColor.h * 255f / (2 * Math.PI)));
    this.satuaration.getModel().setValue((int) (this.hsvColor.s * 255));
    this.value.getModel().setValue((int) (this.hsvColor.v * 255));
}


protected  void saveColor(Color color)
{
	UiSettings.saveInt(this.settingsKey+RgbLedControlView.colorKey, color.getRGB());
}



@Override
protected int getViewWidth()
{
	return(RgbLedControlView.width);
}

@Override
protected int getViewHeight()
{
	return(RgbLedControlView.height);
}



//Cmd_setLedBrightness
/**
 * Create some view and set brightness.
 * @param led RgbLed object containing brightness
 * @return
 */

public static ComponentView createView(RgbLed led)
{
	if (led!=null)
	{
		return(new RgbLedControlView(led));
	}
	else
	{
		return(new MissingComponentView(Led.class.getName()));
	}
}


@Override
public void stateChanged(ChangeEvent changeEvent)
{
	float h;
	float s;
	float v;
	Object source;
	
	source = changeEvent.getSource();
	
	if (source == this.hue || source == this.value || source == this.satuaration) {
		h = (float) ((((float) this.hue.getValue()) / 255) * 2 * Math.PI);
		v = ((float) this.value.getValue()) / 255;
		s = ((float) this.satuaration.getValue()) / 255;
		
		this.hsvColor = new HsvColor(h, s, v);
		
		Color color = new Color(this.hsvColor.getAsRgb().red, this.hsvColor.getAsRgb().green, this.hsvColor.getAsRgb().blue);
		
		this.led.remote_setColor(this.hsvColor);
		this.colorSelector.setColor(color);
		this.saveColor(color);
	}
}


@Override
public void colorSelected(Color color)
{

	RgbColor rgbColor = new RgbColor(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);
	
	this.hsvColor = rgbColor.getAsHsv();
	
	this.led.remote_setColor(this.hsvColor);
	
	this.colorSelector.setColor(color);
    this.hue.getModel().setValue((int) (this.hsvColor.h * 255f / (2 * Math.PI)));
    this.satuaration.getModel().setValue((int) (this.hsvColor.s * 255));
    this.value.getModel().setValue((int) (this.hsvColor.v * 255));
	
	this.saveColor(color);
}


}
