package de.hska.lat.ant.arbiter.led;

import de.hska.lat.ant.Ant;
import de.hska.lat.ant.behavoir.AntBehavoirPriority;
import de.hska.lat.ant.metaData.AntComponents;
import de.hska.lat.behavior.arbiter.component.led.LedArbiter;
import de.hska.lat.robot.component.actor.led.Led;

public class AntTailRightLedArbiter extends LedArbiter
{

	public AntTailRightLedArbiter(Ant ant)
	{
		super(AntBehavoirPriority.TAIL_LED_ARBITER_RIGHT.getPriority(),
				(Led) ant.getComponentOnGlobalId(AntComponents.TAIL_RIGHT_LED.getGlobalId()));
	}

}
