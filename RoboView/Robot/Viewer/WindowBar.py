import customtkinter as ctk
from tkinter import Canvas, LEFT, BOTTOM


class WindowBar:
    def __init__(self, root):
        self._frame = ctk.CTkFrame(
            master=root, corner_radius=0, fg_color='transparent', width=0, height=27)
        self._root = root
        self._internal_windows = []
        self._dict = {}
        self._frame.pack(side=BOTTOM, anchor='w')

    def add_window(self, internal_window):
        self._internal_windows.append(internal_window)
        self.render_windowbar()

    def render_windowbar(self):
        for widget in self._frame.winfo_children():
            widget.destroy()
        for intwin in self._internal_windows:
            canvas = Canvas(self._frame, bg='darkblue', height=27)
            canvas.create_text(
                10, 15, text=intwin._title._name, fill='WHITE', anchor='w', font=('Helvetica', '15', 'bold'))
            canvas.bind("<Button-1>", self.mouse_pressed)
            canvas.pack(side=LEFT)
            self._dict[canvas] = intwin

    def mouse_pressed(self, event):
        internal_window = self._dict[event.widget]
        internal_window.show_window()
        self._internal_windows.remove(internal_window)
        self._dict.pop(event.widget)
        self.render_windowbar()
