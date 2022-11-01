package de.hska.lat.robot.component.generic.distance;

import de.hska.lat.robot.component.ComponentChangeNotifier;


public interface DistanceChangeNotifier extends ComponentChangeNotifier{

	
	/**
	 * notify when distance determinated by a detector changed   
	 * @param index index of detector
	 * @param distance new distance
	 */
	//public void distanceChanged(int index,int distance);
	public void distanceChanged(DistanceSensor<?,?> sensor);		

	
}
