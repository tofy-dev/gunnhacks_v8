class Movable:

    # We will need to add some sort of Board variable too
    def __init_(self, t_x, t_y, t_dx, t_dy, t_type):
        self._x = t_x
        self._y = t_y
        self._dx = t_dx
        self._dy = t_dy
        self._type = t_type

    # -------------------------------------------
    # Getter's and Setter's
    @property
    def x(self):
        return self._x

    # This should only be used by the move() function
    @x.setter
    def x(self, t_x):
        self._x = t_x

        # Getter's and Setter's

    @property
    def y(self):
        return self._y

    # This should only be used by the move() function
    @y.setter
    def x(self, t_y):
        self._y = t_y

        # Getter's and Setter's

    @property
    def dx(self):
        return self._dx

    # This should only be used by the move() function
    @dx.setter
    def dx(self, t_dx):
        self._dx = t_dx

    @property
    def dy(self):
        return self._dy

    # This should only be used by the move() function
    @dy.setter
    def dy(self, t_dy):
        self._dy = t_dy

    @property
    def type(self):
        return self._type
    # Getter's and Setter's
    # -------------------------------------------

    def draw(self):
        # Skyler: Implement This

    def notifyIsHit(self, movable):
        # Implement later

    def remove(self):
        # Implement later

    # Some children classes will override this
    def move(self):
        self.x += self.dx
        self.y += self.dy
