package de.hska.lat.robot.component.sensor.mlx90620.protocol;
import de.hska.lat.comm.remote.RemoteCommand;

import de.hska.lat.comm.remote.parameter.RemoteParameterInt8;

public class Cmd_getAmbientTemperature extends RemoteCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5236461611026679580L;
	
	
	public Cmd_getAmbientTemperature(){
		this.add(new RemoteParameterInt8("index", "Sensor index"));
	}
	
	public Cmd_getAmbientTemperature(int command){
		this();
		this.setId(command);
	}
	
	public static Cmd_getAmbientTemperature getCommand(int command){
		Cmd_getAmbientTemperature cmd;
		cmd = new Cmd_getAmbientTemperature(command);
		return cmd;
	}
	
	


}
