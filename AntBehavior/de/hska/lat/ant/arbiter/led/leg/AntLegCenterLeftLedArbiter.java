package de.hska.lat.ant.arbiter.led.leg;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.led.LedArbiter;
import de.hska.lat.robot.component.actor.led.Led;

public class AntLegCenterLeftLedArbiter extends LedArbiter {

	public AntLegCenterLeftLedArbiter(Ant ant) {
		super(AntBehavoirPriority.FOOT_LED_ARBITER_CENTER_LEFT.getPriority(),
				(Led) ant.getComponentOnGlobalId(AntComponents.CENTER_LEFT_LEG_LED.getGlobalId()) );
	}

}
