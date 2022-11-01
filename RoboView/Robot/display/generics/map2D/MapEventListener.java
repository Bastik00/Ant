package de.hska.lat.robot.display.generics.map2D;


import de.hska.lat.navigation.path.NavigationPoint;
import de.hska.lat.robot.display.generics.map2D.MapControlElement.MapControlMode_e;


public interface MapEventListener
{

//	public boolean mapEvent(MotionEvent event);
	
	public void toGrid(boolean status);
	public void mapEditModeChanged(MapControlMode_e mode);
	
	public boolean moveToDestination();

	public void moveToNavPoint(NavigationPoint navPoint);

	public void executePath(int index);
	
	
	public void mapClicked(int xPosition, int yPosition);
	
}
