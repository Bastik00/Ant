package de.hska.lat.robot.display.generics.map.navigationPath.action.view;

import de.hska.lat.navigation.path.action.NavPointAction;
import de.hska.lat.navigation.path.action.NavPointActionNext;
import de.hska.lat.navigation.path.action.NavPointActionPause;

public enum DefaultActionsEditors
{
	SET_PAUSE(new NavPointActionNext(), new ActionNextEditor()),
	SET_NEXT(new NavPointActionPause(),new ActionPauseEditor());
	
	
	

	
	private final ActionEditor editor;
	private final NavPointAction action;
	
	
	
private DefaultActionsEditors(NavPointAction action, ActionEditor editor)
{
	this.action = action;
	this.editor = editor;	
}

/**
 * 
 * @return 
 */
public ActionEditorProvider getEditorProvider()
{
	return(new ActionEditorProvider(this.action, this.editor));
}




public ActionEditor getEditor()
{
	return (this.editor);
}




public NavPointAction getAction()
{
	return (this.action);
}



public String toString()
{
	return(this.action.getName());
}

}
