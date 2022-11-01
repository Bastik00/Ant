package de.hska.lat.ant.information.leg;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.NoFilter;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.value.distance.DistanceValue;


public class AntLegDistanceInformationSet extends InformationSet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -647700039046654702L;

	public static final int SENSOR_LEFT_FRONT		= 0 ;
	public static final int SENSOR_LEFT_CENTER		= 1 ;
	public static final int SENSOR_LEFT_BACK		= 2 ;
	public static final int SENSOR_RIGHT_FRONT		= 3 ;
	public static final int SENSOR_RIGHT_CENTER		= 4 ;
	public static final int SENSOR_RIGHT_BACK		= 5 ;
	
public AntLegDistanceInformationSet(Ant ant)
{
	this.add( new Information(this.getDistanceValue(ant, AntComponents.FRONT_LEFT_LEG_VCNL4020.getGlobalId()),
			new NoFilter() , 1, AntLegDistanceInformationSet.SENSOR_LEFT_FRONT));

	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.CENTER_LEFT_LEG_4020.getGlobalId()),
			new NoFilter() , 1,AntLegDistanceInformationSet.SENSOR_LEFT_CENTER));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.BACK_LEFT_LEG_VCNL4020.getGlobalId()),
			new NoFilter() , 1, AntLegDistanceInformationSet.SENSOR_LEFT_BACK));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.FRONT_RIGHT_LEG_4020.getGlobalId()),
			new NoFilter() , 1, AntLegDistanceInformationSet.SENSOR_RIGHT_FRONT));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.CENTER_RIGHT_LEG_VCNL4020.getGlobalId()),
			new NoFilter() , 1, AntLegDistanceInformationSet.SENSOR_RIGHT_CENTER));
	
	
	this.add( new Information(this.getDistanceValue(ant, AntComponents.BACK_RIGHT_LEG_VCNL4020.getGlobalId()),
			new NoFilter() , 1, AntLegDistanceInformationSet.SENSOR_RIGHT_BACK));

}
	



public DistanceValue getDistanceValue(Ant ant, int globalId)
{
	Vcnl4000 vcnl4000 = (Vcnl4000) ant.getComponentOnGlobalId(globalId);
	
	return(vcnl4000.getDistanceValue());
}


}
