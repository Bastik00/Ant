from RobotBehavior.Arbiter.Action.ArbiterAction import ArbiterAction


class LedBlink(ArbiterAction):
	def __init__(self, arbiter_config):
		self._frequency = 0
		arbiter_config['name'] = "Action blink LED"
		super()._init__(arbiter_config)

	
	def get_frequency(self):
		return self._frequency
	

	def set_frequency(self, frequency):
		self._frequency = frequency





