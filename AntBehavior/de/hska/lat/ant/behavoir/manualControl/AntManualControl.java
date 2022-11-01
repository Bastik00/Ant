package de.hska.lat.ant.behavoir.manualControl;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavior.motion.action.Move;
import de.hska.lat.ant.behavior.motion.action.Rotate;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.behavior.manualControl2D.ManualControl2D;


public class AntManualControl extends ManualControl2D
{

	
	protected static final int PERIOD 	= 100;
	
	protected ArbiterAction action;
	protected Move moveAction;
	
	public AntManualControl(Ant ant)
	{
		super(ant, AntBehavoirPriority.MANUAL_CONTROL_PRIORITY.getPriority(), 100);
	}
	
	
	protected void react()
	{
		
		System.out.println("Info: some controls has changed ! "+ this.velocity.getValue()+" - heading: "+	this.heading.getValue() + " ! - rotate: " + this.rotate.getValue());
		if ((this.velocity.getValue() !=0) & (this.heading.getValue()!=0))
		{
			if(action == null)
			{
				startMoveAction();
			}
			else
			{
				terminateCurrentAction();
				startMoveAction();
			}

		}
		else if((this.velocity.getValue() != 0) & (this.rotate.getValue() != 0))
		{
			if (action == null)
			{
				startRotateAction();
			}
			else
			{
				terminateCurrentAction();
				startRotateAction();
			}
		}
		else
		{
			if (action!=null)
			{
				terminateCurrentAction();
			}			
		} 
	}


	private void startRotateAction() {
		action = new Rotate(this.id);
		action.activate();
		((Rotate)action).setVelocity(this.velocity.getValue());
		((Rotate)action).setRotation(this.rotate.getValue());
		this.motionOutput.receive(action);
	}


	private void startMoveAction() {
		moveAction = new Move(this.id);
		moveAction.activate();
		moveAction.setVelocity(this.velocity.getValue());
		moveAction.setHeading(this.heading.getValue());
		action = moveAction;
		this.motionOutput.receive(action);
	}


	private void terminateCurrentAction() {
		this.motionOutput.clear(this.id);
		this.action.terminate();
		this.action = null;
	}	
	
}
