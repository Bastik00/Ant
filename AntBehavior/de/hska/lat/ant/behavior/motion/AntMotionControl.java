package de.hska.lat.ant.behavior.motion;

import java.util.ArrayList;
import java.util.Random;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavior.motion.action.Move;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.arbiter.component.led.LedBrightness;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.NoFilter;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.value.distance.DistanceValue;

public class AntMotionControl extends Behavior {

	private static final float VELOCITY_FORWARD = 0.13f;
	private static final float VELOCITY_BACKWARD = -0.13f;
	
	/**
	 * Defines what happens after an avoid to fall action
	 */
	protected static final boolean ALWAYS_RUNNING = false;
	protected InformationSet legDistanceInformationSet = new InformationSet();
	protected ArbiterConnection motionOutput;
	protected ArbiterAction action;
	private ArbiterConnection[] legLedList = new ArbiterConnection[6];
	
	private enum State {MOVE, MOVE_BACK, MOVE_FORWARD, ROTATE, WAIT};
	private State currentState = State.MOVE;
	

	
	public AntMotionControl(Ant ant) {
		super(ant, AntBehavoirPriority.MOTION_CONTROL_PRIORITY.getPriority(), 100);

		this.name = "ASAI ground detection";
		this.init(ant);
		this.start();
	}
	
	public void setOutput(ArbiterConnection motionOutput)
	{
		this.motionOutput = motionOutput;
	}
	
	private enum LED {FRONT_LEFT_LED, CENTER_LEFT_LED, BACK_LEFT_LED, FRONT_RIGHT_LED, CENTER_RIGHT_LED, BACK_RIGHT_LED};
	protected void init(Ant ant)
	{

		this.legDistanceInformationSet.add( new Information(this.getDistanceValue(ant, AntComponents.FRONT_LEFT_LEG_VCNL4020.getGlobalId()) , new NoFilter() , 1 ,0));
		this.legDistanceInformationSet.add( new Information(this.getDistanceValue(ant, AntComponents.CENTER_LEFT_LEG_4020.getGlobalId()) , new NoFilter() , 1 ,1));
		this.legDistanceInformationSet.add( new Information(this.getDistanceValue(ant, AntComponents.BACK_LEFT_LEG_VCNL4020.getGlobalId()) , new NoFilter() , 1, 2));
		this.legDistanceInformationSet.add( new Information(this.getDistanceValue(ant, AntComponents.FRONT_RIGHT_LEG_4020.getGlobalId()) , new NoFilter() , 1, 3));
		this.legDistanceInformationSet.add( new Information(this.getDistanceValue(ant, AntComponents.CENTER_RIGHT_LEG_VCNL4020.getGlobalId()) , new NoFilter() , 1, 4));
		this.legDistanceInformationSet.add( new Information(this.getDistanceValue(ant, AntComponents.BACK_RIGHT_LEG_VCNL4020.getGlobalId()) , new NoFilter() , 1, 5));

		
		this.informations.add(this.legDistanceInformationSet);
		
	}
	
	public DistanceValue getDistanceValue(Ant ant, int globalId)
	{
		Vcnl4000 vcnl4000 = (Vcnl4000) ant.getComponentOnGlobalId(globalId);
		
		return(vcnl4000.getDistanceValue());
	}
	
	@Override
	public void receive(ArbiterConnection input, ArbiterAction action)
	{
		this.setAction(action);
	};

	@Override
	protected void behave() {
		if (this.informations.hasChanged()==true)
		{
			this.react();
		}
		else
		{
			this.continueAction();
		}
	}
	
	
	protected void react()
	{
		ArbiterAction action;
		boolean init = false;
		
		action = this.actions.getActualAction();
		
		
		if(isNotOnGround())
		{
			if(this.action!=null)
			{
				this.overrideAction();
			}
			
		}
		else
		{
			if (this.action!=null)
			{
				if(this.action.isCompleted() && (this.currentState == State.MOVE_BACK))
				{
					changeActionToRotate();
				}
				else if (this.action.isCompleted() && (this.currentState == State.ROTATE || this.currentState == State.MOVE_FORWARD ))
				{
					if(ALWAYS_RUNNING)
					{
						changeActionToMove();
					}
					else
					{
						changeActionToWait();
					}
				}
				else
				{
					continueAction();
			
				}
			}
		}		
	}

	
	
	private void changeActionToMove() {
		this.action.terminate();
		this.motionOutput.clear(this.id);
		this.action = new Move(AntBehavoirPriority.MANUAL_CONTROL_PRIORITY.ordinal());
		((Move)this.action).setHeading(0.0f);
		((Move)this.action).setVelocity(AntMotionControl.VELOCITY_FORWARD);
		((Move)this.action).setStepCount(0.0f);
		this.action.activate();
		this.motionOutput.receive(action);
		
	}

	private void changeActionToWait()
	{
		this.currentState = State.WAIT;
		((Move)this.action).setHeading( 0.0f);
		((Move)this.action).setVelocity(AntMotionControl.VELOCITY_FORWARD);
		((Move)this.action).setStepCount(0.0f);
		this.action.terminate();
		this.action = null;
		this.motionOutput.clear(this.id);
	}
	
	
	
	private void changeActionToRotate()
	{
		this.action.suspend();
		this.action = new Move(this.id);
		
		float heading = 3.0f;
		if(new Random().nextBoolean()){
			heading *= -1;
		};
		
		((Move)this.action).setHeading(heading);
		((Move)this.action).setVelocity(AntMotionControl.VELOCITY_FORWARD);
		((Move)this.action).setStepCount(15.0f);
		
		this.action.activate();
		this.motionOutput.receive(action);
		this.currentState = State.ROTATE;
	}

	private void continueAction()
	{
		if(this.action != null)
		{
			this.motionOutput.receive(this.action);
			if(this.currentState == State.WAIT && action instanceof Move){
				this.currentState = State.MOVE;
			}
		}
	}

	private void overrideAction() 
	{
		this.action.suspend();
		this.action = new Move(this.id);
		if(this.isLegNotOnGround(0) || this.isLegNotOnGround(3))
		{
			((Move)this.action).setVelocity(VELOCITY_BACKWARD);
			((Move)this.action).setHeading(0.0f);
			this.currentState = State.MOVE_BACK;
		}
		else if(this.isLegNotOnGround(2) || this.isLegNotOnGround(5))
		{
			((Move)this.action).setVelocity(VELOCITY_FORWARD);
			float heading = (float) (Math.PI/2);
			if(new Random().nextBoolean()){
				heading *= -1;
			};
			((Move)this.action).setHeading(heading);
			this.currentState = State.MOVE_FORWARD;
		}
		((Move)this.action).setStepCount(20.0f);
		this.action.activate();
		this.motionOutput.receive(this.action);
		
	}

	private boolean isNotOnGround()
	{
		return (isLegNotOnGround(0) |
				isLegNotOnGround(1) |
				isLegNotOnGround(2) |
				isLegNotOnGround(3) |
				isLegNotOnGround(4) | isLegNotOnGround(5));
	}

	private boolean isLegNotOnGround(int index) {
		boolean isNotOnGround = this.legDistanceInformationSet.get(index).getValue() > 30 || this.legDistanceInformationSet.get(index).getValue() < 1;
		LedBrightness ledBrightness = new LedBrightness(this.id);
		if(isNotOnGround){
			ledBrightness.setBrightness(255.0f);
			System.out.println("Leg " + index + " distance: " + this.legDistanceInformationSet.get(index).getValue() );
		}
		else
		{
			ledBrightness.setBrightness(0.0f);
		}
		legLedList[index].receive(ledBrightness);
		return isNotOnGround;
	}

	public void setOutput(ArbiterConnection backLeftLed, ArbiterConnection centerLeftLed,
			ArbiterConnection frontLeftLed, ArbiterConnection backRightLed, ArbiterConnection centerRightLed,
			ArbiterConnection frontRightLed) {
			legLedList[LED.BACK_LEFT_LED.ordinal()] = backLeftLed;
			legLedList[LED.CENTER_LEFT_LED.ordinal()] = centerLeftLed;
			legLedList[LED.FRONT_LEFT_LED.ordinal()] = frontLeftLed;
			legLedList[LED.BACK_RIGHT_LED.ordinal()] = backRightLed;
			legLedList[LED.CENTER_RIGHT_LED.ordinal()] = centerRightLed;
			legLedList[LED.FRONT_RIGHT_LED.ordinal()] = frontRightLed;
		
		
	}
	
	
	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.motionOutput);
		
		return(list);
	}	
	
}
