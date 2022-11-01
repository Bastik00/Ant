package de.hska.lat.robot.display.generics.map.navigationPath;


import javax.swing.JComboBox;

import de.hska.lat.navigation.path.action.NavPointAction;

import de.hska.lat.robot.display.generics.map.navigationPath.action.view.ActionEditorProvider;

public class NavigationActionSelector extends JComboBox<ActionEditorProvider>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6428049422660967887L;
	protected NavPointAction navPointAction;
//	protected ActionEditor editor;

	
	
	
/**
 * select & display an editor for the given action 	
 * @param action
 */
public ActionEditorProvider getActionEditor(NavPointAction action)
{
	for(int i = 0; i < this.getItemCount(); i++)
	{
		if(this.getItemAt(i).action.equals(action))
		{
			this.navPointAction = action;
			return this.getItemAt(i);
		}
	}
	return null;	
}


public NavPointAction getNavPointAction()
{
	return ((ActionEditorProvider)this.getSelectedItem()).action;
}

	

	

	
	
}
