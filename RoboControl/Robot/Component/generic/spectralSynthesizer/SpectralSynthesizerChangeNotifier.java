package de.hska.lat.robot.component.generic.spectralSynthesizer;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface SpectralSynthesizerChangeNotifier extends ComponentChangeNotifier
{
	public void spectrumChanged(SpectralSynthesizer synthesizer);
}
