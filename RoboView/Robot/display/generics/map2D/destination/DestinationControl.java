package de.hska.lat.robot.display.generics.map2D.destination;



import de.hska.lat.robot.display.generics.map2D.MapControlElement;
import de.hska.lat.robot.display.generics.map2D.MapEventListener;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;





public class DestinationControl extends MapControlElement // implements DroidButtonListener, DroidSwitchListener, DroidSliderListener
{


	  
	 
/**
	 * 
	 */
	private static final long serialVersionUID = -3709883516185943093L;


public DestinationControl( MapEventListener listener)
{
	super( false, MapControlMode_e.MODE_DESTINATION, listener);
	
	
	
}


public void addViews(MapDisplayMetaData mapDisplayMetaData)
		
{
	
	/*his.colorSelector = new ColorSelector(context,ColorSelector.BASIC_COLOR_LIST);
	this.colorSelector.setBounds(60, 10, 50, 50);
	this.colorSelector.setListener(mapDisplayMetaData.getDestinationDrawer());
	this.colorSelector.setNearestColor(mapDisplayMetaData.getDestinationDrawer().getDisplayColor());
	this.addView(this.colorSelector);

	this.destination = mapDisplayMetaData.getDestination();
	
	DestinationInfoView destinationInfoView = new DestinationInfoView(context,this.destination); 
	destinationInfoView.setPosition(0, 60);
	this.addView(destinationInfoView);

	this.toGrid = new DroidSwitch(context,"#");
	this.toGrid.setBounds(10, 10, 50, 50);	
	this.toGrid.setListener(this);
	this.toGrid.on();
	this.addView(this.toGrid);
	
	this.speedText = new DroidTextField(context, "0%");
	this.speedText.setBounds(10, this.getHeight()-140, 80, 60);
	this.addView(this.speedText);
	
	this.speedControl = new AmpSlider(context, 100, true);
	this.speedControl.setBounds(10, this.getHeight()-130, this.getWidth()-20, 60);
	this.speedControl.setListener(this);
	this.addView(this.speedControl);
	
	
	this.gotoButton = new DroidButton(context,"goto");
	this.gotoButton.setBounds(10, this.getHeight()-60, this.getWidth()-20, 50);
	this.gotoButton.setListener(this);
	this.addView(this.gotoButton);
	
	*/
}

/*
@Override
public void clicked(DroidButton source) 
{
	if (source==this.gotoButton)
	{
		this.eventListener.moveToDestination();
	}

	
}


@Override
public void longClicked(DroidButton source) {
	// TODO Auto-generated method stub
	
}


@Override
public void droidSwitchStatusChanged(DroidSwitch source) 
{
	if (source==this.toGrid)
	{
		this.eventListener.toGrid(this.toGrid.isOn());
	}
	
}



@Override
public void valueChanged(DroidSlider<?> source, int value) 
{

	
}



@Override
public void moved(DroidSlider<?> source, int value) 
{
	  
	if (source == speedControl)
	{
		this.destination.setSpeed(0.01f * value);
		this.speedText.setText(value+"%");
	}
}
*/






}
