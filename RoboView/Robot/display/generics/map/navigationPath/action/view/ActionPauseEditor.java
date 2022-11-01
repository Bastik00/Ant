package de.hska.lat.robot.display.generics.map.navigationPath.action.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hska.lat.navigation.path.action.NavPointAction;
import de.hska.lat.navigation.path.action.NavPointActionPause;

public class ActionPauseEditor extends ActionEditor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3682487907896687017L;

	
	private static final String NAME = "pause action view";

	protected JSpinner pauseSpinner;


	private NavPointActionPause navPointAction;

	
	
	
public ActionPauseEditor()
{
	JLabel description;

//	this.pausePanel.setLayout(null);
	
	//this.pausePanel.setBounds(10,10,100,100);
	
	//this.pausePanel.setPreferredSize(new Dimension(100,40));
	
	description = new JLabel("sec");
	this.add(description,BorderLayout.EAST);
	
	this.pauseSpinner = new JSpinner();
	this.pauseSpinner.setPreferredSize(new Dimension(100,30));
	this.add(this.pauseSpinner,BorderLayout.WEST);

	
}	
	

protected void display(NavPointActionPause action)
{
	this.navPointAction = action;
	
	this.pauseSpinner.addChangeListener(new ChangeListener(){
		@Override
		public void stateChanged(ChangeEvent e) {
			setActionValue();
		}
	});
}

	
@Override
public void displayAction(NavPointAction action)
{
	if (action instanceof NavPointActionPause)
	{
		System.out.println("WHOO");
		this.display((NavPointActionPause) action);
	}
	else
	{
		System.out.println("FUCKUP!");
		//display error
	}
}

public void setActionValue() 
{
	if(navPointAction != null)
	{
		navPointAction.setTime((int) pauseSpinner.getValue());
	}
}
	


@Override
public String getName()
{
	return (ActionPauseEditor.NAME);
}







	
}
