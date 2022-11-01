package de.hska.lat.robot.component.text.protocol;

import de.hska.lat.comm.remote.RemoteDataPacket;
import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;



/**
 * 
 * @author Oktavian Gniot
 *
 * Message containing fragment of a text
 */

public class Msg_textFragment extends RemoteMessage
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 8493244605338334808L;
	
	
	private static final int INDEX_TEXT				= 0;
	private static final int INDEX_START_CHAR		= 1;
	private static final int INDEX_FIRST_CHAR		= 2;

	
	
	protected static final String name = "textFragment";
	protected static final String description = "message containing fragment of a text";
	
	

public Msg_textFragment() 
{
	this.add(new RemoteParameterUint8("index","text index"));
	this.add(new RemoteParameterUint16("char index","index of the first char in message"));

}





	
	
public Msg_textFragment(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_textFragment.name);
}


@Override
public String getDescription() 
{
	return(Msg_textFragment.description);
}




public void setData(int index, int startChar)
{
	(( RemoteParameterUint8) this.get(Msg_textFragment.INDEX_TEXT)).setValue(index);
	(( RemoteParameterUint16) this.get(Msg_textFragment.INDEX_START_CHAR)).setValue(startChar);


}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_textFragment.INDEX_TEXT)).getValue());
}

public int getStartIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_textFragment.INDEX_START_CHAR)).getValue());
}








public void parseDataPacketData(RemoteDataPacket packet)
{
	
	System.out.println("size = dataPacket.getDataSize(); "+ packet.getDataSize());
	
	int elements;
	int index;
	
	elements = packet.getDataSize()-3;
	elements/=2;
	
	for (index =0; index<elements;index++)
	{
		this.add(new RemoteParameterTextCharacter("character"+index,"character"));
	}
	
	super.parseDataPacketData(packet);
	
	

}



public float[] getFragment()
{
	int index;
	int dataIndex=0;

	float [] data = new float [this.size()-Msg_textFragment.INDEX_FIRST_CHAR];
	
	for (index=Msg_textFragment.INDEX_FIRST_CHAR; index<this.size(); index++)
	{
		data[dataIndex++]= ((RemoteParameterTextCharacter) this.get(index)).getCharacter();	
	}
	
	return(data);
}







public static Msg_textFragment getCommand(int id)
{
	Msg_textFragment cmd;
	cmd = new Msg_textFragment(id);
	
	return(cmd);
}







public static Msg_textFragment getCommand(int command, int index,
		int userDefined)
{
	
	Msg_textFragment cmd;
	cmd = Msg_textFragment.getCommand(command);
	cmd.setData(index, userDefined);
	
	return(cmd);
	
	
}




}

