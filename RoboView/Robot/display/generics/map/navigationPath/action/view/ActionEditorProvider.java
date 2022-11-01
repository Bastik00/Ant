package de.hska.lat.robot.display.generics.map.navigationPath.action.view;

import de.hska.lat.navigation.path.action.NavPointAction;

public class ActionEditorProvider
{

	public final NavPointAction action;
	public final ActionEditor actionEditor;
	
public 	ActionEditorProvider(NavPointAction action, ActionEditor actionEditor)
{
	this.action = action;
	this.actionEditor = actionEditor;
}


public String toString()
{
	return(this.action.getName());
}



}
