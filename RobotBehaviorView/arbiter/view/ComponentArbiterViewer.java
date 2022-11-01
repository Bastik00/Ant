package de.hska.lat.behavior.arbiter.view;



import de.hska.lat.behavior.arbiter.ComponentArbiter;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;

public abstract class ComponentArbiterViewer<A extends ComponentArbiter> extends ArbiterViewer<A>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7227845237468149847L;

	public ComponentArbiterViewer(RobotBehavior<?> behavior, A arbiter, RobotSettings settings)
	{
		super(behavior, arbiter, settings);
		
		
		/*
		this.setBorder(new TitledBorder("A: "+arbiter.getName()));
		this.setLayout(null);
		this.setSize(260, 100);*/
		this.update((ComponentArbiter) arbiter);
		
	}








public void update(ComponentArbiter arbiter)
{
	
}
	
	
	
	
	
	
}
