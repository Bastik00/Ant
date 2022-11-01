package de.hska.lat.robot.component.generic.spectralSynthesizer;



import de.hska.lat.comm.remote.RemoteDataTransmitter;
import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentSettingsChangeNotifier;
import de.hska.lat.robot.component.RobotComponent;
import de.hska.lat.robot.component.generic.spectralAnalyzer.SpectralAnalyzerChangeNotifier;
import de.hska.lat.robot.component.generic.spectralBand.SpectralBandSet;



public class SpectralSynthesizer
		extends
		RobotComponent<SpectralAnalyzerChangeNotifier, ComponentSettingsChangeNotifier, SpectralSynthesizerProtocol>
{

	protected SpectralBandSet spectralBandSet;

	public SpectralSynthesizer(ComponentMetaData metaData,
			SpectralSynthesizerProtocol protocol)
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

	
	
	
	
	
	

}
