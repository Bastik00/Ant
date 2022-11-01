package de.hska.lat.robot.device.device.remoteProcessor;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.RemoteData;
import de.hska.lat.comm.remote.RemoteException;
import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.RemoteStream;



public interface RemoteDecoder
{

	public boolean decode(RemoteData remoteData);
	public boolean decodeCommand(RemoteCommand remoteData);
	public boolean decodeMessage(RemoteMessage remoteData);
	public boolean decodeStream(RemoteStream remoteData);
	public boolean decodeException(RemoteException remoteData);
	
}
