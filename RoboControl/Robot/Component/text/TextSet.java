package de.hska.lat.robot.component.text;

import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.text.protocol.Msg_textFragment;


/**
 * Super class for TSL 2561 Sensor sets.
 * 
 * @author Oktavian Gniot
 * 
 */
public class TextSet extends
		ComponentSet<Text, ComponentProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7626423114169877335L;

	public TextSet(ArrayList<ComponentMetaData> texts, TextProtocol protocol)
	{

		for (ComponentMetaData template : texts)
		{
			this.add(new Text(template, protocol));
		}
	}

	
	
	private void processTextFragment(Msg_textFragment remoteData)
	{
		// TODO Auto-generated method stub
		//remoteData.getParameterCount()
	}
	
	
	@Override
	public boolean decodeMessage(RemoteMessage remoteData)
	{
		if (remoteData instanceof Msg_textFragment)
		{
			processTextFragment((Msg_textFragment)remoteData);
		}
		
		
		return false;
	}





}
