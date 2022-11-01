package de.hska.lat.ant.information.head;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.information.Information;
import de.hska.lat.behavior.information.InformationSet;
import de.hska.lat.behavior.information.filter.BandpassFilter;
import de.hska.lat.behavior.information.filter.BandpassFilterInformation;
import de.hska.lat.behavior.information.filter.FilterInformation;
import de.hska.lat.behavior.information.filter.InformationFilter;
import de.hska.lat.behavior.information.filter.NoFilter;
import de.hska.lat.robot.component.sensor.vcnl4000.Vcnl4000;
import de.hska.lat.robot.value.lux.LuxValue;

public class AntHeadLuxInformationSet extends InformationSet
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5129858757369596267L;

	public static final int SENSOR_LEFT_90		= 0 ;
	public static final int SENSOR_LEFT_45		= 1 ;
	public static final int SENSOR_CENTER		= 2 ;
	public static final int SENSOR_RIGHT_45		= 3 ;
	public static final int SENSOR_RIGHT_90		= 4 ;
	
	private InformationFilter[] filters;
	
	
	public AntHeadLuxInformationSet(Ant ant, FilterInformation filterInformation)
	{
		filters = new InformationFilter[5];
		if (filterInformation instanceof BandpassFilterInformation)
		{
			filters[0] = new BandpassFilter(((BandpassFilterInformation) filterInformation).getFrequency(),
					((BandpassFilterInformation) filterInformation).getTimeBase());
			filters[1] = new BandpassFilter(((BandpassFilterInformation) filterInformation).getFrequency(),
					((BandpassFilterInformation) filterInformation).getTimeBase());
			filters[2] = new BandpassFilter(((BandpassFilterInformation) filterInformation).getFrequency(),
					((BandpassFilterInformation) filterInformation).getTimeBase());
			filters[3] = new BandpassFilter(((BandpassFilterInformation) filterInformation).getFrequency(),
					((BandpassFilterInformation) filterInformation).getTimeBase());
			filters[4] = new BandpassFilter(((BandpassFilterInformation) filterInformation).getFrequency(),
					((BandpassFilterInformation) filterInformation).getTimeBase());
		}
		else
		{
			filters[0] = new NoFilter();
			filters[1] = new NoFilter();
			filters[2] = new NoFilter();
			filters[3] = new NoFilter();
			filters[4] = new NoFilter();
		}
		

		this.add( new Information(this.getLuxValue(ant, AntComponents.HEAD_VCNL4000_LEFT_SIDE.getGlobalId()),
				filters[0], 1, AntHeadLuxInformationSet.SENSOR_LEFT_90));
	
	
		this.add( new Information(this.getLuxValue(ant, AntComponents.HEAD_VCNL4000_LEFT.getGlobalId()) ,
				filters[1], 1, AntHeadLuxInformationSet.SENSOR_LEFT_45));
	
		
		this.add( new Information(this.getLuxValue(ant, AntComponents.HEAD_VCNL4000_CENTER.getGlobalId()) ,
				filters[2], 1, AntHeadLuxInformationSet.SENSOR_CENTER));
		
		
		this.add( new Information(this.getLuxValue(ant, AntComponents.HEAD_VCNL4000_RIGHT.getGlobalId()) ,
				filters[3], 1, AntHeadLuxInformationSet.SENSOR_RIGHT_45));
		
		
		this.add( new Information(this.getLuxValue(ant, AntComponents.HEAD_VCNL4000_RIGHT_SIDE.getGlobalId()) ,
				filters[4], 1, AntHeadLuxInformationSet.SENSOR_RIGHT_90));
	
	}


	public LuxValue getLuxValue(Ant ant, int globalId)
	{
		Vcnl4000 vcnl4000 = (Vcnl4000) ant.getComponentOnGlobalId(globalId);
		
		return(vcnl4000.getLuxValue());
	}


}
