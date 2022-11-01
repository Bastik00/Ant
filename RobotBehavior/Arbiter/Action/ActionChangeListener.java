package de.hska.lat.behavior.arbiter.action;

public interface ActionChangeListener
{
	public void onActionInitialized(ArbiterAction action);
	public void onActionActivated(ArbiterAction action);
	public void onActionReady(ArbiterAction action);
	public void onActionCompleted(ArbiterAction action);
	public void onActionInterrupted(ArbiterAction action);
	public void onActionSuspended(ArbiterAction action);
	public void onActionFailed(ArbiterAction action);
	public void onActionTerminated(ArbiterAction action);
	
}
