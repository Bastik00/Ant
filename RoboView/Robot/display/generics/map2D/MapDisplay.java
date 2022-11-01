package de.hska.lat.robot.display.generics.map2D;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JToolBar;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.navigation.Destination;
import de.hska.lat.navigation.path.NavigationPath;
import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.robot.Robot;
import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.component.generic.navigator.Navigator;
import de.hska.lat.robot.display.generics.map.navigationPath.NavigationPathManager;
import de.hska.lat.robot.display.generics.map.navigationPath.NavigationPathSelector;
import de.hska.lat.robot.display.generics.map2D.MapControlElement.MapControlMode_e;
import de.hska.lat.robot.display.generics.map2D.displayData.DisplayData;
import de.hska.lat.robot.display.generics.map2D.metaData.MapDisplayMetaData;
import de.hska.lat.robot.display.generics.map2D.origin.OriginManager;
import de.hska.lat.robot.display.generics.map2D.pose.PoseManager;
import de.hska.lat.robot.displayFrame.DisplayFrame;
import de.hska.lat.robot.pose.Pose3D;





public abstract class MapDisplay extends DisplayFrame implements MapEventListener, ComponentListener, ActionListener
{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7684970099829132662L;




	protected DisplayData displayData = new DisplayData();

	
	protected Destination destination;
	
//	protected NavigationPath navigationPath = new NavigationPath();

	protected NavigationPath activePath;
	
	protected ArrayList<NavigationPath> paths = new ArrayList<NavigationPath>();
	
	protected MapControlMode_e editMode;
	
	
	
	protected Navigator<?> navigator; 
	
	protected Pose3D pose;
	protected boolean toGrid;
	
	protected MapControl mapControl;
	protected MapView mapView;
	
	protected AbstractRobot<?,?,?> robot;
	
	
	
	
	/************** new **************/

	protected static final String cmdPoseManager = "cmdPoseManager";
	protected static final String cmdOriginManager = "cmdOriginManager";
	protected static final String cmdPathManager = "cmdPathManager";
	protected static final String cmdExecutePath = "cmdExecutePath";

	protected static final String CMD_PATH_SELECTED = "cmdPathSelected";

	protected NavigationPathManager pathManager = null;

	protected OriginManager originManager = null;
	protected PoseManager poseManager = null;

	protected NavigationPathSelector pathSelector; 
	
	JToolBar toolBar;
	
	
public MapDisplay(String name) 
{
		super(name,false,true,false,false);
		
		this.setResizable(true);
		this.setLayout(new BorderLayout());


		this.buildToolBar();


		this.show();
}


private static final String POSE_TEXT = "pose";
private static final String ORIGIN_TEXT = "origin";


private static final String PATH_TEXT = "path";
private static final String EXECUTE_PATH_TEXT = "execute path";

private static final String FOV_TEXT = "fields of view";







private void buildToolBar()
{

	toolBar = new JToolBar();
	
	toolBar.setFloatable(false);
	
	setLayout(new BorderLayout());
	
	JButton tmpButton;
	
	
	tmpButton = new JButton(MapDisplay.ORIGIN_TEXT);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(MapDisplay.cmdOriginManager);
	toolBar.add(tmpButton);
	
	
	tmpButton = new JButton(MapDisplay.POSE_TEXT);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(MapDisplay.cmdPoseManager);
	toolBar.add(tmpButton);
	
	
	tmpButton = new JButton(MapDisplay.PATH_TEXT);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(MapDisplay.cmdPathManager);
	toolBar.add(tmpButton);
	

	this.pathSelector = new NavigationPathSelector();
	this.pathSelector.setPathList(this.paths);
	this.pathSelector.setPreferredSize(new Dimension (100,30)); 
	this.pathSelector.setBounds(10,30,150,30);
	this.pathSelector.addActionListener(this);
	this.pathSelector.setActionCommand(MapDisplay.CMD_PATH_SELECTED);
	
	this.toolBar.add(this.pathSelector); 



	tmpButton = new JButton(MapDisplay.EXECUTE_PATH_TEXT);
	tmpButton.addActionListener(this);
	tmpButton.setActionCommand(MapDisplay.cmdExecutePath);
	toolBar.add(tmpButton);


	tmpButton = new JButton(MapDisplay.FOV_TEXT);

	toolBar.add(tmpButton);

	
	this.add(toolBar,BorderLayout.PAGE_START);

}



public void setMetaData(MapDisplayMetaData metaData)
{
	this.displayData=metaData.getDisplayData();
	this.destination = metaData.getDestination();
	//this.navigationPath = metaData.getNavigationPath();
	this.navigator = metaData.getNavigator();
	this.pose = ((Robot)robot).getPose();
	
	

	
	
	
	this.addComponentListener(this);
	
	this.mapView = new MapView( ((Robot)robot),metaData);
	//this.mapView.setBounds(0, 30, 1000, 700);
	this.mapView.setPreferredSize(new Dimension(1000, 700));
	this.add(this.mapView,BorderLayout.CENTER);

	mapView.setEventListener(this);
	
	displayData.setDisplayDimension(1000, 700);
	
	
	this.mapControl = new MapControl( ((Robot)robot),metaData,null);
//	this.mapControl.setBounds(1000, 0, 230, 700);
	this.add(this.mapControl,BorderLayout.EAST);
	mapView.setEventListener(this);
	
	
	
	
	
	this.originManager = new OriginManager(this.mapView.getOriginDrawer());
	this.poseManager = new PoseManager(this.mapView.getPoseDrawer());
	
	

	this.setBounds(30,30,1280,800);
}
/*
protected void dispatchDraw(Canvas canvas) 
{
	super.dispatchDraw(canvas); 
}
*/





private void setDestination(float xPos, float zPos)
{
	
//	this.destination.setDestination(xPos, zPos);
//	this.destination.setOrigin(this.pose.getXPosition(), this.pose.getZPosition());

}




private void navpoint(float xPos, float yPos, float zPos)
{
	NavigationPoint navPoint;
	float radius;
	
	if (activePath==null)
		return;
	
	radius = this.displayData.getScale();
	
	navPoint=this.activePath.getNavpointAt(xPos, yPos, zPos, radius);
	
	this.activePath.unselectAll();
	if (navPoint!=null)
	{
		this.activePath.select(navPoint);
	}
	else
	{
		navPoint=this.activePath.addNavPoint(new FloatVector3D(xPos, 0,zPos));
		this.activePath.select(navPoint);
	}
}



@Override
public void mapClicked(int xPosition, int yPosition)
{
	float x;
	float y;
	
	x=displayData.translateXFormScreen(xPosition);
	y=displayData.translateYFormScreen(yPosition);

	
	this.editMode = MapControlMode_e.MODE_PATH;
	
	switch (this.editMode)
	{
	
	case MODE_DESTINATION:
		if (this.toGrid)
		{
			x = this.displayData.toScale (x);
			y = this.displayData.toScale (y);
		}	
			
		this.setDestination(x,y);
		break;
	
	case MODE_PATH:
		
		if (this.toGrid)
		{
			x = this.displayData.toScale (x);
			y = this.displayData.toScale (y);
		}	
		
		this.navpoint(x,0,y);
		break;
		
	case MODE_STANDARD:
		break;
	default:
		break;
	}

}
/*
@Override
public boolean mapEvent(MotionEvent event) 
{
	float x;
	float y;
	

	
	switch (event.getAction())
	{
	case MotionEvent.ACTION_DOWN:
		x=displayData.translateXFormScreen(event.getX());
		y=displayData.translateYFormScreen(event.getY());
		
		// Destination -> select??
		// NavPoint ?? -> select
		return(true);
		
		
	case MotionEvent.ACTION_UP:
	
		x=displayData.translateXFormScreen(event.getX());
		y=displayData.translateYFormScreen(event.getY());
	
		switch (this.editMode)
		{
		
		case MODE_DESTINATION:
			if (this.toGrid)
			{
				x = this.displayData.toScale (x);
				y = this.displayData.toScale (y);
			}	
				
			this.setDestination(x,y);
			break;
		
		case MODE_PATH:
			
			if (this.toGrid)
			{
				x = this.displayData.toScale (x);
				y = this.displayData.toScale (y);
			}	
			
			this.navpoint(x,y);
			break;
			
		case MODE_STANDARD:
			break;
		default:
			break;
		}
	
		return(true);
	
	
	}
	
	return(false);
}

*/



@Override
public void mapEditModeChanged(MapControlMode_e mode)
{
	this.editMode = mode;
	
}





@Override
public boolean moveToDestination() 
{
//	this.destination.setOrigin(this.pose.getXPosition(), this.pose.getZPosition());
//	navigator.moveTo(this.destination.getXDestination(), this.destination.getYDestination(), this.destination.getSpeed());
	
	return(true);
}





@Override
public void toGrid(boolean status)
{
	this.toGrid = status;
	
}





@Override
public void moveToNavPoint(NavigationPoint navPoint) 
{
	this.navigator.moveTo(navPoint.getXPosition(),navPoint.getZPosition(), navPoint.getVelocity());
	
}





@Override
public void executePath(int index) 
{
/*	// Transfer path
	// select active
	// execute
	
	this.navigator.executePath(this.navigationPath, index);
	
	
	
	
	
	NavigationPoint navPoint;
	
	int enumerator;
	
	this.navigator.clearNavPointList();
	
	for (enumerator=0;enumerator<this.navigationPath.size() ;enumerator++)
	{
		navPoint=this.navigationPath.getNavpoint(enumerator);
		this.navigator.addNavPoint(navPoint.getXPosition(), navPoint.getZPosition(), navPoint.getSpeed());
	}
	
	this.navigator.selectNavPoint((byte)index);
	this.navigator.executePath();
	
*/	
}



public void componentResized(ComponentEvent e) 
{
	
	
   System.out.println(e.getComponent().getClass().getName() + " --- Resized ");            
   
   this.toolBar.setPreferredSize(new Dimension(this.getWidth()-10,30));
   
   /* Dimension mapMinDimensions;
    Dimension mapControlDimension;
    
    this.getPreferredSize();
    mapControlDimension=this.mapControl.getPreferredSize();
    
    mapControlDimension.getHeight();
    mapControlDimension.getWidth();
    
    this.mapControl.setLocation((int) (this.getWidth()-mapControlDimension.getWidth()) ,0);
    this.mapView.setBounds(0,0,(int) (this.getWidth()-mapControlDimension.getWidth()),this.getHeight());
    
    this.displayData.setDisplayDimension((float) (this.getWidth()-mapControlDimension.getWidth()),this.getHeight());
	
 */   
}


@Override
public void componentHidden(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void componentMoved(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void componentShown(ComponentEvent e) 
{
	// TODO Auto-generated method stub

}


/**
 * close all open Manager frames
 */
@Override
protected void onClosing()
{
	super.onClosing();

	if (this.pathManager!=null)
	{
		if (this.pathManager.getDesktopPane()!=null)
		{
			this.pathManager.dispose();
		}
	}
	
	
}


@Override
public void actionPerformed(ActionEvent actionEvent)
{
	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(MapDisplay.cmdPathManager))
	{
		if (this.pathManager==null)
		{
			this.pathManager = new NavigationPathManager();
			this.getDesktopPane().add(this.pathManager); 
			this.pathManager.setPathList(this.paths);
		}
		else
		{
			if (this.pathManager.getDesktopPane()==null)
				this.getDesktopPane().add(this.pathManager);
		}
		this.pathManager.show();
		this.pathManager.toFront();
	}
	
	
	
	if (cmd.equals(MapDisplay.cmdOriginManager))
	{
		if (this.originManager.getDesktopPane()==null)
				this.getDesktopPane().add(this.originManager);

		this.originManager.show();
		this.originManager.toFront();
	}
		
	

	if (cmd.equals(MapDisplay.cmdPoseManager))
	{
		if (this.poseManager.getDesktopPane()==null)
			this.getDesktopPane().add(this.poseManager);

		this.poseManager.show();
		this.poseManager.toFront();
	}
		

	
	else if (cmd.equals(MapDisplay.CMD_PATH_SELECTED))
	{
		this.activePath = (NavigationPath)this.pathSelector.getSelectedItem();
		this.mapView.setPath(this.activePath);
		
	}
	
	if (cmd.equals(MapDisplay.cmdExecutePath)){
		NavigationPath path = this.activePath;
		Navigator<?> navigator = this.getNavigator();
		navigator.executePath(path, 0);
	}
}

abstract protected Navigator<?> getNavigator();
}