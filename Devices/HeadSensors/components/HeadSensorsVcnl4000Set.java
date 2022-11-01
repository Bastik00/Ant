package de.hska.lat.ant_IIIb.devices.headSensors.components;



import de.hska.lat.ant_IIIb.metaData.AntComponents;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000Protocol;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000Set;

public class HeadSensorsVcnl4000Set extends Vcnl4000Set
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1455037511364580876L;

	public HeadSensorsVcnl4000Set(Vcnl4000Protocol protocol)
	{

		
		this.add(new Vcnl4000(AntComponents.HEAD_VCNL4000_LEFT_SIDE.getMetaData(), protocol));
		this.add(new Vcnl4000(AntComponents.HEAD_VCNL4000_LEFT.getMetaData(), protocol));
		this.add(new Vcnl4000(AntComponents.HEAD_VCNL4000_CENTER.getMetaData(), protocol));
		this.add(new Vcnl4000(AntComponents.HEAD_VCNL4000_RIGHT.getMetaData(), protocol));
		this.add(new Vcnl4000(AntComponents.HEAD_VCNL4000_RIGHT_SIDE.getMetaData(), protocol));
		
		this.init();
	}

}
