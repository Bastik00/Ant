package de.hska.lat.robot.value.valueViewer;

public interface ValueGraphNotifier
{

	public void cursorMoved(int time, float value);
	
	public void onFocus();
	
	public void lostFocus();
}
