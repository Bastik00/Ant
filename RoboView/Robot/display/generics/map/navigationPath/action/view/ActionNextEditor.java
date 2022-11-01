package de.hska.lat.robot.display.generics.map.navigationPath.action.view;

import de.hska.lat.navigation.path.action.NavPointAction;
import de.hska.lat.navigation.path.action.NavPointActionNext;

public class ActionNextEditor extends ActionEditor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3682487907896687017L;

	
	
	private static final String NAME = "pause action view";


	
protected void display(NavPointActionNext action)
{
	
}
	
	
@Override
public void displayAction(NavPointAction action)
{
	if (action instanceof NavPointActionNext)
	{
		this.display((NavPointActionNext) action);
	}
	else
	{
		//display error
	}
}	
			
	
	
@Override	
public String getName()
{
	return (ActionNextEditor.NAME);
}	
	
	
}
