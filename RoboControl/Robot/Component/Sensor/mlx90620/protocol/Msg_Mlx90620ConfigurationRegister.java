package de.hska.lat.robot.component.sensor.mlx90620.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint16;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;

public class Msg_Mlx90620ConfigurationRegister extends RemoteMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1247433190649447765L;
	
	private static final int INDEX_SENSOR = 0;
	private static final int INDEX_REGISTER = 1;

	protected static final String name = "mlx90620ConfigurationRegister";
	protected static final String description = "Configuration Register of a mlx90620 temperature array sensor";
	
	
	public Msg_Mlx90620ConfigurationRegister(){
		this.add(new RemoteParameterUint8("index", "sensor index"));
		this.add(new RemoteParameterUint16("value", "configuration register value"));
	}
	
	public Msg_Mlx90620ConfigurationRegister(int command){
		this();
		this.setId(command);
	}
	
	public void setData(int index, int value){
		((RemoteParameterUint8) this.get(Msg_Mlx90620ConfigurationRegister.INDEX_SENSOR)).setValue(index);
		((RemoteParameterUint16) this.get(Msg_Mlx90620ConfigurationRegister.INDEX_REGISTER)).setValue(value);
	}
	
	public int get_Index(){
		return ((RemoteParameterUint8) this.get(Msg_Mlx90620ConfigurationRegister.INDEX_SENSOR)).getValue();
	}
	
	public int get_Configuration_Register(){
		return ((RemoteParameterUint16) this.get(Msg_Mlx90620ConfigurationRegister.INDEX_REGISTER)).getValue();
	}
}

