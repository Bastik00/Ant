package de.hska.lat.ant.arbiter.expresion;

import java.util.ArrayList;


import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.arbiter.ComponentArbiter;
import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public class AntExpressionArbiter extends ComponentArbiter
{

	protected AntExpressionArbiter(int id)
	{
		super(id, 100);
		// TODO Auto-generated constructor stub
		
		this.start();
	}

	@Override
	protected void executeAction(ArbiterAction action)
	{
		// TODO Auto-generated method stub
		
	}

	
	public  ArrayList<ArbiterConnection> getAllOutputs()
	{
		ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
				
		return(list);
	}
	
}
