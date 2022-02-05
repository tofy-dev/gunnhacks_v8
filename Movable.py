class Movable:
    # We will need to add some sort of Board variable too
    # def __init_(self, t_x, t_y, t_dx, t_dy, t_type, win):
    def __init_(self, t_x, t_y, t_dx, t_dy, win, img):
        self._x = t_x
        self._y = t_y
        self._dx = t_dx
        self._dy = t_dy

        self.win = win
        self.img = img
        self._type = null
   
    def draw(self):
        win.blit(self.img)

    def notifyIsHit(self, movable):
        # Implement later

    def remove(self):
        # Implement later

    # Some children classes will override this
    def move(self):
        self.x += self.dx
        self.y += self.dy

    # -------------------------------------------
    # Getter's and Setter's
    # -------------------------------------------
    @property
        def x(self):
        return self._x
    @x.setter
    def x(self, t_x):
        self._x = t_x

    @property
    def y(self):
        return self._y
    @y.setter
    def x(self, t_y):
        self._y = t_y

    @property
    def dx(self):
        return self._dx
    @dx.setter
    def dx(self, t_dx):
        self._dx = t_dx

    @property
    def dy(self):
        return self._dy
    @dy.setter
    def dy(self, t_dy):
        self._dy = t_dy

    @property
    def type(self):
        return self._type
    # -------------------------------------------
