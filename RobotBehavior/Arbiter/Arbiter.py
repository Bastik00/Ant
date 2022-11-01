from time import sleep
import threading




class Arbiter:
	def __init__(self):
		self._period = 0
		self.running = False
		self._thread = threading.Thread(target=self.run, daemon=True)
		

	def init(peroid):

		pass
	


	def start(self):
		if self.running == False:
			if (self._period > 0):
				self.running = True
				self._thread.start()
        

	def run(self):
		sleep(self._period)
		

	def stop(self):
		self.running = False
		self._thread.join()



	def get_time_base(self):
		return self._period


"""
package de.hska.lat.behavior.arbiter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hska.lat.behavior.arbiter.action.ActionChangeListener;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;
import de.hska.lat.behavior.control.BehaviorControl;

public abstract class Arbiter implements ArbiterInputNotifer , ActionChangeListener
{

	public enum ArbiterStatus_e  {READY,  ACTIVE, ERROR };

	protected ArbiterConnection input;
	
	protected ArbiterActionList actions = new ArbiterActionList();
	
	protected int period;
	
	protected Timer sheduler;
	protected String name = "generic";
	
	protected final int id;
	
	protected ArrayList<ArbiterChangeNotifier> changeListener = new ArrayList<ArbiterChangeNotifier>();
	
	protected ArbiterStatus_e status;
	
	
	
protected Arbiter(int id)
{
	this.id = id;
	this.init(0);
}


protected Arbiter(int id, int period)
{
	this.id = id;
	this.init(period);
}





public void init(int period)
{


	
}

/**
 * start this arbiter
 */
public void start()
{
	if (this.period>0)
	{
		this.sheduler.scheduleAtFixedRate(new ArbiterTask(), this.period, this.period);	
	}
}

public void stop()
{
	this.sheduler.cancel();
}


<<<<<<< HEAD
public int getTimeBase()
{
	return(this.period);
}

=======
>>>>>>> d3f2a202fec6df9918719190d94f40615286a00d


public void addChangeListener(ArbiterChangeNotifier listener)
{
	this.changeListener.add(listener);
}




public void notifyChange()
{
	for (  ArbiterChangeNotifier notifier : changeListener)
	{
		notifier.arbiterChanged(this);
	}
}



public void notifyStatusChange()
{
	for (  ArbiterChangeNotifier notifier : changeListener)
	{
		notifier.statusChanged(this);
	}
}



/**
 * set Arbiter to Active Mode - a command is actualy executed.
 */
protected void setActive()
{
	if (this.status!=Arbiter.ArbiterStatus_e.ACTIVE)
	{
		this.status=Arbiter.ArbiterStatus_e.ACTIVE;
		this.notifyStatusChange();
	}
}

public boolean isActive()
{
	if (this.status==Arbiter.ArbiterStatus_e.ACTIVE)
	{
		return(true);
	}
	return(false);
}


/**
 * Set arbiter to ready mode - no command is actualy executed
 */
protected void setReady()
{
	if (this.status!=Arbiter.ArbiterStatus_e.READY)
	{
		this.status=Arbiter.ArbiterStatus_e.READY;
		this.notifyStatusChange();
	}
}




public boolean isReady()
{
	if (this.status==Arbiter.ArbiterStatus_e.READY)
	{
		return(true);
	}
	return(false);
}



/**
 * Set arbiter to error Mode. 
 */

protected void setError()
{
	if (this.status!=Arbiter.ArbiterStatus_e.ERROR)
	{
		this.status=Arbiter.ArbiterStatus_e.ERROR;
		this.notifyStatusChange();
	}
}


public boolean isInErrorMode()
{
	if (this.status==Arbiter.ArbiterStatus_e.ERROR)
	{
		return(true);
	}
	return(false);
}






/**
 * set an action for this arbiter. If this action has the highest priority, it will be executed. Otherwise it will be suspended
 * @param action
 */

protected void setAction(ArbiterAction action)
{
	
	ArbiterAction previousAction = this.actions.put(action);
		
	if (previousAction!=null && !previousAction.isCompleted())
	{
		previousAction.terminate();
	}
	
	if (action != this.actions.getActualAction())
	{
		action.interrupt();
	}
	
	
}



protected void removeAction(int priority)
{
	this.actions.remove(priority);
}



protected void removeAction(ArbiterAction action)
{
	this.actions.remove(action);
	
}



/**
 * get this arbiters name
 * @return name of this arbiter
 */
public String getName()
{
	return(name);
}

	
@Override
public void receive(ArbiterConnection input, ArbiterAction action)
{
	// TODO Auto-generated method stub
	
}

	
@Override
public void clear(ArbiterConnection input,  int priority)
{
	this.actions.remove(priority);
}





public ArbiterConnection getInput()
{
	return(this.input);
}
	
	
	
public abstract ArrayList<ArbiterConnection> getAllOutputs();

	




protected abstract void process();



public ArbiterActionList getActions()
{
	 return(this.actions); 
}


class ArbiterTask extends TimerTask
{


    @Override
	public void run() 
    {

   		Arbiter.this.process();

    //	Behavior.this.informations.capture();
    //	Behavior.this.behave();


    }
}






public ArrayList<BehaviorControl> getControls()
{
	
	 return( new ArrayList<BehaviorControl>());
}


public int getId()
{
	return (this.id);
}





public void onActionInitialized(ArbiterAction action)
{
	
}

public void onActionActivated(ArbiterAction action)
{
	
}

public void onActionReady(ArbiterAction action)
{
	
}

public void onActionCompleted(ArbiterAction action)
{
	
}



public void onActionInterrupted(ArbiterAction action)
{
	
}

public void onActionSuspended(ArbiterAction action)
{
	
}

public void onActionFailed(ArbiterAction action)
{
	
}

public void onActionTerminated(ArbiterAction action)
{
	action.removeListener(this);
}







}
"""