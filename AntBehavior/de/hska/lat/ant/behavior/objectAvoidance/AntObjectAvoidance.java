package de.hska.lat.ant.behavior.objectAvoidance;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavior.motion.action.Hold;

import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.NoFilter;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.value.distance.DistanceValue;
/**
 * Asai light avoidance behavior
 * @author tavin
 *
 */
public class AntObjectAvoidance extends Behavior
{


	InformationSet distanceInformation = new InformationSet();
	
	protected ArbiterConnection motionOutput;

	
	
	
public AntObjectAvoidance(Ant ant)
{
	super(ant, AntBehavoirPriority.LIGHT_AVOIDANCE_PRIORITY.getPriority(), 100);
	this.init(ant);
	this.name = "ASAI light avoidance";
	
	this.start();
	
}

	



public void setOutput(ArbiterConnection motionOutput)
{
	this.motionOutput = motionOutput;
}

	




protected void init(Ant ant)
{

	this.distanceInformation.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_LEFT_SIDE.getGlobalId()) , new NoFilter() , 1 ,0));
	this.distanceInformation.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_LEFT.getGlobalId()) , new NoFilter() , 1 ,1));
	this.distanceInformation.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_CENTER.getGlobalId()) , new NoFilter() , 1, 2));
	this.distanceInformation.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_RIGHT.getGlobalId()) , new NoFilter() , 1, 3));
	this.distanceInformation.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_RIGHT_SIDE.getGlobalId()) , new NoFilter() , 1, 4));

	
	this.informations.add(this.distanceInformation);
	
}





public DistanceValue getDistanceValue(Ant ant, int globalId)
{
	Vcnl4000 vcnl4000 = (Vcnl4000) ant.getComponentOnGlobalId(globalId);
	
	return(vcnl4000.getDistanceValue());
}





protected void avoid()
{
	
}


ArbiterAction actualAction;



protected void react()
{
	
	Information inf;

		
	
	
	inf = this.distanceInformation.get(2);
	
	
	if (inf.getValue()<40)
	{
		if (actualAction==null)
		{
			System.out.println("object avoider create action");
			
			this.actualAction  = new Hold(this.id);
			this.motionOutput.receive(this.actualAction); 
		}
	
			
//		System.out.println("avoid !! :"+ inf.getValue()); // TODO Delete

	}
	else
	{
		if (this.actualAction!=null)
		{
			System.out.println("object avoider terminate action");
			this.motionOutput.clear(this.id);
		//	this.actualAction.terminate();
			this.actualAction = null;
		
			

			
		}
	}
	
/*	Information max;
	Information min;
	
	// get ID of max & min values  
	
	max = this.lightInformation.getMax();
	min = this.lightInformation.getMin();
	
	int id;
	
	
	
	id = max.getId();
	
	//  is max right or left sensor?
	
	if ((max.getValue()>800) && ((id == 0) || (id == 4)))
	{
		if (action==null)
		{
				System.out.println("avoidance create action");
				action = new ServoRotate(this.id);
				this.motionOutput.receive(action);
		}
		float velocity;
		velocity = max.getValue()-min.getValue();
		
		velocity/=60;
		
		if (max.getId() ==0)
		{
			velocity= 0-velocity;
		}
		action.setVelocity(velocity);
		
		System.out.println("aua !! :"+max.getValue());

		}
	else
	{
		System.out.println("non aua !! :"+max.getValue());
		if (action!=null)
		{
			this.motionOutput.clear(this.id);
			System.out.println("avoidance terminate action");	
			this.action.terminate();
			this.action= null;

		}

	}
	
*/
}



	@Override
	protected void behave()
	{
		
		if (this.informations.hasChanged()==true)
		{
			this.react();
		}
		else
		{
		}
		

		
	}

	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.motionOutput);
		
		return(list);
	}

	
}