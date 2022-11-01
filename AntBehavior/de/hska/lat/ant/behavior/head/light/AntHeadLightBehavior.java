package de.hska.lat.ant.behavior.head.light;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.ArbiterGroup;
import de.hska.lat.behavior.arbiter.SuppressorNode;


public class AntHeadLightBehavior extends ArbiterGroup
{

	protected LightAvoidance lightAvoidance;
	protected LightFollower lightFollower;
	protected SuppressorNode lightNode; 
	
	protected ArbiterConnection headOutput;
	
public AntHeadLightBehavior(Ant ant)
{
	super(AntBehavoirPriority.HEAD_LIGHT_PRIORITY.getPriority());
	init(ant);
}



protected void init(Ant ant)
{

	
	this.lightAvoidance = new LightAvoidance(ant);
	this.addArbiter(this.lightAvoidance);
	
	this.lightFollower = new LightFollower(ant);
	this.addArbiter(this.lightFollower);
	
	this.lightNode = new SuppressorNode(AntBehavoirPriority.LIGHT_SUPRESSOR_PRIORITY.getPriority(), "light node");
	this.addArbiter(this.lightNode);
	
	
	this.lightFollower.setHeadOutput(this.lightNode.getActionInput());
	this.lightAvoidance.setHeadOutput(this.lightNode.getSuppressorInput());
	
	
}
	

public void setOutput(ArbiterConnection output)
{
	this.lightNode.connectOutput(output);
}


 public static AntHeadLightBehavior create(Ant ant )
 {
	 AntHeadLightBehavior behavior;
	 
	 behavior = new AntHeadLightBehavior(ant);
	 
	 return(behavior);
 }


}
