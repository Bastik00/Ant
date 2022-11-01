package de.hska.lat.robot.component.sensor.mlx90620;


import de.hska.lat.robot.component.ComponentChangeNotifier;

public interface Mlx90620ChangeNotifier extends ComponentChangeNotifier{

	public void pixelChanged(Mlx90620 detector);
}
