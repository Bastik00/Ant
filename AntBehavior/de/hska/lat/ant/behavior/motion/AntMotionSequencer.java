package de.hska.lat.ant.behavior.motion;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavior.motion.action.Hold;
import de.hska.lat.ant.behavior.motion.action.Move;
import de.hska.lat.ant.behavior.motion.action.Rotate;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.action.ActionWait;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.arbiter.component.servo.ServoRotateTo;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.NoFilter;
import de.hska.lat.robot.component.actor.servo.Servo;
import de.hska.lat.robot.component.actor.servo.ServoDestinationValue;
import de.hska.lat.robot.value.servo.ServoAngleValue;

public class AntMotionSequencer extends Behavior
{

	protected enum MovementState_e {IDLE, TILT, MONITOR_TILT, PUSH, MONITOR_PUSH, RELEASE, MONITOR_RELEASE, ROTATE, MONITOR_ROTATE};
	
	protected MovementState_e movementState = MovementState_e.IDLE;
	
	
	protected final float MAX_LEFT_SERVO_VELOCITY = 4.0f;
	protected final float MAX_CENTER_SERVO_VELOCITY = 4.0f;
	protected final float MAX_RIGHT_SERVO_VELOCITY = 4.0f;
	
	
	
	protected ArbiterAction activeAction;

	protected Information leftServoPosition;
	protected Information leftServoDestination;

	
	protected Information centerServoPosition;
	protected Information centerServoDestination;
	
	protected Information rightServoPosition;
	protected Information rightServoDestination;

	
	protected InformationSet servoPositions = new InformationSet();
	protected InformationSet servoDestinations = new InformationSet();
	
	protected ArbiterAction leftServoAction = null;
	protected ArbiterAction centerServoAction = null;
	protected ArbiterAction rightServoAction = null;
	
	
	
	protected ArbiterConnection leftServoOutput;
	protected ArbiterConnection centerServoOutput;
	protected ArbiterConnection rightServoOutput;
	
	
	protected float centerDestination;
	protected float leftDestination;
	protected float rightDestination;

	
	
	public AntMotionSequencer(Ant ant)
	{
		super(ant, AntBehavoirPriority.MOTION_SEQUENCER_PRIORITY.getPriority(), 100);
		this.name = "motion sequence";
	
		
		this.leftServoPosition = new Information(this.getServoPositionValue(ant, AntComponents.LEFT_SERVO.getGlobalId()) , new NoFilter() , 1 ,0);
		this.leftServoDestination = new Information(this.getServoDestinationValue(ant, AntComponents.LEFT_SERVO.getGlobalId()) , new NoFilter() , 1 ,0);

		this.centerServoPosition = new Information(this.getServoPositionValue(ant, AntComponents.CENTER_SERVO.getGlobalId()) , new NoFilter() , 1 ,0);
		this.centerServoDestination = new Information(this.getServoDestinationValue(ant, AntComponents.CENTER_SERVO.getGlobalId()) , new NoFilter() , 1 ,0);

		
		this.rightServoPosition = new Information(this.getServoPositionValue(ant, AntComponents.RIGHT_SERVO.getGlobalId()) , new NoFilter() , 1 ,0);
		this.rightServoDestination = new Information(this.getServoDestinationValue(ant, AntComponents.RIGHT_SERVO.getGlobalId()) , new NoFilter() , 1 ,0);

		
		this.servoPositions.add(this.leftServoPosition);
		this.servoPositions.add(this.centerServoPosition);
		this.servoPositions.add(this.rightServoPosition);
		
		this.servoDestinations.add(this.leftServoDestination);
		this.servoDestinations.add(this.centerServoDestination);
		this.servoDestinations.add(this.rightServoDestination);
		this.start();
	}



public ServoAngleValue getServoPositionValue(Ant ant, int globalId)
{
	Servo servo = (Servo) ant.getComponentOnGlobalId(globalId);
	
	return(servo.getServoValue());
}

public ServoDestinationValue getServoDestinationValue(Ant ant, int globalId)
{
	Servo servo = (Servo) ant.getComponentOnGlobalId(globalId);
	
	return(servo.getDestinationValue());
}







public boolean doTilt()
{
	if(!(activeAction instanceof Rotate))
	{
		centerDestination =0.22f;
	}
	
	

	if (this.leftServoPosition.getValue() < this.rightServoPosition.getValue())
	{
		centerDestination *=-1;
	}		
		
	if(activeAction instanceof Rotate)
	{
		centerDestination *=-1;
	}
	
	if (this.movementVelocity < 0)
	{
		centerDestination *=-1;
	}
	
	ServoRotateTo action = new ServoRotateTo(this.id);
	
	
	
	action.setDestination(centerDestination);
	action.setVelocity(this.movementVelocity *MAX_CENTER_SERVO_VELOCITY);
	
	
	
	this.setCenterServoOutput(action);
	
	this.setLeftServoOutput(new Hold(this.id));
	this.setRightServoOutput(new Hold(this.id));
	
	this.movementState = MovementState_e.MONITOR_TILT;
	return(true);
}



public boolean doMonitorTilt()
{
	

	if (this.centerServoPosition.inRange(centerDestination, this.centerServoPosition.getValue(), 0.01f))
	{
		if(this.activeAction instanceof Rotate)
		{
			this.movementState = MovementState_e.ROTATE;
		}
		else
		{
			this.movementState = MovementState_e.PUSH;
		}
		return(true);
	}
	
	
	return(false);
}





/** 
 * 
 */
public boolean push()
{
	
	
	{
		leftDestination = Math.abs(leftDestination);
		rightDestination = Math.abs(rightDestination);
		if (this.movementVelocity > 0)
		{
		
			if (this.leftServoPosition.getValue() > this.rightServoPosition.getValue())
			{
				leftDestination *= -1;
			}
			else
			{
				rightDestination *= -1;
			}
		}
		else
		{
			
			if (this.leftServoPosition.getValue() > this.rightServoPosition.getValue())
			{
				leftDestination *= -1;
			}
			else
			{
				rightDestination *= -1;
			}
		}
	}
	
	this.movementState = MovementState_e.MONITOR_PUSH;
	return(true);
}	



public boolean monitorPush()
{
	
	if (this.leftServoDestination.getValue() != leftDestination)
	{
		ServoRotateTo leftAction = new  ServoRotateTo(this.id);
		
		leftAction.setDestination(leftDestination);
		leftAction.setVelocity(3.0f);
		this.setLeftServoOutput(leftAction);
	}
	
	if (this.rightServoDestination.getValue() != rightDestination)
	{
		ServoRotateTo rightAction = new  ServoRotateTo(this.id);
		
		rightAction.setDestination(rightDestination);
		rightAction.setVelocity(3.0f);
		this.setRightServoOutput(rightAction);
		
	}
	
	
	if ((this.leftServoPosition.inRange(leftDestination, this.leftServoPosition.getValue(), 0.01f)) &&
			(this.rightServoPosition.inRange(rightDestination, this.rightServoPosition.getValue(), 0.01f)))
	{
		if(this.activeAction instanceof Move){
			((Move)this.activeAction).countStep();
		}
		this.movementState = MovementState_e.TILT;
		return(true);
	}
	
	return(false);
}

public boolean doMonitorRotate() {
	
	return this.monitorPush();
	
}



public boolean rotate() {
	
	if(this.leftDestination != this.rightDestination)
	{
		this.rightDestination = 0.13f;
		this.leftDestination = 0.13f;
	}
	this.leftDestination *= -1;
	this.rightDestination *= -1;
	
	this.movementState = MovementState_e.MONITOR_ROTATE;
	return true;
}



/**
 * movement is completed or aborted relase 
 * @return
 */
protected boolean doRelease()
{
	ServoRotateTo action = new ServoRotateTo(this.id);
	action.setDestination(0);
	action.setVelocity(3.0f);
	
	this.setLeftServoOutput(new Hold(this.id));
	this.setRightServoOutput(new Hold(this.id));
	this.setCenterServoOutput(action);
	this.movementState = MovementState_e.MONITOR_RELEASE;
	return(true);
}




protected boolean doMonitorRelease()
{
	
	if (this.centerServoPosition.inRange(0, this.centerServoPosition.getValue(), 0.01f))
	{
		this.clearLeftServoOutput();
		this.clearCenterServoOutput();
		this.clearRightServoOutput();
		this.movementState = MovementState_e.IDLE;
		return(true);
	}

	
	return(false);
}






protected void a() 
{
	boolean stateCompleted;
	
	do
	{
		
		switch (this.movementState)
		{
			case TILT:
				stateCompleted = doTilt();
				break;
	
			
			case MONITOR_TILT:
				stateCompleted = this.doMonitorTilt();
				break;
	
			
			case PUSH:
				stateCompleted = this.push();
				break;
				
			case MONITOR_PUSH:
				stateCompleted = this.monitorPush();
				break;
			
			case ROTATE:
				stateCompleted = this.rotate();
				break;
				
			case MONITOR_ROTATE:
				stateCompleted = this.doMonitorRotate();
				break;
				
			case RELEASE:
				stateCompleted = doRelease();
				break;

	
			case MONITOR_RELEASE:
				stateCompleted = doMonitorRelease();
				break;	
			
			default:
				this.movementState = MovementState_e.IDLE;
				stateCompleted= false;
				break;
		}
		}	
		while (stateCompleted==true);
			
}




protected float movementVelocity;


protected void executeMove(boolean initialize)
{
	
	if (initialize)
	{
		this.leftServoAction=null;
		this.centerServoAction=null;
		this.rightServoAction=null;
		this.movementState=MovementState_e.TILT;
		this.leftDestination = 0.13f * ((Move)this.activeAction).getLeftStepSize();
		this.rightDestination = 0.13f * ((Move)this.activeAction).getRightStepSize();
		this.movementVelocity = ((Move)this.activeAction).getVelocity();
	}
	
}



public void executeWait(boolean initialize)
{
	if (initialize)
	{
		this.movementState=MovementState_e.RELEASE;
	}
}



/**
 * set action for the left servo 
 * @param action left servo action
 */
protected void setLeftServoOutput(ArbiterAction action)
{
	if (this.leftServoOutput!=null)
	{
		this.leftServoOutput.receive(action);
	}
	
	this.leftServoAction=action;
}


/**
 * clear action for the left servo  
 */
protected void clearLeftServoOutput()
{
	if (this.leftServoOutput!=null)
	{
		this.leftServoOutput.clear(this.id);
	}
	
	this.leftServoAction=null;
}




/**
 * set action for the center servo 
 * @param action center servo action
 */
protected void setCenterServoOutput(ArbiterAction action)
{
	if (this.centerServoOutput!=null)
	{
		this.centerServoOutput.receive(action);
	}
	
	this.centerServoAction=action;
}


/**
 * clear action for the center servo  
 */
protected void clearCenterServoOutput()
{
	if (this.centerServoOutput!=null)
	{
		this.centerServoOutput.clear(this.id);
	}
	
	this.centerServoAction=null;
}



/**
 * set action for the right servo 
 * @param action center right action
 */
protected void setRightServoOutput(ArbiterAction action)
{
	if (this.rightServoOutput!=null)
	{
		this.rightServoOutput.receive(action);
	}
	
	this.rightServoAction=action;
}

/**
 * clear action for the right servo  
 */
protected void clearRightServoOutput()
{
	if (this.rightServoOutput!=null)
	{
		this.rightServoOutput.clear(this.id);
	}
	
	this.rightServoAction=null;
}





public void executeAction(ArbiterAction action, boolean initialize)
{
	if (action instanceof Move)
	{
		this.executeMove(initialize);
	}
	else if (action instanceof ActionWait)
	{
		this.executeWait(initialize);
	}
	else if (action instanceof Rotate)
	{
		this.executeRotate(initialize);
	}
	else if (action instanceof Hold)
	{
		
	}
	
	
	

	
}
	
	
	
protected void executeRotate(boolean initialize)
{
	if (initialize)
	{
		this.leftServoAction=null;
		this.centerServoAction=null;
		this.rightServoAction=null;
		this.movementState=MovementState_e.TILT;
		this.leftDestination = 0.13f;
		this.rightDestination = -0.13f;
		if(((Rotate)activeAction).getRotation() <= 0.0f)
		{
			this.centerDestination = 0.22f;
		}
		else
		{
			this.centerDestination = -0.22f;
		}
		this.movementVelocity = ((Rotate)this.activeAction).getVelocity();
	}
}



	@Override
	protected void behave()
	{
		this.servoPositions.capture();
		ArbiterAction action;
		boolean init = false;
		
		action = this.actions.getActualAction();
		
		if (action!=this.activeAction)
		{
			this.activeAction= action;
			init =true;
		}
	
		/*
		if (action.getStatus() != ActionStatus_e.ACTIVE)
		{
			action.activate();
			this.activeAction.interrupt();
			this.activeAction = action;
		
		}
		*/
		
		this.executeAction(this.activeAction, init);
		this.a();
		this.notifyChange();
	}
	

public void setOutput(ArbiterConnection leftServoOutput, ArbiterConnection centerServoOutput, ArbiterConnection rightServoOutput)
{
	this.leftServoOutput = leftServoOutput;
	this.centerServoOutput = centerServoOutput;	
	this.rightServoOutput = rightServoOutput;
}





@Override
public void clear(ArbiterConnection input,  int priority)
{
	
	this.removeAction(priority);

}


@Override
public void receive(ArbiterConnection input, ArbiterAction action)
{
	
	this.setAction(action);

}
	




public ArbiterAction getActiveAction()
{
	return (this.activeAction);
}
	


public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
	list.add(this.leftServoOutput);
	list.add(this.centerServoOutput);
	list.add(this.rightServoOutput);
	
	return(list);
}

}
