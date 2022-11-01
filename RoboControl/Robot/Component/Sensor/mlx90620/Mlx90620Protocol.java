package de.hska.lat.robot.component.sensor.mlx90620;

import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.generic.temperatureSensor.TemperatureSensorProtocol;
import de.hska.lat.robot.component.thermalCamera.protocol.ThermalCameraProtocol;

public class Mlx90620Protocol extends ComponentProtocol
{

	public final int getAmbientTemperatureId;
	public final int getConfigurationRegisterId;
	public final int setConfigurationRegisterId;
	public final int getIRPixelId;
	public final int getIRRowId;
	
	
	public final ThermalCameraProtocol thermalCameraProtocol; 
	public final TemperatureSensorProtocol temperatureProtocol; 
	
	/**
	 * erzeugt ein neues Verwaltungsobjekt für den MLX90620. enthält alle Nachrichten-IDs, welche
	 * für die Kommunikation benötigt werden
	 * @param deviceId
	 * @param setSettingsCommandId
	 * @param getSettingsCommandId
	 * @param saveDefaultsCommandId
	 * @param loadDefaultsCommandId
	 * @param getValueCommandId
	 * @param getAmbientTemperatureId
	 * @param getConfigurationRegisterId
	 * @param setConfigurationRegisterId
	 * @param getIRPixelId
	 * @param getIRRowId
	 * @param streamTemperatureMeasurmentsId
	 */
	public Mlx90620Protocol(
			int deviceId, 
			int setSettingsCommandId,
			int getSettingsCommandId, 
			int saveDefaultsCommandId,
			int loadDefaultsCommandId,
			int getValueCommandId,
			int getAmbientTemperatureId,
			int getConfigurationRegisterId,
			int setConfigurationRegisterId,
			int getIRPixelId,
			int getIRRowId,
			int streamTemperatureMeasurmentsId,
			
			int streamThermalData
			) 
	{
		super(deviceId, setSettingsCommandId, getSettingsCommandId,
				saveDefaultsCommandId, loadDefaultsCommandId,0);
		
		this.getAmbientTemperatureId = getAmbientTemperatureId;
		this.getConfigurationRegisterId = getConfigurationRegisterId;
		this.setConfigurationRegisterId = setConfigurationRegisterId;
		this.getIRPixelId = getIRPixelId;
		this.getIRRowId =getIRRowId;
		
		
		 this.temperatureProtocol = new TemperatureSensorProtocol(
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				streamTemperatureMeasurmentsId
		 );
		
		
		this.thermalCameraProtocol = new ThermalCameraProtocol(
				0,
				0,
				0,
				0,
				0,
				streamThermalData
				);
		
		// TODO Auto-generated constructor stub
	}

	



}
