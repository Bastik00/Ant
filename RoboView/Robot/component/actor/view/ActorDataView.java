package de.hska.lat.robot.component.actor.view;

import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import de.hska.lat.robot.component.actor.Actor;
import de.hska.lat.robot.component.view.ComponentView;


public class ActorDataView<C extends Actor<?,?,?,?>> extends ComponentView  
{


/**
	 * 
	 */
	private static final long serialVersionUID = -671723169597138383L;

	private static final String REFRESH_TEXT	= "refresh";
	private static final String CMD_REFRESH		= "cmdRefresh";
	
	protected C component;
	
	
public ActorDataView(C component)
{
	super(component.getComponentName(), false);
	
	this.component = component;
}



@Override
protected void makePopupMenu()
{
	super.makePopupMenu();

	this.contextMenue.add(new JSeparator());
	this.addMenuItem(this.contextMenue , ActorDataView.REFRESH_TEXT, ActorDataView.CMD_REFRESH);


}


@Override
public void actionPerformed(ActionEvent actionEvent)
{
	String cmd;
	
	cmd = actionEvent.getActionCommand();
	
	if (cmd.equals(ActorDataView.CMD_REFRESH))
	{
		this.component.remote_getValue();
	}	
	else
	{
		super.actionPerformed(actionEvent);
	}
	
	

}


}
