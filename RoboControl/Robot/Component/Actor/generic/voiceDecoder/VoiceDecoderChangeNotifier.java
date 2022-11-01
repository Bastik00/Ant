package de.hska.lat.robot.component.actor.generic.voiceDecoder;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface VoiceDecoderChangeNotifier extends ComponentChangeNotifier
{

	public void speedChanged(int globalId, int speed);
}
