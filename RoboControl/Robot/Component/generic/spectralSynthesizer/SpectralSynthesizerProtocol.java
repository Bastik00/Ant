package de.hska.lat.robot.component.generic.spectralSynthesizer;

import java.util.ArrayList;


import de.hska.lat.robot.component.generic.spectralAnalyzer.SpectralAnalyzer;
import de.hska.lat.robot.component.generic.spectralBand.SpectralBandProtocol;
import de.hska.lat.robot.component.sensor.SensorProtocol;
import de.hska.lat.robot.device.device.remoteProcessor.RemoteStreamProcessor;

public class SpectralSynthesizerProtocol extends SensorProtocol {
	
	public final SpectralBandProtocol spectralBandProtocol;
	
	public SpectralSynthesizerProtocol (int deviceId,

		int cmdSetSettingsId,
		int cmdGetSettingsId,
		int cmdSaveDefaultsId,
		int cmdLoadDefaultsId,
		int msgSettingsId,
		int cmdSetSpectralBandId,
		int cmdGetSpectralBandId,
		int msgSpectralBandId, 
		int streamSpectralBandId
			)

	
	{
	super(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId, 0, 0, 0
			);
	
	
	this.spectralBandProtocol = new SpectralBandProtocol(deviceId,
			cmdSetSettingsId,
			cmdGetSettingsId,
			cmdSaveDefaultsId,
			cmdLoadDefaultsId,
			msgSettingsId,
			cmdSetSpectralBandId,
			cmdGetSpectralBandId,
			msgSpectralBandId,
			streamSpectralBandId
			);
	
	}
	/*
	public ArrayList<RemoteCommandProcessor> getCommandProcessors(SpectralBandCoderSet spectralBandCoderSet)
	{
		ArrayList<RemoteCommandProcessor> commands =super.getCommandProcessors(spectralBandCoderSet);
		
		commands.addAll(this.spectralBandProtocol.getCommandProcessors(spectralBandCoderSet.getSpectralBandSet()));
		
		return(commands);
	}
	
	
	
	public ArrayList<RemoteMessageProcessor> getMessageProcessors(SpectralBandCoderSet spectralBandCoderSet)
	{
		ArrayList<RemoteMessageProcessor> messages =super.getMessageProcessors(spectralBandCoderSet);
		
		messages.addAll(this.spectralBandProtocol.getMessageProcessors(spectralBandCoderSet.getSpectralBandSet()));
		
		return(messages);
	}
		
	
	*/
	public ArrayList<RemoteStreamProcessor> getStreamProcessors(SpectralAnalyzer spectralAnalyzer)
	{
		ArrayList<RemoteStreamProcessor> streams = super.getStreamProcessors(spectralAnalyzer);
		
		streams.addAll(this.spectralBandProtocol.getStreamProcessors(spectralAnalyzer));
		
		
		
		
		return(streams);
	}
		

}
