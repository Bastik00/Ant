
from tkinter import  LEFT, RIGHT, TOP, Button, Frame, Label, Menu

class CpuStatisticsView:
	def __init__(self, root, device):
		self._frame = Frame(master = root, bg = "WHITE", borderwidth=1 , height = 50, width = 300  )
		self._root = root
		self._device = device
		self.build_view()

		device.add_cpu_status_listener(self)

	def build_view(self):
		label = Label(self._frame  ,text="CPU")
		label.pack(side = TOP)

		label = Button(self._frame  ,text="clear")
		label.pack(side = RIGHT)

		label = Label(self._frame, text="last :", font=("Courier", 12))
		label.pack(side = LEFT, padx = 2 )
		self._last_load = Label(self._frame, text="-", font=("Courier", 12))
		self._last_load.pack(side = LEFT, padx = 2 )

		label = Label(self._frame, text="min :", font=("Courier", 12))
		label.pack(side = LEFT, padx = 2 )
		self._min_load = Label(self._frame, text="-", font=("Courier", 12))
		self._min_load.pack(side = LEFT, padx = 2 )

		label = Label(self._frame, text="max :", font=("Courier", 12))
		label.pack(side = LEFT , padx = 2)
		self._max_load = Label(self._frame, text="-", font=("Courier", 12))
		self._max_load.pack(side = LEFT, padx = 2 )

		self._frame.bind("<ButtonRelease-3>", self.mouse_released)

		self._context_menue = Menu(self._frame, tearoff=0)
		self._context_menue.add_command(label="Clear cpu Statistic", command=self.on_clear_statistic) 


	def mouse_released(self, event):

		try:
			self._context_menue.tk_popup(event.x_root, event.y_root)
		finally:
			self._context_menue.grab_release()

	def on_clear_statistic(self):
		self._device.remote_clear_cpu_statistics()
		pass



	def cpu_status_changed(self,statistic):
		min_load = statistic.get_min_load()
		self._min_load['text']=str(min_load)

		max_load = statistic.get_max_load()
		self._max_load['text']=str(max_load)

		
		last_load =statistic.get_last_load()
		self._last_load['text']=str(last_load)
	
