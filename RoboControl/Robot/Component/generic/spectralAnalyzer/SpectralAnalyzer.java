package de.hska.lat.robot.component.generic.spectralAnalyzer;



import java.util.ArrayList;

import de.hska.lat.comm.remote.RemoteDataTransmitter;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.generic.spectralBand.SpectralBand;
import de.hska.lat.robot.component.generic.spectralBand.SpectralBandSet;
import de.hska.lat.robot.component.sensor.Sensor;
import de.hska.lat.robot.value.ComponentValue;


public class SpectralAnalyzer
		extends
		Sensor<SpectralAnalyzerChangeNotifier, ComponentSettingsChangeNotifier, SpectralAnalyzerProtocol>
{

	protected SpectralBandSet spectralBandSet;

	public SpectralAnalyzer(ComponentMetaData metaData,
			SpectralAnalyzerProtocol protocol)
	{
		super(metaData, protocol);

		this.spectralBandSet = new SpectralBandSet();
		
		//this.spectralBand = new SpectralBand(metaData, protocol.spectralBandProtocol);
	}

	/**
	 * get Mpu 9150 Accelerometer
	 * 
	 * @return Mpus 9150 Accelerometer
	 */

	public SpectralBandSet getSpectralBandSet()
	{
		return (this.spectralBandSet);
	}
/*
	@Override
	public ArrayList<ComponentValue<?>> getDataValues()
	{

		ArrayList<ComponentValue<?>> values = new ArrayList<ComponentValue<?>>();

		values.addAll(this.spectralBand.getDataValues());

		return (values);
	}
*/
	@Override
	public void setTransmitter(RemoteDataTransmitter transmitter)
	{
		super.setTransmitter(transmitter);

		this.spectralBandSet.setTransmitter(transmitter);
	}

	
	
	
	public ArrayList<ComponentValue<?>> getDataValues()
	{
		
		ArrayList<ComponentValue<?>> values = super.getDataValues();
		
		for (SpectralBand band : this.spectralBandSet)
		{
			values.add(band.getSpectralBandValue());
		}

				
		return (values);
	}
	
	

}
