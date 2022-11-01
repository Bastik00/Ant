package de.hska.lat.behavior.arbiter;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;


public class SuppressorNode extends Arbiter
{
	protected ArbiterAction action;
	protected ArbiterAction supressionAction;
	
	protected  ArbiterConnection output;
	
	protected  ArbiterConnection actionInput;
	protected  ArbiterConnection suppressorInput;

	protected final Lock lock = new ReentrantLock();
	
	
	
public 	SuppressorNode(int id, String name)
{
	super(id);
	this.actionInput = new ArbiterConnection(this);
//	this.actionInput.setListener(this);
	
	this.suppressorInput = new ArbiterConnection(this);
	//this.suppressorInput.setListener(this);
	
	this.name = name;
	

}


public void connectOutput(ArbiterConnection output)
{
	this.output = output;
}



/**
 * get this node action input. This input will always be overrides by an existing suppresion action
 * @return action input
 */
public ArbiterConnection getActionInput()
{
	return(this.actionInput);
}


/**
 * get this node suppressor Input. The suppressor action always override action imput 
 * @return suppressor input
 */
public ArbiterConnection getSuppressorInput()
{
	return(this.suppressorInput);
}





/**
 * Suppress actual input with suppressor action
 */

protected void suppress(ArbiterAction action)
{
	this.supressionAction = action;
	
	System.out.println("supress !!!!");
	
	if (this.action!=null)
	{
	//	this.output.clear(this.action.getPrioryty());
		this.action.interrupt();
	}
	
	this.output.receive(action);
	
}


/**
 * set an action. if there is no suppressor action, send action to output. Otherwise action is suppressed. 
 * @param action new action
 */
protected void setAction(ArbiterAction action)
{
	this.action = action;
	
	System.out.println("******************  and action !!!");
	
	if (this.supressionAction==null)
	{
		this.output.receive(action);
	}

}




@Override
public void receive(ArbiterConnection input, ArbiterAction action)
{

	this.lock.lock();
	
	if (input == this.actionInput)
	{
		if (action != this.action)
		{
			this.setAction(action);
		}
	}
	else if (input == this.suppressorInput)
	{
		if (action != this.supressionAction)
		{
			this.suppress(action);
		}
	}
	
	this.lock.unlock();
	this.notifyChange();
}




@Override
public void clear(ArbiterConnection input,  int priority)
{

	this.lock.lock();
	
	if (input == this.suppressorInput)
	{
		if (this.action != null)
		{
			if (this.supressionAction!=null)
			{
			this.output.clear(priority);
			}
			this.output.receive(this.action);
		}
		else
		{
			this.output.clear(priority);
		}
		
		this.supressionAction=null;
		
	}
	
	else if (input == this.actionInput)
	{
		if (this.action!=null)
		{
			
			if (this.supressionAction==null)
			{
				this.output.clear(priority);
			}

			this.action=null;
	
		}
		

	}
	
	this.lock.unlock();
	
	this.notifyChange();
}


@Override
protected void process()
{
	// TODO Auto-generated method stub
	
}


public ArbiterAction getAction()
{
	 return(this.action);
	
}


public ArbiterAction getSuppressorAction()
{
	 return(this.supressionAction);
	
}



public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
	list.add(this.output);
	
	return(list);
}




}
