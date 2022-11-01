package de.hska.lat.robot.component.sensor.mlx90620;

import java.util.ArrayList;


import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;

import de.hska.lat.robot.component.sensor.mlx90620.protocol.Cmd_setMlx90620setSettings;
import de.hska.lat.robot.value.ComponentValue;



public class Mlx90620 extends RobotComponent <Mlx90620ChangeNotifier,ComponentSettingsChangeNotifier,Mlx90620Protocol  > 
{

	
	

	
	//protected float ambientTemperature;
	
	/**************** old ****************************/
	protected Mlx90620IrSensor irSensor;
	protected Mlx90620AmbientSensor ambientSensor;
	protected Mlx90620IrFrameRate IR_refreshRate;
	
	protected int measurementMode;
	protected int operationMode;
	protected int TA_measurementRunning;
	protected int IR_measurementRunning;
	protected int POR_occured;
	protected int I2C_fastModeEnabled;
	protected Mlx90620AmbientRefreshRate TA_refreshRate;
	protected int ADC_highReferenceEnabled;

	
	//Mlx9020IrFrame
	
//	protected Mlx90620Protocol protocol;
	
	
	/**
	 * erzeugt ein neues Verwaltungsobjekt für den MLX90620 IR-Array-Sensor
	 * @param metaData	Metadaten, vom Device bereitzustellen
	 * @param protocol	Protokoll des MLX90620
	 */
	public Mlx90620(ComponentMetaData metaData, Mlx90620Protocol protocol)
	{
		super(metaData, protocol);
		

		
		this.irSensor = new Mlx90620IrSensor(metaData, protocol.thermalCameraProtocol);
		this.ambientSensor = new Mlx90620AmbientSensor(metaData, protocol.temperatureProtocol);
		
		
		
		/***** old **********/
		
		this.IR_refreshRate = Mlx90620IrFrameRate.IR_FRAME_RATE_512;
		this.measurementMode = 0;
		this.operationMode = 0;
		this.I2C_fastModeEnabled = 0;
		this.POR_occured = 1;
		this.TA_refreshRate = Mlx90620AmbientRefreshRate.AMBIENT_RATE_2;
		this.ADC_highReferenceEnabled = 1;
	
	}
	
	
	
	public void setSetup(int distanceRange, int gain, int address)
	{		
		for (ComponentSettingsChangeNotifier listener  : this.setupListener)
		{
		//	listener.setupChanged(this);
		}	
	}
	

	/**
	 * generiert aus den gesetzten Werten bzw. den hier gesetzten Defaults das Konfigurationsregister
	 * des MLX90620.
	 * Dieses setzt automatisch das POR-Flag des Sensors zurück, so dass dieser direkt nach Schreiben
	 * des Registers einsatzbereit ist
	 * bisher nicht veränderbare Werte:
	 * Arbeitsmodus: gesetzt auf normal
	 * I2C Fastmode: aktiviert
	 * ADC low reference: aktiviert
	 * @return	generiertes Statusregister
	 */
	protected int generateConfigurationRegister(){
		int register = 0;
		register |= this.IR_refreshRate.getNumber();
		// 4 und 5 sind reserviert -> NA
		register |= (this.measurementMode << 6);
		register |= (this.operationMode << 7);
		//register |= (this.TA_measurementRunning << 8); read-only register
		//register |= (this.IR_measurementRunning << 9); read-only register
		register |= (1 << 10); //POR resetten, um Sensor zu aktivieren
		register |= (this.I2C_fastModeEnabled << 11);
		register |= (this.TA_refreshRate.getNumber() << 12);
		register |= (this.ADC_highReferenceEnabled << 14);
		return register;
	}
	
	/**
	 * generiert das in der Manual des MLX90620 beschriebene Default-Konfigurationsregister
	 * Dabei gelieferte Werte:
	 * Bildrate: 1Hz
	 * Umgebungstemperaturrate: 2Hz
	 * Arbeitsmodus: normal
	 * Fastmode: enabled
	 * ADC low reference: enabled
	 * @return
	 */
	protected int generateDefaultConfigurationRegister(){
		return 0x740E;
	}
	
	
	
	
public boolean remote_setSettings(Mlx90620AmbientRefreshRate ambientRefreshRate, Mlx90620IrFrameRate irFrameRate)
{
	
	return(this.sendData(Cmd_setMlx90620setSettings.getCommand(this.componentProtocol.cmdSetSettingsId,
			this.localId,
			ambientRefreshRate.getRate(),
			irFrameRate.getNumber())));
	
}
	
	
	
	
	


	
	/***************************************************************************************
	 * Getter und Setter
	 ***************************************************************************************/
	/**
	 * liefert die aktuelle Wiederholrate der Umgebungstemperaturmessung des Sensors. 
	 * Muss nicht zwangsläufig mit der tatsächlichen Konfiguration übereinstimmen! 
	 * es muss
	 * - {@link #remote_getConfigurationregister()} aufgerufen worden sein
	 * - genug Zeit vergangen sein, dass die Antwort eingetroffen und verarbeitet wurde
	 * @return die aktuelle Wiederholrate der Umgebungstemperaturmessung
	 */
	public Mlx90620AmbientRefreshRate getTA_refreshRate() {
		return TA_refreshRate;
	}
	
	
	/**
	 * setzt die Wiederholrate der Umgebungstemperaturmessung auf den gegebenen Wert. 
	 * Muss anschließend noch mit {@link #remote_setConfigurationRegister()} an den Sensor geschickt werden
	 * um einen Effekt zu haben
	 * @param tA_refreshRate	die gewünschte neue Wiederholrate der Umgebungstemperaturmessung
	 */
	public void setTA_refreshRate(Mlx90620AmbientRefreshRate tA_refreshRate) {
		TA_refreshRate = tA_refreshRate;
	}
	
	
	/**
	 * liefert die aktuelle Bildwiederholrate, wie sie im Konfogurationsregister steht.
	 * Achtung! um den tatsächlich im Sensor eingestellten Wert wiederzuspiegeln muss:
	 * - {@link #remote_getConfigurationregister()} aufgerufen werden
	 * - genug Zeit vergangen sein, dass die Antwort ankommen und verarbeitet werden konnte
	 * @return die aktuell eingestellte Bildwiederholrate
	 */
	public Mlx90620IrFrameRate getIR_refreshRate() {
		return IR_refreshRate;
	}
	
	
	/**
	 * setzt die Bildwiederholrate des IR-Sensors auf den gewünschten Wert.
	 * Achtung! es muss {@link #remote_setConfigurationRegister()} aufgerufen werden, um den neuen Wert des
	 * Konfgurationsregisters an den Sensor zu Übertragen.
	 * Davon nicht betroffen ist die Kommunikationsgeschwindigkeit zwischen Software und Device!
	 * Um eine hohe Wiederholrate in der Oberfläche zu erhalten, muss ein entsprechender Wert beim Streaming
	 * des IR frames eingestellt werden.
	 * @param iR_refreshRate die gewünschte Bildwiederholrate
	 */
	public void setIR_refreshRate(Mlx90620IrFrameRate iR_refreshRate) {
		IR_refreshRate = iR_refreshRate;
	}
	
	
	/**
	 * liefert die aktuelle Umgebungstemperatur des IR Sensors. Der Wert wird nur durch manuellen Aufruf von
	 *  {@link #remote_getAmbientTemperature()} aktualisiert, oder durch aktivieren des Streamings.
	 * @return die aktuelle Umgebungstemperatur in °C auf Zwei Nachkommastellen genau
	 */
	/*public float getAmbientTemperature(){
		return (this.ambientSensor.ge.ambientTemperature;
	}


	
	
	/*
	public Mlx90620FieldSensor getFieldSensor() {
		return fieldSensor;
	}

*/

	
public Mlx90620AmbientSensor getAmbientSensor() 
{
	return ambientSensor;
}



	
public Mlx90620IrSensor getIrSensor()
{
	return (this.irSensor);
}


	
public ArrayList<ComponentValue<?>> getDataValues()
{
	
	ArrayList<ComponentValue<?>> values = this.irSensor.getDataValues();
	values.addAll(this.ambientSensor.getDataValues());

	return (values);
}




}
