from enum import Enum

class State(Enum):
    CLOSED = 1
    INTERNAL = 2
    MINIMIZED = 3
    EXTERNAL = 4

class WindowState:
    def __init__(self):
        self._state = State.CLOSED
    
    @property
    def state(self):
        return self._state
    
    @state.setter
    def state(self, new_state):
        if isinstance(new_state, State):
            self._state = new_state
        else:
            raise ValueError("Invalid State")
