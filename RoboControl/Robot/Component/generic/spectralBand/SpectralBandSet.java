package de.hska.lat.robot.component.generic.spectralBand;

import de.hska.lat.comm.remote.RemoteStream;
import de.hska.lat.robot.component.ComponentSet;
import de.hska.lat.robot.component.generic.spectralBand.protocol.Stream_spectralBand;





/**
 * Super class for TSL 2561 Sensor sets.  
 * 
 * @author Oktavian Gniot
 *
 */
public class SpectralBandSet  extends ComponentSet<SpectralBand, SpectralBandProtocol>
{

/**
	 * 
	 */
	private static final long serialVersionUID = -1233984250450938066L;

	
public SpectralBandSet()
{
}	


/*
public SpectralBandSet(SpectralBandCoderSet spectralBandCoderSet) {
	for (SpectralAnalyser spectralBandCoder : spectralBandCoderSet)
	{
		this.add(spectralBandCoder.getSpectralBand());
	}
}

public SpectralBandSet(ArrayList<DetectorMetaData> sensors,
		SpectralBandProtocol protocol)
{
	for (DetectorMetaData detector: sensors)
	{
		this.add(new SpectralBand(detector,protocol));
	}
}


*/
public void processSpectralBands(Stream_spectralBand spectralBands) 
{
//	System.out.println(this.size());
//	System.out.println(spectralBands.size());
	SpectralBand band;
	for (int i = 0; i <= spectralBands.size(); i++)
	{
		band = this.getComponentOnLocalId(i);
		if (band != null)
		{
//			System.out.println("es gibt:" + i);
			this.get(i).setSpectralBandValue(spectralBands.getSpectralband(i));
		}
	}
}





@Override
public boolean decodeStream(RemoteStream remoteData)
{
	
	if (remoteData instanceof Stream_spectralBand)
	{
		Stream_spectralBand data = (Stream_spectralBand) remoteData;
//		System.out.println("Stream_spectralBand Message:");
//		System.out.println("\t" + data.toString());
//		for (int i = 0; i < data.size(); i++)
//		{
//			System.out.println("Band " + i + ": " + data.getSpectralband(i));
//		}
		processSpectralBands(data);
	}
	else
	{
		super.decodeStream(remoteData);		
	}
	
	return false;
}




}
