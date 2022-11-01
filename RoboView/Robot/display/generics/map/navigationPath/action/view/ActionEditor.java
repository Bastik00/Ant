package de.hska.lat.robot.display.generics.map.navigationPath.action.view;

import javax.swing.JPanel;

import de.hska.lat.navigation.path.action.NavPointAction;

public abstract class ActionEditor extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717739840517856924L;

	private static final String NAME = "generic action view";


	
	
public abstract	void displayAction(NavPointAction action);

	
@Override	
public String getName()
{
	return (ActionEditor.NAME);
}
	
}
