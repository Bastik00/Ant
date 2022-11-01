
from RobotBehavior.Arbiter.Action.ArbiterAction import ArbiterAction


class LedBrightness(ArbiterAction):
	def __init__(self, arbiter_config):
		self._brightness = 0
		arbiter_config['name'] = "change LED brightness"
		super()._init__(arbiter_config)


	def get_brightness(self):
		return self._brightness

	def set_brightness(self, brightness):
		self._brightness = brightness
	