package de.hska.lat.ant.behavior.lightFollower;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavior.motion.action.Move;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.information.head.AntHeadLuxInformationSet;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.behavior.Behavior;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.BandpassFilterInformation;
import de.hska.lat.behavior.information.filter.FilterInformation;

/**
 * A behavior that has an ant robot move forward if a light
 * blinking at a given frequency is recognized.
 * 
 * The behavior analyzes the data collected from the ant robot's
 * five head light sensors. The data is preprocessed by a bandpass
 * filter which only passes signals of a certain frequency. If a
 * blinking light of the wanted frequency is recognized the ant
 * robot is moving forward; otherwise the movement is stopped.
 * 
 * (In future implementations the robot is expected to follow
 * the blinking light. This is not possible at the moment since
 * the used sensors are only able to measure with a frequency
 * of 2.0 Hz. The response time is therefore really low.)* 
 * 
 * @author Julia Vogel
 *
 */
public class AntHeadBlinkLightFollower extends Behavior
{

	private final static int BEHAVIOR_PERIOD = 100;
	private final static float FREQUENCY = 0.5f;	

	private InformationSet lightInformation;
	private Move moveAction;
	private ArbiterConnection output;

	private int counter = 0;
	private int maxCount;

	private float[] values;
	private boolean[] isBlinking;


	/**
	 * Creates a behavior for having an ant robot follow
	 * a light blinking at a certain frequency. The robot
	 * starts moving if the blinking light is recognized,
	 * and stops otherwise.
	 *	
	 * @param ant	The ant robot to be controlled.
	 */
	public AntHeadBlinkLightFollower(Ant ant)
	{
		super(ant, AntBehavoirPriority.BLINK_LIGHT_FOLLOWER_PRIORITY.getPriority(), BEHAVIOR_PERIOD);
		this.name = "blink light follower";
		FilterInformation filterInformation = new BandpassFilterInformation(FREQUENCY, BEHAVIOR_PERIOD);
		this.lightInformation = new AntHeadLuxInformationSet(ant, filterInformation);
		this.informations.add(this.lightInformation);

		maxCount = (int) ((1.0f / FREQUENCY) / ((float) BEHAVIOR_PERIOD / 1000.0f));

		values = new float[lightInformation.size()];
		isBlinking = new boolean[lightInformation.size()];

		this.start();
	}


	public void setOutput(ArbiterConnection output)
	{
		this.output = output;
	}	


	private void react()
	{	
		int i = 0;
		for(Information info: lightInformation)
		{
			values[i] = info.getValue();
			if (values[i] == 0.0f)
			{
				isBlinking[i] = false;
			}
			else
			{
				isBlinking[i] = true;
			}

			i++;
		}


		String actionStr = "";
		if (!isBlinking[0] && !isBlinking[1] && !isBlinking[2] && !isBlinking[3] && !isBlinking[4])
		{
//			actionStr = "action: stop";
			//this.stopAction = new ActionStop(this.id);
			//this.output.receive(stopAction);
			this.output.clear(this.id);
		}
		else if (isBlinking[2])
		{
//			actionStr = "action: move";
			this.moveAction = new Move(this.id);
			this.moveAction.setHeading(0.0f);
			this.moveAction.setVelocity(-0.13f);
			this.output.receive(moveAction);
		}
		else
		{
	//		actionStr = "action: stop";
		//	this.stopAction = new ActionStop(this.id);
		//	this.output.receive(stopAction);
			this.output.clear(this.id);
		}

		
		counter++;
		if (counter == maxCount)
		{
			counter = 0;
			i = 0;
			for(boolean blink: isBlinking)
			{
				if (blink)
				{
	//				System.out.println(String.format("sensor %d, %f lx: true", i, values[i]));
				}
				else
				{
	//				System.out.println(String.format("sensor %d, %f lx: false", i, values[i]));
				}

				i++;
			}
//			System.out.println(actionStr);
		}

	}


	@Override
	public void behave()
	{
		react();			
	}

	
	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.output);
		
		return(list);
	}
	
}
