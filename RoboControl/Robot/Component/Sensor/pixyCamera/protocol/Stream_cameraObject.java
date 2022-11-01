package de.hska.lat.robot.component.sensor.pixyCamera.protocol;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;

public class Stream_cameraObject extends RemoteStream {
	private static final long serialVersionUID = -4829283160534045422L;

	protected static final String name = "cameraObject";
	protected static final String description = "camera object block-data";

	public static enum parameterIndex {
		SIGNATURE, X_CENTER, Y_CENTER, WIDTH, HEIGHT, ANGLE
	};

	public Stream_cameraObject(int command) {
		this();
		this.setId(command);
	}

	public Stream_cameraObject() {
		this.add(new RemoteParameterUint16("signature", "describes type of detected object"));
		this.add(new RemoteParameterUint16("x_center", "distance in pixels (left border -> obj. horizontal center)"));
		this.add(new RemoteParameterUint16("y_center", "distance in pixels (top border -> obj. vertical center)"));
		this.add(new RemoteParameterUint16("width", "width of object in pixels"));
		this.add(new RemoteParameterUint16("height", "height of object in pixels"));
		this.add(new RemoteParameterInt16("angle", "angle of object in degree"));
	}

	public String getName() {
		return (Stream_cameraObject.name);
	}

	public String getDescription() {
		return (Stream_cameraObject.description);
	}

	public int getSignature() {
		return (((RemoteParameterUint16) this.get(parameterIndex.SIGNATURE.ordinal())).getValue() & 0x7FFF);
	};

	public int getXcenter() {
		return ((RemoteParameterUint16) this.get(parameterIndex.X_CENTER.ordinal())).getValue();
	};

	public int getYcenter() {
		return ((RemoteParameterUint16) this.get(parameterIndex.Y_CENTER.ordinal())).getValue();
	};

	public int getWidth() {
		return ((RemoteParameterUint16) this.get(parameterIndex.WIDTH.ordinal())).getValue();
	};

	public int getHeight() {
		return ((RemoteParameterUint16) this.get(parameterIndex.HEIGHT.ordinal())).getValue();
	};
	
	public int getAngle() {
		
		int angle = ((RemoteParameterInt16) this.get(parameterIndex.ANGLE.ordinal())).getValue();
		
		if(angle > 0x7FFF) //Negative signed 16Bit value? 
		{
			angle -= 65536;
		}
		
		return angle;
	};
	
	/**
	 * <b>FrameSync to determine whether a new frame starts with the current cameraObject.</b><br>
	 * A camera object can be assigned to frame 0 or 1.<br>
	 * Example:<br>
	 * Frame1: ObjectA/FrameSync=0, ObjectB/FrameSync=0, ObjectC/FrameSync=0<br>
	 * Frame2: ObjectA/FrameSync=1, ObjectB/FrameSync=1, ObjectC/FrameSync=1<br>
	 * Frame3: ObjectA/FrameSync=0, ObjectB/FrameSync=0, ObjectC/FrameSync=0<br>
	 * ...<br>
	 * The information is stored in the most significant bit of the uint16 var "signature"
	 * 
	 * @return the frame assignment of the current frame. 0 or 1
	 */
	public boolean getFrameSync() {
		int tempSignature = ((RemoteParameterUint16) this.get(parameterIndex.SIGNATURE.ordinal())).getValue();
		tempSignature &= 0x8000;
		return (tempSignature > 0);
	};

	public void setData(int signature, int x_center, int y_center, int width, int height, int angle) {
		((RemoteParameterUint16) this.get(parameterIndex.SIGNATURE.ordinal())).setValue(signature);
		((RemoteParameterUint16) this.get(parameterIndex.X_CENTER.ordinal())).setValue(x_center);
		((RemoteParameterUint16) this.get(parameterIndex.Y_CENTER.ordinal())).setValue(y_center);
		((RemoteParameterUint16) this.get(parameterIndex.WIDTH.ordinal())).setValue(width);
		((RemoteParameterUint16) this.get(parameterIndex.HEIGHT.ordinal())).setValue(height);
		((RemoteParameterInt16) this.get(parameterIndex.ANGLE.ordinal())).setValue(angle);
	}

	@Override
	public void parseDataPacketData(RemoteDataPacket packet) {

		int angle = 2 * parameterIndex.ANGLE.ordinal();
		
		if(angle > 0x7FFF) //Negative signed 16Bit value? 
		{
			angle -= 65536;
		}
		
		setData(
				packet.getInt16(2 * parameterIndex.SIGNATURE.ordinal()),
				packet.getInt16(2 * parameterIndex.X_CENTER.ordinal()),
				packet.getInt16(2 * parameterIndex.Y_CENTER.ordinal()),
				packet.getInt16(2 * parameterIndex.WIDTH.ordinal()),
				packet.getInt16(2 * parameterIndex.HEIGHT.ordinal()),
				packet.getInt16(angle));
	}

}
