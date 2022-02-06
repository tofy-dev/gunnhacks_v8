class Player(Movable):

    def __init__(self, t_x, t_y, t_dx, t_dy, t_type):
        super().__init__(self, t_x, t_y, t_dx, t_dy, t_type)
        # Is the angle the player is facing in radians
        angle = 0
        # speed replaces the job that dx and dy does
        self._speed = (t_dy + t_dy)//2
        self._lives = 3


    @override
    def move(self):
        self.dx = ((self.speed ** 2)//(1 + math.tan(self.angle) ** 2)) ** 0.5
        self.dy = dx * math.tan(self.angle)
        super.move()

        self.x += self.dx
        self.y += self.dy

    @override
    def move(self):
        self.lives -= 1
        if self.lives <=0:
            

    # -------------------------------------------
    # Getter's and Setter's
    # -------------------------------------------
    @angle.setter
    def angle(self, t_angle):
        self._angle = t_d

    @property
    def angle(self):
        return self._angle

    @speed.setter
    def speed(self, t_speed):
        self._speed = t_speed

    @property
    def speed(self):
        return self._speed
    # -------------------------------------------
    # Getter's and Setter's
    # -------------------------------------------