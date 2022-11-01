package de.hska.lat.robot.component.sensor.tsl2591;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;

/**
 * Super class for TSL 2561 Sensor sets.
 * 
 * @author Oktavian Gniot
 * 
 */
public class Tsl2591Set extends
		ComponentSet<Tsl2591, ComponentProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7626423114169877335L;

	public Tsl2591Set(ComponentMetaData[] sensors, ComponentProtocol protocol)
	{

		for (ComponentMetaData template : sensors)
		{
			this.add(new Tsl2591(template, protocol));
		}
	}

}
