package de.hska.lat.ant.arbiter.led.leg;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.led.LedArbiter;
import de.hska.lat.robot.component.actor.led.Led;

public class AntLegFrontRightLedArbiter extends LedArbiter 
{

	public AntLegFrontRightLedArbiter(Ant ant)
	{
		super(AntBehavoirPriority.FOOT_LED_ARBITER_FRONT_RIGHT.getPriority(),
				(Led) ant.getComponentOnGlobalId(AntComponents.FRONT_RIGHT_LEG_LED.getGlobalId()) );
	}

}
