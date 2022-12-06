import tkinter as tk
from tkinter import *
from tkinter import ttk
import customtkinter

from RoboView.Gui.InternalWindow.InternalWindow import InternalWindow

#root = Tk()
#frame = Frame(root,borderwidth=2)
#frame.config(highlightbackground = "red", highlightcolor= "red", bg = "GREY") 
#frame.place(height=500, width = 500)
#frame.pack()
#frame1 = Frame(root, height = 100, width = 200, bg = "RED", borderwidth=2)
#frame.place(height=1000, width = 1000, x=10, y=10)


root = tk.Tk()
container = ttk.Frame(root)
canvas = tk.Canvas(container)
scrollbar = ttk.Scrollbar(container, orient="vertical", command=canvas.yview)
scrollable_frame = ttk.Frame(canvas)

scrollable_frame.bind(
    "<Configure>",
    lambda e: canvas.configure(
        scrollregion=canvas.bbox("all")
    )
)

canvas.create_window((0, 0), window=scrollable_frame, anchor="nw")

canvas.configure(yscrollcommand=scrollbar.set)

for i in range(50):
    ttk.Label(scrollable_frame, text="Sample scrolling label").pack()

container.pack()
canvas.pack(side="left", fill="both", expand=True)
scrollbar.pack(side="right", fill="y")

class ScrollableFrame(ttk.Frame):
    def __init__(self, container, *args, **kwargs):
        super().__init__(container, *args, **kwargs)
        canvas = tk.Canvas(self)
        scrollbar = ttk.Scrollbar(self, orient="vertical", command=canvas.yview)
        self.scrollable_frame = ttk.Frame(canvas)

        self.scrollable_frame.bind(
            "<Configure>",
            lambda e: canvas.configure(
                scrollregion=canvas.bbox("all")
            )
        )

        canvas.create_window((0, 0), window=self.scrollable_frame, anchor="nw")

        canvas.configure(yscrollcommand=scrollbar.set)

        canvas.pack(side="left", fill="both", expand=True)
        scrollbar.pack(side="right", fill="y")

#window = InternalWindow(root, 100,100,300,300)
#window2 = InternalWindow(root, 100,100,200,200)

#window.set_min_dimension(100,100)
#window2.set_min_dimension(200,200)

#window.rename("Window 1")
#window2.rename("Window 2")



root.mainloop()

