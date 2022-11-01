

from tkinter import Canvas

class WindowCloser():
    def __init__(self, root,master):
        self._root = root
        self._canvas = Canvas(root)
        self._master = master
        self._canvas.config(bg = "RED")
        self._canvas.bind("<ButtonRelease-1>", self.mouse_released)
 
    def mouse_released(self,event):
        self._master.close()


