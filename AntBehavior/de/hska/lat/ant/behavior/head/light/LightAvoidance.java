package de.hska.lat.ant.behavior.head.light;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavior.motion.action.Move;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.information.head.AntHeadLuxInformationSet;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.arbiter.component.servo.ServoRotateTo;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;

/**
 * Asai light avoidance behavior
 * @author tavin
 *
 */
public class LightAvoidance extends Behavior
{


	InformationSet lightInformation = new InformationSet();
	
	protected ArbiterConnection headOutput;
	protected ArbiterConnection motionOutput;

	
	
	
public LightAvoidance(Ant ant)
{
	super(ant, AntBehavoirPriority.LIGHT_AVOIDANCE_PRIORITY.getPriority(), 100);
	
	this.lightInformation = new AntHeadLuxInformationSet(ant, null);
	this.informations.add(this.lightInformation);
	
	this.name = "ASAI light follower";
	
	this.start();
}

	



public void setHeadOutput(ArbiterConnection headOutput)
{
	this.headOutput = headOutput;
}



public void setMotionOutput(ArbiterConnection motionOutput)
{
	this.motionOutput = motionOutput;
}	




protected ServoRotateTo actualAction;

protected Move moveAction;


protected float threshold = 10000;
protected float value;




public float getValue()
{
	return(this.value);
}

protected void react() 
{
	Information inf;

	
	
/*		
	
	
	inf = this.lightInformation.get(2);
		value = inf.getValue();
	
		
		
		
	if (inf.getValue()>400)
	{
		if (actualAction==null)
		{
			System.out.println("follower create action");
			this.actualAction = new ServoRotateTo(this.id);
			this.headOutput.receive(this.actualAction);
			
			this.moveAction  = new Move(this.id);
			this.moveAction.setVelocity(value/17000);				
			
			this.motionOutput.receive(moveAction); 
		}
		this.moveAction.setVelocity(value/17000);	
			System.out.println("backward !!!!!!!!!!!!!!!!!!!");
		
		this.actualAction.setDestination(0);

			
		System.out.println("follow me !! :"+ inf.getValue());

	}
	else
	{
		if (this.actualAction!=null)
		{
			System.out.println("follower terminate action");
			this.headOutput.clear(this.id);
		//	this.actualAction.terminate();
			this.actualAction = null;
		
			this.motionOutput.clear(this.id);
			this.moveAction = null;
			
		}
	}
	'*/
	this.notifyChange();
}



	@Override
	protected void behave()
	{
		
		if (this.informations.hasChanged()==true)
		{
			this.react();
			
			//	System.out.println("max :"+this.lightInformation.getMax());
		
		}
		else
		{
		}
		

		
	}

	
@Override
protected void onTerminated(ArbiterAction action)
{

/*	
		if (action == this.actualAction)
			actualAction= null;
			*/
}



public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
	list.add(this.motionOutput);
	list.add(this.headOutput);
	
	return(list);
}

	
}