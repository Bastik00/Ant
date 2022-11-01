

from RobotBehavior.Arbiter.Action.ActionWait import ActionWait


class ArbiterActionList:
	def __init__(self):
		self._list = list()
		self._list.append(ActionWait())



	def get_action(self, index):
		if (len(self._list) < index):
			return self._list[index]
		
		return None
		


	def put(self ):


"""package de.hska.lat.behavior.arbiter;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;

import de.hska.lat.behavior.arbiter.action.ActionWait;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class ArbiterActionList extends ConcurrentSkipListMap<Integer ,ArbiterAction>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2559906231196062953L;

	
	public ActionWait defaultAction = new ActionWait();
	
public ArbiterActionList()
{
	this.put(this.defaultAction);
}

	
public ArbiterAction put(ArbiterAction action)
{
	ArbiterAction previousAction;
	
	previousAction = super.put(action.getPrioryty(), action);
	
	return(previousAction);
}



public void remove(ArbiterAction action)
{
	ArbiterAction action2;
	
	action2 = super.get(action.getPrioryty());
	if (action2 == action)
	{
		super.remove(action.getPrioryty());
	}
}







/**
 * return actual action. actual action is the action with higest priority
 * @return actual action
 */
public ArbiterAction getActualAction()
{
	ArbiterAction action;
	

	if (this.size()==0)
		return(this.defaultAction);
	
	action = this.firstEntry().getValue();
	
	if (action == null)
	{
		return(this.defaultAction);
	}
	
	
	while (action.isSuspended() || action.isTerminated())
	{
		Integer key = this.lowerKey(action.getPrioryty());

		
		if (key == null)
		{
			return(this.defaultAction);
		}
		
		action = this.get(key);
	}
	
	return (action);

}
	
	



}"""
