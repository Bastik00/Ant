package de.hska.lat.robot.component.sensor.mlx90620;

import java.util.ArrayList;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.value.ComponentValue;




public class Mlx90620Set extends ComponentSet<Mlx90620, Mlx90620Protocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4672515149815681959L;

	protected Mlx90620IrSensorSet irSensors;
	protected Mlx90620AmbientSensorSet ambientSensors;
	
	
	public Mlx90620Set(ArrayList<ComponentMetaData> sensors, Mlx90620Protocol protocol)
	{
		super();
		
		
		for (ComponentMetaData sensor: sensors)
		{
			this.add(new Mlx90620(sensor, protocol));
		}
		
		this.irSensors = new Mlx90620IrSensorSet(this);
		
		this.ambientSensors = new Mlx90620AmbientSensorSet(this);
		
		
	
		
	}
	
	
	
	
	
	public Mlx90620AmbientSensorSet getAmbientSensors()
	{
		return(this.ambientSensors);
	}





	public Mlx90620IrSensorSet getIrSensors()
	{
		return (this.irSensors);
	}





	public ArrayList<ComponentValue<?>> getInputValues()
	{

		return (new ArrayList<ComponentValue<?>>());
	}
	
	
	/**
	 * wie {@link #processAmbientTemperature(Msg_Mlx90620AmbientTemperature)}, allerdings für 
	 * den Fall einer eintreffenden Stream-Nachricht
	 * @param message vom Device verschickter Stream
	 */
/*	
	private void processAmbientTemperatureStream(Stream_Mlx90620AmbientTemperature message){
		this.ambientTemperature = message.get_Ambient_Temperature();
		this.value = new UnsignedAnalogValue("value", 600);
		this.value.setRawValue( Math.round(this.ambientTemperature));
		this.ambientSensor.setTemperature(this.ambientTemperature + 273.0f);
		
		for(Mlx90620ChangeNotifier listener : this.sensorListener){
			listener.AmbientTemperatureChanged(this);
		}
		
	}
	*/
}
