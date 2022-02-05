import pygame
import os
from pygame.locals import *
pygame.display.set_caption("AstBlast")

# game information
WIDTH, HEIGHT = 900, 500
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
FPS = 60

# visuals
BLACK = (0, 0, 0)
SPACESHIP_IMG = pygame.image.load(os.path.join('sprites', 'player.png'))

# updates what's displayed on the screen
def draw_window():
    WIN.fill(BLACK)
    WIN.blit(SPACESHIP_IMG, (WIDTH/2, HEIGHT/2))
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