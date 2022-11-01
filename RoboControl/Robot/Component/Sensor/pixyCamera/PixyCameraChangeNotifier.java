package de.hska.lat.robot.component.sensor.pixyCamera;

import de.hska.lat.robot.component.ComponentChangeNotifier;


public interface PixyCameraChangeNotifier extends ComponentChangeNotifier {

	public void cameraObjectDetected(PixyCamera camera);
	
}
