package de.hska.lat.robot.component.battery.protocol;

import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;






/**
 * command that ping a device, device returns ping response message  
 * @author Oktavian Gniot
 *
 */
public class Alert_batteryCritical extends RemoteCommand
{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8615954259563545343L;


	private static final int INDEX_BATTERY = 0;
	

	protected static final String name = "batteryCritical";
	protected static final String description = "batery level is critical ";
	
	
public Alert_batteryCritical() 
{
	this.add(new RemoteParameterUint8("index","index of battery"));
}



public Alert_batteryCritical(int command) 
{
	this();
	this.setId(command);
}


public void setData(int index)
{
	(( RemoteParameterUint8) this.get(Alert_batteryCritical.INDEX_BATTERY)).setValue(index);
}



public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Alert_batteryCritical.INDEX_BATTERY)).getValue());
}



@Override
public String getName() 
{
	return(Alert_batteryCritical.name);
}


@Override
public String getDescription() 
{
	return(Alert_batteryCritical.description);
}



public static Alert_batteryCritical getCommand(int command)
{
	Alert_batteryCritical cmd;
	cmd = new Alert_batteryCritical(command);
	
	return(cmd);
}

public static Alert_batteryCritical getCommand(int command,int index)
{
	Alert_batteryCritical cmd;
	cmd = Alert_batteryCritical.getCommand(command);
	cmd.setData(index);
	
	return(cmd);
}


}
