package de.hska.lat.ant.behavior.motion;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.behavior.arbiter.ArbiterGroup;


public class AntMotionBehavior extends ArbiterGroup
{
	protected AntMotionSequencer motionSequencer;
	protected AntMotionControl motionControl;
	protected AntEdgeDetector edgeDetector;
	
public AntMotionBehavior()
{
	super(AntBehavoirPriority.MOTION_CONTROL_PRIORITY.getPriority());
}



private void init(Ant ant)
{
	this.motionSequencer = new AntMotionSequencer(ant);
	this.addArbiter(this.motionSequencer);
	
	
	this.motionControl = new AntMotionControl(ant);
	this.addArbiter(this.motionControl);
	
	this.edgeDetector = new AntEdgeDetector(ant);
	
	this.motionControl.setOutput(this.motionSequencer.getInput());

}
	
}
