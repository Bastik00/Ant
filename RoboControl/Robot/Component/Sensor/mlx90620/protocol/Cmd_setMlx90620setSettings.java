package de.hska.lat.robot.component.sensor.mlx90620.protocol;
import de.hska.lat.comm.remote.RemoteCommand;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;


public class Cmd_setMlx90620setSettings extends RemoteCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5236461611026679580L;
	
	private static final int INDEX_SENSOR 				= 0;
	private static final int INDEX_AMBIENT_REFRESH_RATE 	= 1;
	private static final int INDEX_ARRAY_FRAME_RATE 	= 2;
	
	
	protected static final String name = "setMlx90620Settings";
	protected static final String description = "set settings for a MLX 90620  ir array sensor";
	
	
	
	
public Cmd_setMlx90620setSettings()
{
	this.add(new RemoteParameterUint8("index", "Sensor index"));
	this.add(new RemoteParameterUint8("configuration register", "configuration register (0x92) of MLX90620"));
	this.add(new RemoteParameterUint8("configuration register", "configuration register (0x92) of MLX90620"));
}

	
	
@Override
public String getName() 
{
	return(Cmd_setMlx90620setSettings.name);
}


@Override
public String getDescription() 
{
	return(Cmd_setMlx90620setSettings.description);
}

	
	
	public Cmd_setMlx90620setSettings(int command){
		this();
		this.setId(command);
	}
	
	
	
	
public void setData(int index, int ambientRefreshRate, int irFrameRate)
{
	(( RemoteParameterUint8) this.get(Cmd_setMlx90620setSettings.INDEX_SENSOR)).setValue(index);
	(( RemoteParameterUint8) this.get(Cmd_setMlx90620setSettings.INDEX_AMBIENT_REFRESH_RATE)).setValue(ambientRefreshRate);
	(( RemoteParameterUint8) this.get(Cmd_setMlx90620setSettings.INDEX_ARRAY_FRAME_RATE)).setValue(irFrameRate);
}
	
	
public int getIndex()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMlx90620setSettings.INDEX_SENSOR)).getValue());
}


public int getAmbientRefreshRate()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMlx90620setSettings.INDEX_AMBIENT_REFRESH_RATE)).getValue());
}


public int getArrayFrameRate()
{
	return((( RemoteParameterUint8) this.get(Cmd_setMlx90620setSettings.INDEX_ARRAY_FRAME_RATE)).getValue());
}



	
public static Cmd_setMlx90620setSettings getCommand(int command,int index, int ambientRefreshRate, int irFrameRate)

{
		Cmd_setMlx90620setSettings cmd;
		cmd = new Cmd_setMlx90620setSettings(command);
		cmd.setData(index, ambientRefreshRate, irFrameRate);
		return cmd;
}

}
