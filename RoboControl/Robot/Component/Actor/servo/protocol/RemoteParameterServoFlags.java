package de.hska.lat.robot.component.actor.servo.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterServoFlags extends RemoteParameter<RemoteParameterServoFlags>
{

	protected static final int BYTE_SIZE = 1;
	protected int value;
	
	private boolean reverse;
	private boolean on;
	private boolean forceFeedbackOn;
	private boolean positionFeedbackOn;
	
	private static final int FLAG_REVERSE				= 0;
	private static final int FLAG_ON					= 1;
	private static final int FLAG_FORCEFEEDBACK_ON 		= 2;
	private static final int FLAG_POSITIONFEEDBACK_ON 	= 3;	
	
	private static final String name = "servo flags";
	private static final String description = "servo flags";	
	
	
	public RemoteParameterServoFlags()
	{
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterServoFlags.BYTE_SIZE);
	}

	
	public void setReverse(boolean status)
	{
		this.reverse = status;	
	}
	
	public void setOn(boolean status)
	{
		this.on = status;	
	}
	
	
	public boolean isReverse()
	{
		return(this.reverse);
	}
	
	
	public boolean isOn()
	{
		return(this.on);
	}
	
	public boolean forceFeedbackisOn(){
		return this.forceFeedbackOn;
	}
	
	public boolean positionFeedbackisOn(){
		return this.positionFeedbackOn;
	}
	
	
	
	@Override
	public void putData(ByteBuffer data)
	{
		byte flags;
		
		flags = 0;
		
		
		
		if (this.reverse)
		{
			flags |= (1<<FLAG_REVERSE);	
		}
		  
		if (this.on)
		{
			flags |= (1<<FLAG_ON);	
		}
		
		if (this.forceFeedbackOn)
		{
			flags |= (1<<FLAG_FORCEFEEDBACK_ON);	
		}
		
		if (this.positionFeedbackOn)
		{
			flags |= (1<<FLAG_POSITIONFEEDBACK_ON);	
		}
		
		data.put(flags);
	}



	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{
		
		byte flags;
		
		
		flags = data.get(index);
		
		if ((flags & (1<<FLAG_REVERSE))>0)
		{
			this.reverse = true;
		}
		
		if ((flags & (1<<FLAG_ON))>0)
		{
			this.on = true;
		}
		
		if ((flags & (1<<FLAG_FORCEFEEDBACK_ON))>0)
		{
			this.forceFeedbackOn = true;
		}
		
		if ((flags & (1<<FLAG_POSITIONFEEDBACK_ON))>0)
		{
			this.positionFeedbackOn = true;
		}
		
		
		return(RemoteParameterServoFlags.BYTE_SIZE);
	}



	
@Override
public String getAsString(boolean description)
{
	String returnString ="";
	
	if (description)
	{
		returnString+= RemoteParameterServoFlags.name+" ";
		
		returnString+= "on="+ this.on;
		returnString+= ", reverse="+this.reverse;
		returnString+= ", forcefeedback="+this.forceFeedbackOn;
		returnString+= ", positionfeedback="+this.positionFeedbackOn;
	}
	else
	{
		returnString+= this.on+", ";
		returnString+= this.reverse;
		returnString+= this.forceFeedbackOn;
		returnString+= this.positionFeedbackOn;
		
	}
		
	return(returnString);
}

	
}
