package de.hska.lat.robot.component.sensor.tsl2591.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;




/**
 * 
 * @author Oktavian Gniot
 *
 *command containing new settings (gradient, offset, maximal measurable distance) for a GP2 sensor
 */

public class Msg_Tsl2591Settings extends RemoteMessage
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 8493244605338334808L;
	
	
	private static final int INDEX_SENSOR 			= 0;
	private static final int INDEX_USER_DEFINED		= 1;

	
	
	protected static final String name = "templateSettings";
	protected static final String description = "settings for a template component";
	
	

public Msg_Tsl2591Settings() 
{
	this.add(new RemoteParameterUint8("index","template component  index"));
	this.add(new RemoteParameterUint8("user defined","component user defined value 1"));

}





	
	
public Msg_Tsl2591Settings(int command) 
{
	this();
	this.setId(command);
}


@Override
public String getName() 
{
	return(Msg_Tsl2591Settings.name);
}


@Override
public String getDescription() 
{
	return(Msg_Tsl2591Settings.description);
}




public void setData(int index, int userDefined)
{
	(( RemoteParameterUint8) this.get(Msg_Tsl2591Settings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Msg_Tsl2591Settings.INDEX_USER_DEFINED)).setValue(userDefined);


}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Msg_Tsl2591Settings.INDEX_SENSOR)).getValue());
}

public int getSettingsValue()
{
	return((( RemoteParameterUint8) this.get(Msg_Tsl2591Settings.INDEX_USER_DEFINED)).getValue());
}




public static Msg_Tsl2591Settings getCommand(int id)
{
	Msg_Tsl2591Settings cmd;
	cmd = new Msg_Tsl2591Settings(id);
	
	return(cmd);
}







public static Msg_Tsl2591Settings getCommand(int command, int index,
		int userDefined)
{
	
	Msg_Tsl2591Settings cmd;
	cmd = Msg_Tsl2591Settings.getCommand(command);
	cmd.setData(index, userDefined);
	
	return(cmd);
	
	
}




}

