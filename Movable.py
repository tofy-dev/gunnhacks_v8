class Movable:
    # We will need to add some sort of Board variable too
    # def __init_(self, t_x, t_y, t_dx, t_dy, t_type, win):
    def __init__(self, t_x, t_y, t_dx, t_dy, img, win):
        self._x = t_x
        self._y = t_y
        self._dx = t_dx
        self._dy = t_dy

        self._img = img
        self._win = win
        self._type = "placeholder"
    
    # -------------------------------------------
    # Getters and Setters
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
    def y(self, t_y):
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
    def img(self):
        return self._img
    @img.setter
    def img(self, t_dy):
        self._img = t_img

    @property
    def win(self):
        return self._win
    
    @property
    def type(self):
        return self._type
    # -------------------------------------------

    def draw(self):
        self.win.blit(self.img, (self.x, self.y))

    def notifyIsHit(self, movable):
        print("Implement later")

    def remove(self):
        print("Implement later")

    def move(self):
        self.x += self.dx
        self.y += self.dy