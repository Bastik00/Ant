package de.hska.lat.ant.behavior.behavoir;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.arbiter.head.AntHeadArbiter;
import de.hska.lat.ant.arbiter.led.leg.AntLegBackLeftLedArbiter;
import de.hska.lat.ant.arbiter.led.leg.AntLegBackRightLedArbiter;
import de.hska.lat.ant.arbiter.led.leg.AntLegCenterLeftLedArbiter;
import de.hska.lat.ant.arbiter.led.leg.AntLegCenterRightLedArbiter;
import de.hska.lat.ant.arbiter.led.leg.AntLegFrontLeftLedArbiter;
import de.hska.lat.ant.arbiter.led.leg.AntLegFrontRightLedArbiter;
import de.hska.lat.ant.arbiter.motion.CenterServoArbiter;
import de.hska.lat.ant.arbiter.motion.LeftServoArbiter;
import de.hska.lat.ant.arbiter.motion.RightServoArbiter;
import de.hska.lat.ant.behavior.head.light.AntHeadLightBehavior;
import de.hska.lat.behavior.behavior.RobotBehavior;


public class AntBehavior extends RobotBehavior<Ant>
{

public AntBehavior(Ant robot)
{
	super(robot);
	this.build();
}


public void build()
{	
	// create Leg Led Arbiter
	
	AntLegBackLeftLedArbiter legBackLeftLedArbiter = new AntLegBackLeftLedArbiter(this.robot);
	this.addArbiter(legBackLeftLedArbiter);
	AntLegCenterLeftLedArbiter legCenterLeftLedArbiter = new AntLegCenterLeftLedArbiter(this.robot);
	this.addArbiter(legCenterLeftLedArbiter);
	AntLegFrontLeftLedArbiter legFrontLeftLedArbiter = new AntLegFrontLeftLedArbiter(this.robot);
	this.addArbiter(legFrontLeftLedArbiter);
	

	AntLegBackRightLedArbiter legBackRightLedArbiter = new AntLegBackRightLedArbiter(this.robot);
	this.addArbiter(legBackRightLedArbiter);
	AntLegCenterRightLedArbiter legCenterRightLedArbiter = new AntLegCenterRightLedArbiter(this.robot);
	this.addArbiter(legCenterRightLedArbiter);
	AntLegFrontRightLedArbiter legFrontRightLedArbiter = new AntLegFrontRightLedArbiter(this.robot);
	this.addArbiter(legFrontRightLedArbiter);
	
	// create behaviors
	
	AntHeadArbiter headServoArbiter = new AntHeadArbiter(this.robot);
	this.addArbiter(headServoArbiter);
	
	LeftServoArbiter leftServoArbiter = new LeftServoArbiter(this.robot);
	this.addArbiter(leftServoArbiter);
	
	CenterServoArbiter centerServoArbiter = new CenterServoArbiter(this.robot);
	this.addArbiter(centerServoArbiter);
	
	RightServoArbiter rightServoArbiter = new RightServoArbiter(this.robot);
	this.addArbiter(rightServoArbiter);
	
/*	
	AntMotionSequencer motionSequence = new AntMotionSequencer(this.robot);
	this.addArbiter(motionSequence);

*/
	
/*
	AntObjectAvoidance obstacleAvoidance = new AntObjectAvoidance(this.robot);
	this.addArbiter(obstacleAvoidance);
	
	AntMotionControl motionBehavior = new AntMotionControl(this.robot);
	this.addArbiter(motionBehavior);

	InhibitorNode obstacleInhibitor = new InhibitorNode(0,"obstacle inhibitor");
	this.addArbiter(obstacleInhibitor);
	
	AntManualControl manualControl = new AntManualControl(this.robot);
	this.addArbiter(manualControl);
			
	
	AntHeadBlinkLightFollower headBlinkLightFollower = new AntHeadBlinkLightFollower(this.robot);
	this.addArbiter(headBlinkLightFollower);
*/
	
	/**** new *******/
	
	
	AntHeadLightBehavior headLightBehavior = new AntHeadLightBehavior(this.robot);
	this.addArbiter(headLightBehavior);
	
	
		
	/********* old ******/
	// map behaviors
	/*
	motionSequence.setOutput(leftServoArbiter.getInput(),
					centerServoArbiter.getInput(),
					rightServoArbiter.getInput());

	/*
	lightNode.connectOutput(headServoArbiter.getInput());
	lightAvoidance.setOutput(lightNode.getActionInput());
	lightFollower.setHeadOutput(lightNode.getSuppressorInput());
*/
	//lightAvoidance.setOutput(headServoArbiter.getInput());

/*	manualControl.setOutput(motionBehavior.getInput());
	motionBehavior.setOutput(motionSequence.getInput());
	motionBehavior.setOutput(legBackLeftLedArbiter.getInput(), legCenterLeftLedArbiter.getInput(), legFrontLeftLedArbiter.getInput(),
							 legBackRightLedArbiter.getInput(), legCenterRightLedArbiter.getInput(), legFrontRightLedArbiter.getInput());

	obstacleInhibitor.connectOutput(motionSequence.getInput());
	//lightFollower.setMotionOutput(obstacleInhibitor.getInput());
	obstacleAvoidance.setOutput(obstacleInhibitor.getInhibitorInput());
	
	//tailRightLedBlinkBehavior.setOutput(tailRightLedArbiter.getInput());
	//headBlinkLightFollower.setOutput(tailLeftLedArbiter.getInput());
	
	headBlinkLightFollower.setOutput(motionBehavior.getInput());
	
	*/
	
	/** new **************/
	
	headLightBehavior.setOutput(headServoArbiter.getInput());
	
}



	
}
