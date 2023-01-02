

from tkinter import Canvas, PhotoImage


class WindowCloser():
    def __init__(self, root, master):
        self._root = root
        self._canvas = Canvas(root)
        self._master = master

        self._image = PhotoImage(file="Ant/Icons/close.png")
        self._canvas.create_image(0, 0, image=self._image, anchor='nw')
        self._canvas.bind("<ButtonRelease-1>", self.mouse_released)
        self._canvas.bind("<Enter>", self.mouse_hover)
        self._canvas.bind("<Leave>", self.mouse_leave)

    def mouse_released(self, event):
        self._master.close()

    def mouse_hover(self, event):
        self._image = PhotoImage(file="Ant/Icons/close_hover.png")
        self._canvas.create_image(0, 0, image=self._image, anchor='nw')

    def mouse_leave(self, event):
        self._image = PhotoImage(file="Ant/Icons/close.png")
        self._canvas.create_image(0, 0, image=self._image, anchor='nw')
