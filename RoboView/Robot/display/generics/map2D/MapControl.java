package de.hska.lat.robot.display.generics.map2D;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JPanel;

import de.hska.lat.robot.Robot;
import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;


public class MapControl extends JPanel//extends ElementView implements DroidTextSelectorListener, DroidButtonListener
{

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7777369846057837493L;


	
	

	protected MapInfo mapInfo;
	protected MapController mapController;

	
	//protected MapToolsSelector toolsSelector;
	  
	
	protected ArrayList <MapControlElement> functionViews= new ArrayList<MapControlElement>(); 
	

	
//	protected DisplayData displayData;
	
	protected MapControlListener controlListener;
	
	protected MapEventListener eventListener;
	
public MapControl(Robot robot,MapDisplayMetaData mapDisplayMetaData, MapEventListener eventListener)

{
	int yPosition = 10;
	
	Dimension dimension;
	
	this.setLayout(null);
	
	DisplayData displayData = mapDisplayMetaData.getDisplayData();
	this.setPreferredSize(new Dimension(250,800));
	
	
	this.mapInfo = new MapInfo(displayData);
	dimension = this.mapInfo.getPreferredSize();
	this.mapInfo.setBounds(10,yPosition,(int)dimension.getWidth(),(int)dimension.getHeight());
	this.add(this.mapInfo);
	
	yPosition+=(int)dimension.getHeight();
	

	yPosition+=(int)dimension.getHeight();
	
	this.mapController = new MapController(displayData);
	dimension = this.mapController.getPreferredSize();
	this.mapController.setBounds(10,yPosition,(int)dimension.getWidth(),(int)dimension.getHeight());
	this.add(this.mapController);
	
	yPosition+=(int)dimension.getHeight();
	

	
	
	
//	(new Dimension2D(200,300));
	
/*	super(context, true);

	this.makeFunctionsNames(context);
	this.eventListener = eventListener;
	
	
	displayData = mapDisplayMetaData.getDisplayData();
	
	functionSelector = new DroidTextSelector(context, functionNames);
	functionSelector.setBounds(5, 300, 270, 60);
	functionSelector.setListener(this);
	this.addView(functionSelector);
	
	
	mapInfo = new MapInfo(context,mapDisplayMetaData.getDisplayData());
	mapInfo.setPosition(5, 5);
	this.addView(mapInfo);
	

	poseInfo = new PoseInfo(context,robot.getPose());
	poseInfo.setPosition(5, 200);
	this.addView(poseInfo);
	*/
/*************** Buttons ************/
/*	
	buttons[ZOOM_OUT_MORE_BUTTON] = this.addButton(context, "++", 5,105,65,50, this);
	buttons[ZOOM_OUT_BUTTON] = this.addButton(context, "+", 70,105,65,50, this);
	
	buttons[ZOOM_IN_BUTTON] = this.addButton(context, "-", 135,105,65,50, this);
	buttons[ZOOM_IN_MORE_BUTTON] = this.addButton(context, "--", 200,105,65,50, this);

	
	
	buttons[HOME_BUTTON] = this.addButton(context, ">.<", 5,155,65,50, this);
*/

	
/*	
	centerButton= new DroidButton(context,"@");
	centerButton.setBounds(1150,165,70,50);
	centerButton.setListener(this);
	this.addView(centerButton);*/

/*	
	MapOriginControl originControl = new MapOriginControl(context, eventListener);
	originControl.setBounds(5, 350, 270, 450);
	originControl.addView(context,mapDisplayMetaData);
	functionViews.add(originControl);

	
	PoseControl poseControl = new PoseControl(context, eventListener);
	poseControl.setBounds(5, 350, 270, 450);
	poseControl.addView(context,mapDisplayMetaData.getPoseDrawer());
	functionViews.add(poseControl);
	
	
	
	
	DestinationControl destinationControl = new DestinationControl(context, eventListener);
	destinationControl.setBounds(5, 350, 270, 340);
	functionViews.add(destinationControl);
	destinationControl.addViews(context,mapDisplayMetaData);		

	
	
	NavigationPathControl pathControl = new NavigationPathControl(context, eventListener);
	functionViews.add(pathControl);
	
	pathControl.setBounds(5, 350, 270, 350);
	pathControl.addViews(context, mapDisplayMetaData);		
	
	
	*/
	
	
	/*********** Panorama *******/
	
	/*
	Panorama [] panoramas = robot.getPanoramas();
	
	PanoramaControl panoramaControl = new PanoramaControl(context, eventListener);
	
	functionViews.add(panoramaControl);
	
	panoramaControl.setBounds(5, 350, 270, 450);
	panoramaControl.addViews(context, panoramas);	
	*/
	
	/***********Field of View *******/
	
/*
	FieldOfViewControl fieldOfViewControl = new FieldOfViewControl(context, eventListener);
	
	functionViews.add(fieldOfViewControl);
	
	fieldOfViewControl.setBounds(5, 350, 270, 450);
	fieldOfViewControl.addViews(context,  mapDisplayMetaData.getFieldOfViewDrawers());	


	
	functionSelector.selectText(0);
	setControl(0);
*/
}

	

/*
public void setEventListener(MapEventListener eventlistener)
{
	this.eventlistener = eventlistener;
}
*/


protected void setControlListener(MapControlListener controlListener)
{
	this.controlListener = controlListener;
}


protected void makeFunctionsNames()
{
/*	 functionNames = new String [6];
	
	 functionNames[0]="origin";
	 functionNames[1]="pose";
	 functionNames[2]=DroidStrings.getString(context,DroidStrings.DESTINATION_TEXT);
	 functionNames[3]=DroidStrings.getString(context,DroidStrings.PATH_TEXT);
	 functionNames[4]=DroidStrings.getString(context,DroidStrings.PANORAMA_TEXT);
	 functionNames[5]=DroidStrings.getString(context,DroidStrings.FIELD_OF_VIEW_TEXT);
	*/
}


protected void setControl(int index)
{
/*	int enumerator;
	
	for (enumerator = 0; enumerator<this.functionViews.size();enumerator++ )
	{
		this.removeView(this.functionViews.get(enumerator));
	}
	
	if (index<this.functionViews.size())
	{
		this.addView(this.functionViews.get(index));	
		
		if (this.eventListener!=null)
			this.eventListener.mapEditModeChanged(this.functionViews.get(index).getControlMode());
	}

	*/
}









/*
@Override
public void clicked(DroidButton source) 
{
	boolean dirty;
	
	dirty=false;
	
	if (source==buttons[ZOOM_IN_BUTTON])
	{
		this.displayData.zoomIn();
		dirty=true;
	}
	else if (source==buttons[ZOOM_IN_MORE_BUTTON])
	{
		this.displayData.zoomInMore();
		dirty=true;
	}
	else if(source==buttons[ZOOM_OUT_BUTTON])
	{
		this.displayData.zoomOut();
		dirty=true;
	}
	else if(source==buttons[ZOOM_OUT_MORE_BUTTON])
	{
		this.displayData.zoomOutMore();
		dirty=true;
	}
/*	
	else if (source==centerButton)
	{
		this.displayData.setDisplayOffset(0, 0);	
		dirty=true;
	}

	else if (source==moveSwitch)
	{
		areaView.setInputModeMove();
	//	destinationSwitch.off();
	//	pathSwitch.off();
	}

	*

	
	/*
	
	if (dirty==true)
	{
		this.postInvalidate();
	}

}
*/




	
	
	
	
	
	
	
}
