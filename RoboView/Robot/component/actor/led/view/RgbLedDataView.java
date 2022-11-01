package de.hska.lat.robot.component.actor.led.view;




import de.hska.lat.robot.component.ComponentChangeNotifier;
import de.hska.lat.robot.component.actor.led.RgbLed;
import de.hska.lat.robot.component.actor.view.ActorDataView;
import de.hska.lat.robot.component.view.ComponentView;
import de.hska.lat.robot.component.view.MissingComponentView;


public class RgbLedDataView  extends ActorDataView<RgbLed> implements ComponentChangeNotifier
{

	
	protected static final int width = 160;
	protected static final int height = 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6397689382576347950L;

	
	
	
	
public RgbLedDataView(RgbLed led)
{
	super(led);
	

	this.buildView();
}

@Override
protected int getViewWidth()
{
	return(RgbLedDataView.width);
}

@Override
protected int getViewHeight()
{
	return(RgbLedDataView.height);
}




	
public void buildView()
{
	super.buildView();
	//ValueView<?> view;
	
	
	//Insets insets = this.getBorder().getBorderInsets(this);
	
	
	
//	view= LuxValueView.createView(this.component.getLuxValue(), true );
	//view.setLocation(10,insets.top+5);
	
	
	//this.add(view);

}
	
	



public static ComponentView createView(RgbLed sensor)
{

	if (sensor!=null)
	{
		return(new RgbLedDataView(sensor));
	}
	else
	{
		return(new MissingComponentView(RgbLed.class.getName()));
	}
}



}
