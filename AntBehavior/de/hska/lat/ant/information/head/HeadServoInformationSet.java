package de.hska.lat.ant.information.head;

import de.hska.lat.ant.Ant;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.FilterInformation;
import de.hska.lat.behavior.information.filter.InformationFilter;
import de.hska.lat.behavior.information.filter.NoFilter;


public class HeadServoInformationSet extends InformationSet
{



	/**
	 * 
	 */
	private static final long serialVersionUID = -2233256974863772993L;


	public static final int SENSOR_LEFT_90		= 0 ;

	
	private InformationFilter[] filters;
	
	
	public HeadServoInformationSet(Ant ant, FilterInformation filterInformation)
	{
		filters = new InformationFilter[1];
		
			filters[0] = new NoFilter();
		

		
		
	/*	this.add( new Information(this.getLuxValue(ant, AntComponents.HEAD_VCNL4000_LEFT_SIDE.getGlobalId()),
				filters[0], 1, HeadServoInformationSet.SENSOR_LEFT_90));
	*/
			
	
	}


/*
	public ServoPosition getServoPosition(Ant ant, int globalId)
	{
		Servo servo = (Servo) ant.getComponentOnGlobalId(globalId);
		
		//return(servo.getAngleValue());
	}
*/
}
