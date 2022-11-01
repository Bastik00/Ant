package de.hska.lat.robot.component.sensor.pixyCamera.view;

public class DetectedObject {

	private int signature;
	private int xCenter;
	private int yCenter;
	private int width;
	private int height;
	private int angle;
	
	public DetectedObject(int signature, int xCenter, int yCenter, int width, int height, int angle)
	{
		this.signature = signature;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}
	
	public int getSignature() {
		return signature;
	}
	public void setSignature(int signature) {
		this.signature = signature;
	}
	public int getxCenter() {
		return xCenter;
	}
	public void setxCenter(int xCenter) {
		this.xCenter = xCenter;
	}
	public int getyCenter() {
		return yCenter;
	}
	public void setyCenter(int yCenter) {
		this.yCenter = yCenter;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
}
