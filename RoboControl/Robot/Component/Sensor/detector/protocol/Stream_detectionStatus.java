package de.hska.lat.robot.component.detector.protocol;

import de.hska.lat.comm.remote.RemoteStream;




public class Stream_detectionStatus extends RemoteStream
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3300298183898023958L;

	protected static final String name = "detectionStatus";
	protected static final String description = "detector status values ";
	
	protected static final int INDEX_CONTACTS = 0;
	
public Stream_detectionStatus()
{
	this.add(new RemoteParameterDetectionStatus());
}
	

public Stream_detectionStatus(int id) 
{
	this();
	this.setId(id);
}




public void setData(boolean...statusArray)
{
	((RemoteParameterDetectionStatus)this.get(INDEX_CONTACTS)).setValues(statusArray);
}


public boolean getStatus(int index)
{
	return (((RemoteParameterDetectionStatus)this.get(INDEX_CONTACTS)).getValue(index));
}





public static Stream_detectionStatus getCommand(int id)
{
	Stream_detectionStatus cmd;
	cmd = new Stream_detectionStatus(id);
	
	return(cmd);
}



public static Stream_detectionStatus getCommand(int command, boolean...status)
{
	Stream_detectionStatus cmd;
	cmd = Stream_detectionStatus.getCommand(command);
	cmd.setData(status);
	
	return(cmd);
}


}
