from RoboControl.Com.Remote.RemoteCommand import RemoteCommand
from RoboControl.Com.Remote.Parameter import RemoteParameterUint8


class Cmd_getActorValue(RemoteCommand):
	
	_index = 0

	def __init__(self):
		super().__init__("getActorValue","get actors value")
		self._parameter_list.append(RemoteParameterUint8("index","sensor index"))

		pass

	def set_index(self, index):
		self._parameter_list[self._index].set_value(index)

	def get_index(self):
		return self._parameter_list[self._index].get_value()


def get_command(id, index):
	cmd = Cmd_getActorValue()
	cmd.set_id(id)
	cmd.set_index(index)

	return (cmd)

