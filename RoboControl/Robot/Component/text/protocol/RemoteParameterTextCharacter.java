package de.hska.lat.robot.component.text.protocol;

import java.nio.ByteBuffer;

import de.hska.lat.comm.remote.parameter.RemoteParameter;

public class RemoteParameterTextCharacter extends RemoteParameter<RemoteParameterTextCharacter>
{

	protected static final int BYTE_SIZE = 2;
	protected char character;
	
	
	
public RemoteParameterTextCharacter(String name, String description)
{
	super(name, description);
}


public void setCharacter(char character)
{

	this.character = character;	
}


public char getCharacter()
{
	return(this.character);

}






@Override
public int getBufferSize()
{
	return(RemoteParameterTextCharacter.BYTE_SIZE);
}



@Override
public void putData(ByteBuffer data)
{
	data.putChar(this.character);
}


@Override
public int parseFromBuffer(ByteBuffer data, int index)
{

	this.character = data.getChar(index);
	
	return(RemoteParameterTextCharacter.BYTE_SIZE);
}


}
