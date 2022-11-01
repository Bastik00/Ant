package de.hska.lat.behavior.arbiter.view;

import java.util.ListIterator;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.ArbiterGroup;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.behavior.view.BehaviorViewer;
import de.hska.lat.robot.RobotSettings;

public class ArbiterGroupViewer <A extends ArbiterGroup> extends ArbiterViewer<A>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787021440300365575L;

	public ArbiterGroupViewer(BehaviorViewer behaviorViewer, RobotBehavior<?> behavior, A arbiter,
			RobotSettings settings)
	{
		super( behavior, arbiter, settings);
		// TODO Auto-generated constructor stub
	
		init (behaviorViewer, arbiter, settings);
	
	
	}

	
	public void build()
	{
		
	}
	
	protected void init(BehaviorViewer behaviorViewer, ArbiterGroup arbiterGroup, RobotSettings settings)
	{
	    
		ListIterator<Arbiter> arbiterList;
		
		for (arbiterList = arbiterGroup.getArbiters() ; arbiterList.hasNext(); ) 
		{
		    Arbiter arbiter = arbiterList.next();
		    ArbiterViewer<?> arbiterViewer = behaviorViewer.findViewer(arbiter,  settings);

		    if (arbiterViewer!=null)
		    {
		    //	this.add(arbiterViewer);
		    	behaviorViewer.add(arbiterViewer);
		    }

			
			
		}
		/*
		
		if (behavior!=null)
		for (arbiterList = this.behavior.getArbiters() ; arbiterList.hasNext(); ) 
		{
		    
		}
		
		*/
	}
	
	
}
