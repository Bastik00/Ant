from tkinter import LEFT, RIGHT, Button, Frame, Menu, Spinbox, StringVar, Tk
import customtkinter as ctk
from tkinter.ttk import Combobox
from RoboView.Robot.Device.Viewer.ComStatisticsView import ComStatisticsView
from RoboView.Robot.Device.Viewer.CpuStatisticsView import CpuStatisticsView


class ToolBar:
	def __init__(self, root, device):
		self._frame = ctk.CTkFrame(master = root,  fg_color = "BLUE", border_width=1)
		self._root = root

		self._device = device


		label = ctk.CTkButton(self._frame  ,text="ping", command = self.send_ping)
		label.pack(side = RIGHT)


		self.build_view()




	def send_ping(self):
		self._device.remote_ping_device()


	def build_view(self):


		my_var= StringVar()
		my_var.set("1000")

		self._period = Spinbox( self._frame, from_= 0, to=10000,increment = 10, textvariable = my_var, width = 10)
		self._period.pack(side = LEFT)
		

		aquisators = self._device.get_data_aquisators()

	
		self._aquisators = Combobox(self._frame, 
								values=aquisators)


		self._aquisators.current(0)
		self._aquisators.pack(side = LEFT)
		# TODO: Buttons kleiner machen
		button = ctk.CTkButton(self._frame  ,text="On", fg_color = "WHITE", width = 50, corner_radius = 5, command = self.start_stream)
		button.pack(side = LEFT)
		
		button = ctk.CTkButton(self._frame  ,text="Off", fg_color = "WHITE", width = 50, corner_radius = 5, command = self.stop_stream)
		button.pack(side = LEFT)

		self._frame.bind("<ButtonRelease-3>", self.mouse_released)

		self._context_menue = Menu(self._frame, tearoff=0)
		self._context_menue.add_command(label="Clear all streams", command=self.on_clear) 
		self._context_menue.add_command(label="Pause all streams", command=self.on_pause)
		self._context_menue.add_command(label="Continue all streams", command=self.on_continue)
		self._context_menue.add_separator()
		self._context_menue.add_command(label="Save all streams", command=self.on_save)
		self._context_menue.add_command(label="Load all streams", command=self.on_load)

	def mouse_released(self, event):

		try:
			self._context_menue.tk_popup(event.x_root, event.y_root)
		finally:
			self._context_menue.grab_release()

	def on_clear(self):
		self._device.remote_clear_streams()

	def on_pause(self):
		self._device.remote_pause_streams()
		pass

	def on_continue(self):
		self._device.remote_continue_streams()
		pass

	def on_save(self):
		self._device.remote_save_streams()
		pass

	def on_load(self):
		self._device.remote_load_streams()
		pass


	def start_stream(self):
		print(self._aquisators.current())
		index = self._aquisators.current() + 1
		self._device.remote_start_stream(index, int(int(self._period.get())/10))


	def stop_stream(self):
		index = self._aquisators.current() + 1
		self._device.remote_stop_stream(index)

