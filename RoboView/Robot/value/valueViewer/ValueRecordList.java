package de.hska.lat.robot.value.valueViewer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hska.lat.robot.value.flow.ValueFlow;


public class ValueRecordList extends ArrayList<ValueFlow>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7753478465497022260L;

	
	Timer sheduler = new Timer();
	
	protected int actualTime;
	
public void record(int startTime)
{
	
	this.actualTime = startTime;
	
	for (ValueFlow valueFlow : ValueRecordList.this)
	{
		valueFlow.clear();
//		valueFlow.start(startTime);
	}
	
	this.sheduler.cancel();
	this.sheduler = new Timer();
	this.sheduler.scheduleAtFixedRate(new ValuePlayerTask(), 10, 10);
}
	


public void stop()
{
	this.sheduler.cancel();
}




class ValuePlayerTask extends TimerTask
{

	
public void run() 
{
	System.out.println(actualTime);
	ValueRecordList.this.actualTime+=10;
	for (ValueFlow valueFlow : ValueRecordList.this)
	{
		valueFlow.record(actualTime);
		//valueFlow.setNext();
		//System.out.println(valueFlow.setNext());
	}
}
	
}	



}
