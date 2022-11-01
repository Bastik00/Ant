package de.hska.lat.behavior.arbiter.view;

import javax.swing.border.TitledBorder;

import de.hska.lat.behavior.arbiter.InhibitorNode;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;

public class InhibitorNodeViewer extends ArbiterViewer<InhibitorNode>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7128563813100014863L;

	
	
	
	public InhibitorNodeViewer(RobotBehavior<?> behavior, InhibitorNode arbiter, RobotSettings settings)
	{
		super(behavior, arbiter, settings);
		
		this.setBorder(new TitledBorder("I: "+arbiter.getName()));
	}
	
	
	
	public void build()
	{
		
	}
	

}
