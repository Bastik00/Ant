from tkinter import Toplevel, Frame, Canvas, LEFT, BOTTOM
from RoboView.Robot.Viewer.RobotSettings import RobotSettings

class ExternalWindow():
    
    def __init__(self, internal_window):
        self._window = Toplevel(bg="GRAY", borderwidth=1, relief='solid')
        self._window.geometry("{}x{}+{}+{}".format(internal_window._width, internal_window._height, internal_window._x_pos, internal_window._y_pos))
        self._window.title(internal_window._title._name)        
        self._window_frame = internal_window._frame
        self._window_frame.pack()
        self.get_components()
        
    def get_components(self):
        RobotSettings.get_int(self._settings_key+".x_pos")