import pygame
import os
import Movable

# game information
WIDTH, HEIGHT = 900, 500
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
FPS = 60
pygame.display.set_caption("AstBlast")

# visuals
BLACK = (0, 0, 0)
SPACESHIP_IMG = pygame.image.load(os.path.join('sprites', 'player.png'))

# class instances
player = Movable(WIDTH/2, HEIGHT/2, 10, 10, WIN, SPACESHIP_IMG)

# updates what's displayed on the screen
def draw_window():
    WIN.fill(BLACK)
    player.draw()
    pygame.display.update()

# runs the program
def main():
    clock = pygame.time.Clock()
    run = True
    while run:
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
        
        draw_window()
    
    pygame.quit()

if __name__ == "__main__":
    main()