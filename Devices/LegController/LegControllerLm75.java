package de.hska.lat.ant_IIIb.devices.legController.components;



import de.hska.lat.ant_IIIb.metaData.AntComponents;
import de.hska.lat.robot.component.sensor.lm75.Lm75;
import de.hska.lat.robot.component.sensor.lm75.Lm75Protocol;
import de.hska.lat.robot.component.sensor.lm75.Lm75Set;



public class LegControllerLm75 extends Lm75Set
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1455037511364580876L;

	public LegControllerLm75(Lm75Protocol protocol)
	{


		this.add(new Lm75(AntComponents.LEFT_TEMPERATURE_SENSOR.getMetaData(), protocol));
		this.add(new Lm75(AntComponents.CENTER_TEMPERATURE_SENSOR.getMetaData(), protocol));
		this.add(new Lm75(AntComponents.RIGHT_TEMPERATURE_SENSOR.getMetaData(), protocol));
		this.add(new Lm75(AntComponents.REGULATOR_TEMPERATURE_SENSOR.getMetaData(), protocol));

	}

}
