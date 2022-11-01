package de.hska.lat.behavior.arbiter.view;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;

public class ArbiterDefaultViewer extends ArbiterViewer<Arbiter>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5547105160481844157L;

	public ArbiterDefaultViewer(RobotBehavior<?> behavior, Arbiter arbiter,
			RobotSettings settings)
	{
		super(behavior, arbiter, settings);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void build()
	{
		this.setSize(100, 100);
		// TODO Auto-generated method stub
		
	}

}
