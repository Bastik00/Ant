package de.hska.lat.ant.behavior.head.light;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.information.head.AntHeadLuxInformationSet;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.component.servo.ServoRotate;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.information.Information;


/**
 * Asai light avoidance behavior
 * @author tavin
 *
 */


public class LightFollower extends Behavior
{


	protected AntHeadLuxInformationSet lightInformation;
	
	protected ArbiterConnection headOutput;
	protected ServoRotate action;
	
	
	
	
public LightFollower(Ant ant)
{
	super(ant, AntBehavoirPriority.LIGHT_AVOIDANCE_PRIORITY.getPriority(), 100);

	this.name = "ASAI light avoidance";
	
	this.lightInformation = new AntHeadLuxInformationSet(ant, null);
	this.informations.add(this.lightInformation);
	
	this.start();
}

	



public void setHeadOutput(ArbiterConnection headOutput)
{
	this.headOutput = headOutput;
}




public void follow()
{
	
}

public float getIntensity(int chanel)
{
	return (this.lightInformation.get(chanel).getValue());
}

public float getMaxIntensity()
{
	return(this.lightInformation.getMax().getValue());
	
}

public float getMinIntensity()
{
	return(this.lightInformation.getMin().getValue());
	
}



protected static final float FACTOR = 0.9f; 

/**
 * brightest zone  
 * @return
 */
protected float onCenter()
{
	
	float left;
	float right;
	float velocity;
	
	left = this.lightInformation.get(1).getValue();
	right =this.lightInformation.get(3).getValue();
	
	
	
	
	if ((left) < right * FACTOR)
	{
		velocity = 0.1f;
	}
	else if ((right) < left * FACTOR)
	{
		velocity = -0.1f;		
	}
	else
	{
		velocity = 0;
	}

	
	return(velocity);
}






/**
 * react to changed informations
 */
protected void react()
{
	Information max;

	int id;
	float velocity;
  
	
	max = this.lightInformation.getMax();
	
	id = max.getId();
	

	switch (id)
	{
	case 0:
		velocity = -30;
		break;
		
		
	case 1:
		velocity = -20;
		break;
		
	case 2:
		velocity = onCenter();
		break;
		
	case 3:
		velocity = 20;
		break;
		
	case 4:
		velocity = 30;
		break;
		
	default :	
		velocity = 0;
		break;

	}

		
	if (velocity == 0)
	{
		if (action!=null)
		{
			this.headOutput.clear(this.id);
			this.action.terminate();
			this.action = null;
		}
		
	}
	else
	{
		if (action==null)
		{
			action = new ServoRotate(this.id);
			this.action.setVelocity(velocity);
			this.headOutput.receive(action);
		}
		else
		{
			this.action.setVelocity(velocity);
		}
	}

	
	this.notifyChange();	
	
	
	/*
	//  is max right or left sensor?
	
	if ((max.getValue()>800) && 
		((id == AntHeadLuxInformationSet.SENSOR_LEFT_90) ||
		(id == AntHeadLuxInformationSet.SENSOR_RIGHT_90)))
	{
		
		
		float velocity;
		velocity = max.getValue() - min.getValue();
		
		velocity/=60;
		
		if (max.getId() == AntHeadLuxInformationSet.SENSOR_LEFT_90)
		{
			velocity = 0 - velocity;
		}

		
		if (action==null)
		{
				action = new ServoRotate(this.id);
				this.action.setVelocity(velocity);
				this.motionOutput.receive(action);
		}
		else
		{
			this.action.setVelocity(velocity);
		}

	}
	else
	{
		if (action!=null)
		{
			this.motionOutput.clear(this.id);
			this.action.terminate();
			this.action = null;

		}

	}
	
*/
}

/*
 
protected void react()
{
	Information max;
	Information min;
	int id;
	
	// get ID of max & min values  
	
	max = this.lightInformation.getMax();
	min = this.lightInformation.getMin();
	
	id = max.getId();
	
	//  is max right or left sensor?
	
	if ((max.getValue()>800) && 
		((id == AntHeadLuxInformationSet.SENSOR_LEFT_90) ||
		(id == AntHeadLuxInformationSet.SENSOR_RIGHT_90)))
	{
		
		
		float velocity;
		velocity = max.getValue() - min.getValue();
		
		velocity/=60;
		
		if (max.getId() == AntHeadLuxInformationSet.SENSOR_LEFT_90)
		{
			velocity = 0 - velocity;
		}

		
		if (action==null)
		{
				action = new ServoRotate(this.id);
				this.action.setVelocity(velocity);
				this.motionOutput.receive(action);
		}
		else
		{
			this.action.setVelocity(velocity);
		}

	}
	else
	{
		if (action!=null)
		{
			this.motionOutput.clear(this.id);
			this.action.terminate();
			this.action = null;

		}

	}
	

}

 
 */

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
		list.add(this.headOutput);
		
		return(list);
	}










	
	
}