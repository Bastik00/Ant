
ACTION_READY 		= 	1
ACTION_INITIALIZING = 	2
ACTION_ACTIVE 		=	3
ACTION_COMPLETED	=	4
ACTION_FAILED		=	5
ACTION_SUSPENDED	=	6
ACTION_INTERRUPTED	=	7
ACTION_TRMINATED	=	8


class ArbiterAction:
<<<<<<< HEAD
	def __init__(self, priority):
		self._priority = priority
=======
	def __init__(self, arbiter_config):
		self._priority = arbiter_config["priority"]
		self._name = arbiter_config["name"]
>>>>>>> d3f2a202fec6df9918719190d94f40615286a00d
		self._actionChanegListener = list()


	def add_listener(self,listener):
		self._actionChanegListener.append(listener) 

	def remove_listener(self,listener):
		self._actionChanegListener.remove(listener)


<<<<<<< HEAD
	def get_priority(self):
		return self._priority


	def get_name(self):
		return self._name

	def get_status(self):
		return self._status
=======
	def get_name(self):
		return (self._name)
>>>>>>> d3f2a202fec6df9918719190d94f40615286a00d

"""package de.hska.lat.behavior.arbiter.action;

import java.util.LinkedList;


public abstract class ArbiterAction
{

	
	
	
	public enum ActionStatus_e  {READY, INITIALIZING,  ACTIVE, COMPLETED, FAILED, SUSPENDED, INTERRUPTED, TERMINATED}; 

	// READY - Action is ready to be executed.
	// ACTIVE  - Action is activ 
	// COMPLETED - Action was completed
	// SUSPENDED - Action is suspended because an action with higher priority is currently executed
	// ABORTED - Action was aborted by owner behavior
	
	
 
	
	LinkedList<ActionChangeListener> changeListener = new LinkedList<ActionChangeListener>();
	
	protected ActionStatus_e status;
	protected final int priority;
	
	private static final String name = "generic";
	
	
	
public ArbiterAction(int priority)
{
	this.priority = priority;
	this.ready();
}





<<<<<<< HEAD
=======






/**
 * return this action priority. 	
 * @return
 */
public int getPrioryty()
{
	return (this.priority);
}

/**
 * get name of this action
 * @return name of this action
 */
public String getName()
{
	return(ArbiterAction.name);
}



/**
 * get actual status of this action 
 * @return actual status of this node
 */
public ActionStatus_e getStatus()
{
	return (this.status);
}








>>>>>>> d3f2a202fec6df9918719190d94f40615286a00d
/**
 * this action is currently being initialized
 */
public void initialize()
{
	this.status = ActionStatus_e.INITIALIZING;
	
	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionInitialized(this);
	}
}


public boolean isInitializing()
{
	if (this.status == ActionStatus_e.INITIALIZING)
		return(true);
	
	return (false);
}


/**
 * activate an action. this action will be executed now.
 */
public void activate()
{
	this.status = ActionStatus_e.ACTIVE;

	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionActivated(this);
	}
	
}


public boolean isActive()
{
	if (this.status == ActionStatus_e.ACTIVE)
		return(true);
	
	return (false);
}




/**
 * Sets Action on status completed
 */
public void completed()
{
	this.status = ActionStatus_e.COMPLETED;
	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionCompleted(this);
	}
	
}

/**
 * retruns true if action is completed
 * @return
 */
public boolean isCompleted()
{
	return (this.status == ActionStatus_e.COMPLETED);
}




/**
 * suspend this action, this action will not be executed eve if it has the highest priority
 */
public void suspend()
{
	this.status = ActionStatus_e.SUSPENDED;
	
	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionSuspended(this);
	}
}

/**
 * check if this action is suspended
 * @return true if this action is suspended, false if not
 */
public boolean isSuspended()
{
	return (this.status == ActionStatus_e.SUSPENDED);
}


public void ready()
{
	this.status = ActionStatus_e.READY;

	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionReady(this);
	}
}


/**
 * check if this Action is ready to be executed
 * @return true if this action is ready for execution, false if not
 */
public boolean isReady()
{
	if (this.status == ActionStatus_e.READY)
		return(true);
	
	return (false);
}


/**
 * mark this action as interrupted. 
 */
public void interrupt()
{
	this.status = ActionStatus_e.INTERRUPTED;
	
	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionInterrupted(this);
	}
	
}

/**
 * check if this Action is interrupted by other action with higher priority
 * @return true if this action is interrupted, false if not
 */
public boolean isInterrupted()
{
	if (this.status == ActionStatus_e.INTERRUPTED)
		return(true);
	
	return (false);
}




public void terminate()
{
	this.status = ActionStatus_e.TERMINATED;

	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionTerminated(this);
	}
}


public boolean isTerminated()
{
	if (this.status == ActionStatus_e.TERMINATED)
		return(true);
	
	return (false);
}









public void failed()
{
	this.status = ActionStatus_e.FAILED;

	for (ActionChangeListener listener: this.changeListener)
	{
		listener.onActionFailed(this);
	}
}



public boolean isFailed()
{
	if (this.status == ActionStatus_e.FAILED)
		return(true);
	
	return (false);
}





public String toString ()
{
	return(this.getName());
}





}"""
