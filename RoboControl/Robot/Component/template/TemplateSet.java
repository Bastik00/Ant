package de.hska.lat.robot.component.template;

import de.hska.lat.robot.component.ComponentMetaData;
import de.hska.lat.robot.component.ComponentProtocol;
import de.hska.lat.robot.component.ComponentSet;

/**
 * Super class for TSL 2561 Sensor sets.
 * 
 * @author Oktavian Gniot
 * 
 */
public class TemplateSet extends
		ComponentSet<TemplateComponent, ComponentProtocol>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7626423114169877335L;

	public TemplateSet(ComponentMetaData[] sensors, ComponentProtocol protocol)
	{

		for (ComponentMetaData template : sensors)
		{
			this.add(new TemplateComponent(template, protocol));
		}
	}

}
