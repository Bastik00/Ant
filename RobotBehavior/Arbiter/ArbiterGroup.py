
from RobotBehavior.Arbiter.Arbiter import Arbiter
from copy import deepcopy

class ArbiterGroup(Arbiter):
	def __init__(self, id):
		self._id = id
		self._arbiters = list()


	def process(self):
		pass

	def add_arbiter(self, arbiter):
		self._arbiters.extend(arbiter)


	def getArbiters(self):
		return deepcopy(self._arbiters)

	
"""
public  ArrayList<ArbiterConnection> getAllOutputs()
{
	ArrayList<ArbiterConnection> list = new ArrayList<ArbiterConnection>();
	return(list);
}
"""

