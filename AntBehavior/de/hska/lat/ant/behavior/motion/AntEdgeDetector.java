package de.hska.lat.ant.behavior.motion;

import java.util.ArrayList;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.behavior.arbiter.ArbiterConnection;
import de.hska.lat.behavior.behavior.Behavior;

public class AntEdgeDetector extends Behavior
{

	public AntEdgeDetector(Ant ant)
	{
		super(ant, AntBehavoirPriority.EDGE_DETECTOR_PRIORITY.getPriority(), 100);

		
	}

	
	
	@Override
	protected void behave()
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public ArrayList<ArbiterConnection> getAllOutputs()
	{
		return (new ArrayList<ArbiterConnection>());
	}

}
