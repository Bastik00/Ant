package de.hska.lat.behavior.arbiter;

import java.util.ArrayList;
import java.util.ListIterator;

public class ArbiterGroup extends Arbiter
{

	
	protected ArbiterGroup(int id)
	{
		super(id);
	}


	protected ArrayList<Arbiter>  arbiters = new ArrayList<Arbiter>(); 
	
	
	@Override
	protected void process()
	{
	}


	
	
	
public void addArbiter(Arbiter arbier)
{
	this.arbiters.add(arbier);
}
	

public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
	return(list);
}





public ListIterator<Arbiter> getArbiters()
{
	return (this.arbiters.listIterator());
}








}
