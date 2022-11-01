package de.hska.lat.comm.remote.parameter;

import java.nio.ByteBuffer;

public class RemoteParameterVideo extends RemoteParameter<RemoteParameterVideo>{
	
	protected static final int BYTE_SIZE = 691200;
	protected byte[] value;
	
	public RemoteParameterVideo(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public byte[] getValue()
	{
		return this.value;

	}
	
	@Override
	public int getBufferSize()
	{
		return(RemoteParameterVideo.BYTE_SIZE);
	}
	
	public void setValue(byte[] data) {
		this.value = data;
	}

	@Override
	public void putData(ByteBuffer data)
	{
		data.put(value);
	}


	@Override
	public int parseFromBuffer(ByteBuffer data, int index)
	{	
		this.value = data.array();
		return RemoteParameterVideo.BYTE_SIZE;
	}

}
