from RoboControl.Com.Remote.Parameter.RemoteParameterUint16 import RemoteParameterUint16
from RoboControl.Com.Remote.Parameter.RemoteParameterUint8 import RemoteParameterUint8
from RoboControl.Com.Remote.RemoteMessage import RemoteMessage



SENSOR_INDEX 	= 0
SENSOR_CURRENT 	= 1

class Msg_maxCurrentDrain(RemoteMessage):

	def __init__(self, id):
		super().__init__(id, "Msg_maxCurrentDrain", "maximal current drain measured by this sensor")

		self._parameter_list.append(RemoteParameterUint8("index","current sensor index"))
		self._parameter_list.append(RemoteParameterUint16("drain","maximal current drain measured by this sensor"))


	def get_command(id):
		cmd = Msg_maxCurrentDrain(id)
		return (cmd)


	def get_index(self):
		return self._parameter_list[SENSOR_INDEX].get_value()


	def get_max_current(self):
		return self._parameter_list[SENSOR_CURRENT].get_value()
		



