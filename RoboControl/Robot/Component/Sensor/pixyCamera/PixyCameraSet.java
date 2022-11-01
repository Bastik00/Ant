package de.hska.lat.robot.component.sensor.pixyCamera;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.sensor.pixyCamera.protocol.Stream_cameraObject;

/**
 * 
 * @author Jan R.
 *
 *         Class contains a list of pixyCameras
 *
 */

public class PixyCameraSet extends ComponentSet<PixyCamera, PixyCameraProtocol>

{
	private static final long serialVersionUID = -6296184626671050693L;

	public PixyCamera currentPixyCamera;

	public PixyCameraSet() {

	}

	public void processCameraObject(Stream_cameraObject cameraObject) {

		// TODO pixyCamera hardcoded to id=0 -> replace by list
		currentPixyCamera = this.getComponentOnLocalId(0);

		currentPixyCamera.signature = cameraObject.getSignature();
		currentPixyCamera.xCenter = cameraObject.getXcenter();
		currentPixyCamera.yCenter = cameraObject.getYcenter();
		currentPixyCamera.width = cameraObject.getWidth();
		currentPixyCamera.height = cameraObject.getHeight();
		currentPixyCamera.angle = cameraObject.getAngle();
		currentPixyCamera.frameSync = cameraObject.getFrameSync();
		currentPixyCamera.valueChanged(null);
	}

	@Override
	public boolean decodeStream(RemoteStream remoteData) {

		if (remoteData instanceof Stream_cameraObject) {
			processCameraObject((Stream_cameraObject) remoteData);
		}
		return false;
	}

	@Override
	public boolean decodeMessage(RemoteMessage remoteData) {
		// see ServoSet for example implementation
		throw new NullPointerException();
	}

}
