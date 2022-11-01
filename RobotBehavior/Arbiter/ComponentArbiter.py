class ComponentArbiter:
	def __init__(self):
		self._activeAction = ActionWait()
		pass

"""package de.hska.lat.behavior.arbiter;

import de.hska.lat.behavior.arbiter.action.ActionWait;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public abstract class ComponentArbiter extends Arbiter
{


 

	protected abstract void executeAction(ArbiterAction action);
	protected ArbiterAction activeAction = new ActionWait();


protected ComponentArbiter(int id, int period)
{
	super(id, period);
}


	
@Override
protected void process()
{
	ArbiterAction action = actions.getActualAction();
	
	
	if (this.activeAction != action)
	{
		this.activeAction.interrupt();
		
		this.activeAction = action;
	
		if (action.isInterrupted() || action.isReady())
		//if (this.activeAction.getStatus() != ActionStatus_e.ACTIVE)
		{
			action.initialize();
		}
	}
	
	this.executeAction(action);
	this.notifyChange();
}



public ArbiterAction getActiveAction()
{
	return (this.activeAction);
}
	


@Override
public void receive(ArbiterConnection input, ArbiterAction action)
{
	
	this.setAction(action);

}
	


@Override
public void clear(ArbiterConnection input,  int priority)
{
	
	this.removeAction(priority);

}


}"""
