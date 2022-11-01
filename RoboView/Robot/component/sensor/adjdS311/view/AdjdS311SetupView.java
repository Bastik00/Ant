package de.hska.lat.robot.component.sensor.adjdS311.view;



import java.awt.Insets;




import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.sensor.adjdS311.AdjdS311;
import de.hska.lat.robot.component.sensor.adjdS311.AdjdS311Setings;
import de.hska.lat.robot.component.view.ComponentSettingsView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;



public class AdjdS311SetupView extends ComponentSettingsView<AdjdS311> implements ComponentSettingsChangeNotifier

{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6199812393064386683L;
	
	protected static final int width = 360;
	protected static final int height = 250;
	
	
	protected AdjdS311ChanelSettingsView redChannel;
	protected AdjdS311ChanelSettingsView greenChannel;
	protected AdjdS311ChanelSettingsView blueChannel;
	protected AdjdS311ChanelSettingsView clearChannel;
	

	
public AdjdS311SetupView(AdjdS311 sensor)
{
	super(sensor);
	
	this.component.addSetupListener(this);
	
	this.buildView();
	this.update();
}


@Override
protected void buildView()
{
	
	super.buildView();


	Insets insets = this.getBorder().getBorderInsets(this);

	this.redChannel = new AdjdS311ChanelSettingsView("red");
	this.redChannel.setLocation(insets.left+5,insets.top+5);
	this.add(this.redChannel);

	
	this.greenChannel = new AdjdS311ChanelSettingsView("green");
	this.greenChannel.setLocation(insets.left+180,insets.top+5);
	this.add(this.greenChannel);
	
	
	
	this.blueChannel = new AdjdS311ChanelSettingsView("blue");
	this.blueChannel.setLocation(insets.left+5,insets.top+110);
	this.add(this.blueChannel);

	
	this.clearChannel = new AdjdS311ChanelSettingsView("clear");
	this.clearChannel.setLocation(insets.left+180,insets.top+110);
	this.add(this.clearChannel);
	

	
	this.addSetButton(insets.left+5, insets.top+220, 50, 22);
	this.addGetButton(insets.left+60, insets.top+220, 50, 22);
	
	this.addSaveButton(insets.left+115, insets.top+220, 50, 22);
	this.addLoadButton(insets.left+170, insets.top+220, 50, 22);
	

	
}

@Override
protected int getViewWidth()
{
	return(AdjdS311SetupView.width);
}

@Override
protected int getViewHeight()
{
	return(AdjdS311SetupView.height);
}



protected void update()
{

	//this.redChannel.setSettings(this.component.getSettings());
	

}


@Override
protected boolean setSettings()
{
	AdjdS311Setings settings = new AdjdS311Setings();
	
	settings.redChannel = this.redChannel.getSettings();
	settings.greenChannel = this.greenChannel.getSettings();
	settings.blueChannel = this.blueChannel.getSettings();
	settings.clearChannel = this.clearChannel.getSettings();
		
	return(this.component.remote_setSettings(settings));
	
}




public static ComponentView createView(AdjdS311 sensor)
{

	if (sensor!=null)
	{
		return(new AdjdS311SetupView(sensor));
	}
	else
	{
		return(new MissingComponentView(AdjdS311.class.getName()));
	}
}



@Override
public void settingsChanged(RobotComponent<?, ?, ?> component)
{
	this.update();
}




}
