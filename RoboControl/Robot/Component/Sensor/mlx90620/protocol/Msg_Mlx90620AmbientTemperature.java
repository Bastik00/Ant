package de.hska.lat.robot.component.sensor.mlx90620.protocol;

import de.hska.lat.comm.remote.RemoteMessage;
import de.hska.lat.comm.remote.parameter.RemoteParameterInt32;
import de.hska.lat.comm.remote.parameter.RemoteParameterUint8;

public class Msg_Mlx90620AmbientTemperature extends RemoteMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1247433190649447765L;
	
	private static final int INDEX_SENSOR = 0;
	private static final int INDEX_TEMPERATURE = 1;

	protected static final String name = "mlx90620AmbientTemperature";
	protected static final String description = "Ambient Temperature of a mlx90620 temperature array sensor";
	
	/**
	 * erzeugt ein Daten-Paket, welches die Umgebungstemperatur des Sensors enthält
	 */
	public Msg_Mlx90620AmbientTemperature(){
		this.add(new RemoteParameterUint8("index", "sensor index"));
		this.add(new RemoteParameterInt32("value", "ambient temperature value"));
	}
	
	/**
	 * erzeugt ein Daten-Paket, welches die Umgebungstemperatur des Sensors enthält
	 * @param command	ID, mit welcher das Device das Paket verschickt
	 */
	public Msg_Mlx90620AmbientTemperature(int command){
		this();
		this.setId(command);
	}

	/**
	 * liefert den Index des Senors, zu welchem das Paket gehört
	 * @return	Index
	 */
	public int get_Index(){
		return ((RemoteParameterUint8) this.get(Msg_Mlx90620AmbientTemperature.INDEX_SENSOR)).getValue();
	}
	
	/**
	 * liefert die Umgebungstemperatur, die im Paket verschickt wurde in °C zurück
	 * @return	Umgebungstemperatur in °C auf zwei Nachkommastellen genau
	 */
	public float get_Ambient_Temperature(){
		return (((RemoteParameterInt32) this.get(Msg_Mlx90620AmbientTemperature.INDEX_TEMPERATURE)).getValue())/100.0f;
	}
}

