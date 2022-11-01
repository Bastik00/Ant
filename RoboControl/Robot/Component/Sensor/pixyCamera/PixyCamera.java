package de.hska.lat.robot.component.sensor.pixyCamera;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.sensor.pixyCamera.PixyCameraProtocol;
import de.hska.lat.robot.value.ComponentValue;
import de.hska.lat.robot.value.ComponentValueChangeListener;


public class PixyCamera extends RobotComponent<PixyCameraChangeNotifier, ComponentSettingsChangeNotifier, PixyCameraProtocol> implements ComponentValueChangeListener {

	public int signature;
	public int xCenter;
	public int yCenter;
	public int width;
	public int height;
	public int angle;
	
	public boolean frameSync;
	public PixyCamera(ComponentMetaData metaData, PixyCameraProtocol protocol) {
		super(metaData, protocol);
	}

	@Override
	public void valueChanged(ComponentValue<?> componentValue) {
		for (PixyCameraChangeNotifier listener : this.sensorListener)
		{
			listener.cameraObjectDetected(this);
		}
	}

}
