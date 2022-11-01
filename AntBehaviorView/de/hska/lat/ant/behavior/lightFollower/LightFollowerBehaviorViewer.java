package de.hska.lat.ant.behavior.lightFollower;

import javax.swing.JLabel;


import de.hska.lat.ant.behavior.head.light.LightAvoidance;
import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.view.ArbiterViewer;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;

public class LightFollowerBehaviorViewer  extends ArbiterViewer<LightAvoidance>
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848034897696851698L;
	
	protected JLabel action;
	
	public LightFollowerBehaviorViewer(RobotBehavior<?> behavior, LightAvoidance arbiter, RobotSettings settings)
	{
		super(behavior, arbiter, settings);
		
		this.arbiter.addChangeListener(this);
		
		this.setSize(150,50);
		this.build();
	}
		

	protected void build()
	{
		this.action  = new JLabel("test");
		this.action.setBounds (10,10,100,20);
		this.add(this.action);
	}


	@Override
	public void arbiterChanged(Arbiter arbiter)
	{
		
		this.action.setText(String.valueOf(this.arbiter.getValue()));
	}



}
