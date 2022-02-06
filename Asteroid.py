class Asteroid(Movable){
    def __init_(self, t_x, t_y, t_dx, t_dy, t_type, win, img, radius){
        super().__init__(self, t_x, t_y, t_dx, t_dy, win, img)
        self._x = t_x
        self._y = t_y
        self._dx = t_dx
        self._dy = t_dy
        self._type = t_type
    }
    
    @radius.setter
    def setRadius(self, t_radius){
        self._radius = t_radius
    }

    @property
    def setRadius(self){
        return self._radius
    }

    def notifyIfHit(){

    }

    def remove(){

    }
}


