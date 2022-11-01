class ArbiterConnection:
	def __init__(self,  listener):
		self._listener = listener
	

	def receive(self, action):
		if self._listener is not None:
			self._listener.receive(action)

	

	def clear(self, priority):
		if self._listener is not None:
			self._listener.clear(priority)

	

	def get_listener(self):
		return self._listener


