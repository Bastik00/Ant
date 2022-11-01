package de.hska.lat.ant_IIIb.devices.headSensors.components;



import de.hska.lat.ant_IIIb.metaData.AntComponents;

import de.hska.lat.robot.component.temperatureSensor.mlx90614.Mlx90614;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.Mlx90614Protocol;
import de.hska.lat.robot.component.temperatureSensor.mlx90614.Mlx90614Set;

public class HeadMlx90614Set extends Mlx90614Set
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1455037511364580876L;

	public HeadMlx90614Set(Mlx90614Protocol protocol)
	{

		
		this.add(new Mlx90614(AntComponents.HEAD_LEFT_MLX90614.getMetaData(), protocol));
		this.add(new Mlx90614(AntComponents.HEAD_RIGHT_MLX90614.getMetaData(), protocol));
		
		this.init();
	}

}
