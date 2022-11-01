package de.hska.lat.ant.behavior.lightAvoidance;

import javax.swing.JLabel;



import de.hska.lat.ant.behavior.head.light.LightFollower;
import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.view.ArbiterViewer;
import de.hska.lat.behavior.behavior.RobotBehavior;
import de.hska.lat.robot.RobotSettings;

public class LightAvoidanceBehaviorViewer  extends ArbiterViewer<LightFollower>
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848034897696851698L;
	
	protected JLabel action;
	
	protected HeadLightPanel lightPanel;
	
	public LightAvoidanceBehaviorViewer(RobotBehavior<?> behavior, LightFollower arbiter, RobotSettings settings)
	{
		super(behavior, arbiter, settings);
		
		this.arbiter.addChangeListener(this);
		
		this.setSize(150,100);
		this.build();
		this.setLayout(null);
	}
		

	protected void build()
	{
	/*	this.action  = new JLabel("test");
		this.action.setBounds (10,10,100,20);
		this.add(this.action);
	*/	
		this.lightPanel = new HeadLightPanel(this.arbiter);
		this.lightPanel.setBounds (10,20,130,60);
		this.add(this.lightPanel);
		
		
	}


	@Override
	public void arbiterChanged(Arbiter arbiter)
	{
		
	//	this.action.setText(String.valueOf(this.arbiter.getValue()));
	}



}
