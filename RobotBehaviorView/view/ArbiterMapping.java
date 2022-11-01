package de.hska.lat.behavior.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import de.hska.lat.behavior.arbiter.Arbiter;
import de.hska.lat.behavior.arbiter.ArbiterConnection;

public class ArbiterMapping {
	
	private HashMap<Arbiter, ArrayList<Arbiter>> arbiterMap = null;
	
	public HashMap<Arbiter, ArrayList<Arbiter>> getArbiterMap() {
		return arbiterMap;
	}

	public ArbiterMapping(ListIterator<Arbiter> listIterator){
		createMappping(listIterator);
	}
	
	public void createMappping(ListIterator<Arbiter> arbiters){
		arbiterMap = new HashMap<Arbiter, ArrayList<Arbiter>>();
		
		ListIterator<Arbiter> arbiterList;
		for (arbiterList = arbiters ; arbiterList.hasNext(); ) 
		{
		    Arbiter arbiter = arbiterList.next();
		    addOutputs(arbiter);
		}
		  
//		    if (arbiter instanceof ArbiterGroup) {
//		    	 System.out.print("GROUP: ");
//		    	 
//		    	 ListIterator<Arbiter> arbiterListInGroup;
//		    	 for (arbiterListInGroup = ((ArbiterGroup) arbiter).getArbiters() ; arbiterListInGroup.hasNext(); ) 
//		    		{
//		    		 Arbiter arbiter2 = arbiterListInGroup.next();
//		    		  System.out.print(arbiter2.getId() + " ,  ");
////		    		  for (ArbiterConnection connection : arbiter2.getAllOutputs()) {
////		    			  	if(connection.getListener() != null){
////		    			  		Arbiter preArbiter = (Arbiter)connection.getListener();
////			    		    	 System.out.print(preArbiter.getId() + " ,  ");
////		    			  	}
////		    			}
//		    		}
//		    	 System.out.print("GROUP END ");
//			}
	}
	
	private void addOutputs(Arbiter sourceArbiter){
		 ArrayList<Arbiter> out = new ArrayList<Arbiter>();
		  for (ArbiterConnection connection : sourceArbiter.getAllOutputs()) {
			
			  try{
		    	Arbiter postArbiter = (Arbiter)connection.getListener();
		    	out.add(postArbiter);
			  }
			  catch(Exception e)
			  {
				  System.out.println();
			  }
		    	
			  }
			
		  
		  arbiterMap.put(sourceArbiter, out);

	}
}
