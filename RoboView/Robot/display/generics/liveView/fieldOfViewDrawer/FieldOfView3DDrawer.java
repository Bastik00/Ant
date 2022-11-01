package de.hska.lat.robot.display.generics.liveView.fieldOfViewDrawer;

import de.hska.lat.perception.fieldOfView.FieldOfView;
import de.hska.lat.robot.display.generics.liveView.drawer.Drawer3D;

public class FieldOfView3DDrawer<T extends FieldOfView<?>> extends Drawer3D
{

	protected T fieldOfView; 
	
public FieldOfView3DDrawer(T fieldOfView)
{
	this.fieldOfView = fieldOfView;
}
	




}
