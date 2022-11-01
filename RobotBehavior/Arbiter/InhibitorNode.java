package de.hska.lat.behavior.arbiter;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class InhibitorNode extends Arbiter
{

	protected ArbiterAction action;
	protected boolean inhibiting;
	
	protected  ArbiterConnection output;
	
	protected  ArbiterConnection inhibitorInput;
	
	protected final Lock lock = new ReentrantLock();
	
	
	public 	InhibitorNode(int id, String name)
	{
		super(id);
		this.input = new ArbiterConnection(this);
	//	this.input.setListener(this);
		
		this.inhibitorInput = new ArbiterConnection(this);
	//	this.inhibitorInput.setListener(this);
		
		this.name = name;

	}

	

protected void setAction(ArbiterAction action)
{
	this.action = action;
	
	System.out.println("******************  and action !!!");
	
	if (this.inhibiting==false)
	{
		this.output.receive(action);
	}

}	
	
	
protected void inhibit()
{
	this.inhibiting = true;
	if (action != null)
	{
		this.action.interrupt();
		this.output.clear(this.action.getPrioryty());
	}
}



@Override
public void receive(ArbiterConnection input, ArbiterAction action)
{
	
	this.lock.lock();

	if (input == this.input)
	{
		if (action != this.action)
		{
			this.setAction(action);
		}
	}
	else if (input == this.inhibitorInput)
	{
			this.inhibit();
	}
	
	this.lock.unlock();
}




@Override
public void clear(ArbiterConnection input,  int priority)
{

	this.lock.lock();
	
	
	if (input == this.input)
	{
		this.output.clear(priority);
		this.action = null;
	}
	else if (input == this.inhibitorInput)
	{
		if ( this.action != null)
		{
			this.output.receive(this.action);
			this.inhibiting = false;
		}

	}
	
	this.lock.unlock();
	
		
}



	@Override
	protected void process()
	{
		// TODO Auto-generated method stub
		
	}



	public void connectOutput(ArbiterConnection output)
	{
		this.output = output;
	}



	public ArbiterConnection getInhibitorInput()
	{
		return (this.inhibitorInput);
	}
	
	
	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.output);
		
		return(list);
	}
	
		
		
	
}
