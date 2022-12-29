

from tkinter import Canvas, PhotoImage

class WindowCloser():
    def __init__(self, root,master):
        self._root = root
        self._canvas = Canvas(root)
        self._master = master
        self._image = PhotoImage(file="xconvert.com.png")
        
        self._canvas.create_image(0,0, image=self._image, anchor='nw')
        self._canvas.bind("<ButtonRelease-1>", self.mouse_released)

 
    def mouse_released(self,event):
        self._master.close()


