from tkinter import Canvas

class WindowTitle():
    def __init__(self, root, master):
        # TODO: Canvas in Frame packen
        self._canvas = Canvas(root)
        self._root = root
        self._master = master
        self._canvas.config(bg = "BLUE")
        self._canvas.bind("<Button-1>", self.mouse_pressed)
        self._canvas.bind("<ButtonRelease-1>", self.mouse_released)
        self._name = self._canvas.create_text(10,10, text = "test",  fill='WHITE', anchor='w')


    def rename(self, new_name):
         self._canvas.itemconfig(self._name, text = new_name)


    def mouse_pressed(self,event):
        self._origin_x = event.x
        self._origin_y = event.y
        self._canvas.bind("<Motion>", self.mouse_motion)

    def mouse_released(self,event):
        self._canvas.unbind("<Motion>")


    def mouse_motion(self, event):
        self._master.move(self._origin_x - event.x, self._origin_y - event.y)



