package de.hska.lat.ant.behavior.view;


import de.hska.lat.ant.behavior.head.light.LightFollower;
import de.hska.lat.ant.behavior.head.light.LightAvoidance;
import de.hska.lat.ant.behavior.led.AntLedBlinkBehavior;
import de.hska.lat.ant.behavior.led.view.AntTailLeftLedBlinkBehaviorViewer;
import de.hska.lat.ant.behavior.lightAvoidance.LightAvoidanceBehaviorViewer;
import de.hska.lat.ant.behavior.lightFollower.LightFollowerBehaviorViewer;
import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.view.ArbiterViewer;
import de.hska.lat.behavior.behavior.RobotBehavior;

import de.hska.lat.behavior.view.BehaviorViewer;
import de.hska.lat.robot.RobotSettings;




public class AntBehaviorViewer extends BehaviorViewer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3533934849855070957L;

	RobotBehavior<?> behavior;
	RobotSettings settings;
	
	protected static final String X_POSITION_KEY 	= ".xPositionKey";
	protected static final String Y_POSITION_KEY 	= ".yPositionKey";
	protected static final String X_SIZE_KEY 	= ".xSizeKey";
	protected static final String Y_SIZE_KEY 	= ".ySizeKey";
	
	
	
	protected String settingsKey;
	
public 	AntBehaviorViewer(RobotBehavior<?> behavior, RobotSettings settings)
{
	super(behavior,  settings);
	this.settingsKey=this.getClass().getName();
	
	this.settings = settings;
	this.behavior = behavior;
	
}
	



public ArbiterViewer<?> findViewer(Arbiter arbiter,RobotSettings settings)
{
	
	    ArbiterViewer<?> arbiterViewer;
	    
	    if (arbiter instanceof AntLedBlinkBehavior)
	    {
		    arbiterViewer = new AntTailLeftLedBlinkBehaviorViewer(behavior, (AntLedBlinkBehavior)arbiter, settings);
	    }
	    else if (arbiter instanceof LightAvoidance)
	    {
		    arbiterViewer = new LightFollowerBehaviorViewer(behavior, (LightAvoidance)arbiter, settings);
	    }
	    else if (arbiter instanceof LightFollower)
	    {
		    arbiterViewer = new LightAvoidanceBehaviorViewer(behavior, (LightFollower)arbiter, settings);
	    }
	    
	    
	    
	    
	    else
	    {
	    	 arbiterViewer = super.findViewer(arbiter, settings);
	    }
	    return(arbiterViewer);
	    
	
}





}
