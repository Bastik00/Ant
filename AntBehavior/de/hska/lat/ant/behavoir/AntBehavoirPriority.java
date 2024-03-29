package de.hska.lat.ant.behavoir;

public enum AntBehavoirPriority
{
	SERVO_ARBITER_LEFT,
	SERVO_ARBITER_CENTER,
	SERVO_ARBITER_RIGHT,
	SERVO_ARBITER_HEAD,
	
	FOOT_LED_ARBITER_FRONT_LEFT,
	FOOT_LED_ARBITER_FRONT_RIGHT,
	FOOT_LED_ARBITER_CENTER_LEFT,
	FOOT_LED_ARBITER_CENTER_RIGHT,
	FOOT_LED_ARBITER_BACK_LEFT,
	FOOT_LED_ARBITER_BACK_RIGHT,
	
	
	TAIL_LED_ARBITER_LEFT,
	TAIL_LED_ARBITER_RIGHT,

	
	
	
	HEAD_LIGHT_PRIORITY,
	LIGHT_SUPRESSOR_PRIORITY,	
	LIGHT_AVOIDANCE_PRIORITY,	
	LIGHT_FOLLOWER_PRIORITY,

	
	
	EDGE_DETECTOR_PRIORITY,
	MOTION_CONTROL_PRIORITY,
	MOTION_BEHAVIOR_PRIORITY,
	MOTION_SEQUENCER_PRIORITY,
	BLINK_LIGHT_FOLLOWER_PRIORITY,
	MANUAL_CONTROL_PRIORITY
	
	;
	
	
public int getPriority ()
{
		return(this.ordinal());	
}
	
}
