package de.hska.lat.robot.component.generic.collision;

import de.hska.lat.math.vector.FloatVector3D;
import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface CollisionChangeNotifier extends ComponentChangeNotifier
{
	/**
	 * Notify listener that a collision has been detected. 
	 * @param vector
	 */
	public void collisionDetected(FloatVector3D vector);
	
}
