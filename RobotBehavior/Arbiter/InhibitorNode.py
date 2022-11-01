
from RobotBehavior.Arbiter.Action.ActionWait import ActionWait
from RobotBehavior.Arbiter.Arbiter import Arbiter
from RobotBehavior.Arbiter.ArbiterConnection import ArbiterConnection



class InhibitorNode(Arbiter):
	
	def __init__(self, id, name):
		super().__init__(id, name)
		self._action = None
		self._output = ArbiterConnection()
		self._inhibitor_input = ArbiterConnection()
		self._inihibiting = False


	def set_action(self, action):
		if (self._inihibiting == False):
			self._output.receive(action)
		


	def inhibit(self):
		self._inihibiting == True
		if (self._action != None):
			self._action.interrupt()
			self._output.clear(self._action.getPriority())






	def process(self):
		pass



	def connect_output(self, output):
		self._output = output


	def get_inhibitor_input(self):
		return self._inhibitor_input

	
	
	def receive():
			
		pass


	"""
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
	"""



	def clear():
		pass
	"""
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
	"""	


"""package de.hska.lat.behavior.arbiter;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class InhibitorNode extends Arbiter
{

	
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

	

p
	



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





	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
		list.add(this.output);
		
		return(list);
	}
	
		
		
	
}"""
