package de.hska.lat.robot.component.generic.spectralAnalyzer;

import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface SpectralAnalyzerChangeNotifier extends ComponentChangeNotifier
{
	public void spectrumChanged(SpectralAnalyzer analyser);
}
