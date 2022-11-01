
from RobotBehavior.Arbiter.Action.ArbiterAction import ArbiterAction

ACTION_WAIT_PRIORITY = 100

class ActionWait(ArbiterAction):
	def __init__(self):
		super().__init__(ACTION_WAIT_PRIORITY)


"""
package de.hska.lat.behavior.arbiter.action;

public class ActionWait extends ArbiterAction
{

	
	private static final String name = "wait";
	
public ActionWait()
{
	super(Integer.MAX_VALUE);
}
	

@Override
public String getName()
{
	return(ActionWait.name);
}

}"""
