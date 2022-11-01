package de.hska.lat.robot.component.sensor.mlx90620.protocol;
import de.hska.lat.comm.remote.RemoteCommand;

import de.hska.lat.comm.remote.parameter.RemoteParameterInt8;

public class Cmd_getConfigurationRegister extends RemoteCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5236461611026679580L;
	
	/**
	 * erzeugt ein neues Command-Paket, welches das Device dazu auffordert, das aktuelle
	 * Konfigurationsregister des Sensors auszulesen und zu verschicken
	 */
	public Cmd_getConfigurationRegister(){
		this.add(new RemoteParameterInt8("index", "Sensor index"));
	}
	
	/**
	 *  erzeugt ein neues Command-Paket, welches das Device dazu auffordert, das aktuelle
	 * Konfigurationsregister des Sensors auszulesen und zu verschicken
	 * @param command ID, welche vom device ausgewertet wird
	 */
	public Cmd_getConfigurationRegister(int command){
		this();
		this.setId(command);
	}
	
	public static Cmd_getConfigurationRegister getCommand(int command){
		Cmd_getConfigurationRegister cmd;
		cmd = new Cmd_getConfigurationRegister(command);
		return cmd;
	}
	
	


}
