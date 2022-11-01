package de.hska.lat.behavior.arbiter;

import de.hska.lat.behavior.arbiter.action.ArbiterAction;

public interface ArbiterInputNotifer
{
	/**
	 * receive an action
	 * @param input  
	 * @param action action
	 */
	void receive(ArbiterConnection input, ArbiterAction action);

	void clear(ArbiterConnection input, int priority);
	
//	void override(ArbiterInput input, ArbiterAction action);
	

}
