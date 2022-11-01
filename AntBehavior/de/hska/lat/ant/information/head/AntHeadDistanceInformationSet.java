package de.hska.lat.ant.information.head;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.NoFilter;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.value.distance.DistanceValue;


public class AntHeadDistanceInformationSet extends InformationSet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -647700039046654702L;

	public static final int SENSOR_LEFT_90		= 0 ;
	public static final int SENSOR_LEFT_45		= 1 ;
	public static final int SENSOR_CENTER		= 2 ;
	public static final int SENSOR_RIGHT_45		= 3 ;
	public static final int SENSOR_RIGHT_90		= 4 ;
	
public AntHeadDistanceInformationSet(Ant ant)
{
	this.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_LEFT_SIDE.getGlobalId()),
			new NoFilter() , 1, AntHeadDistanceInformationSet.SENSOR_LEFT_90));

	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_LEFT.getGlobalId()),
			new NoFilter() , 1,AntHeadDistanceInformationSet.SENSOR_LEFT_45));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_CENTER.getGlobalId()),
			new NoFilter() , 1, AntHeadDistanceInformationSet.SENSOR_CENTER));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_RIGHT.getGlobalId()),
			new NoFilter() , 1, AntHeadDistanceInformationSet.SENSOR_RIGHT_45));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.HEAD_VCNL4000_RIGHT_SIDE.getGlobalId()),
			new NoFilter() , 1, AntHeadDistanceInformationSet.SENSOR_RIGHT_90));

}
	



public DistanceValue getDistanceValue(Ant ant, int globalId)
{
	Vcnl4000 vcnl4000 = (Vcnl4000) ant.getComponentOnGlobalId(globalId);
	
	return(vcnl4000.getDistanceValue());
}


}
