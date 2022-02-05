import pygame
from pygame.locals import *
pygame.display.set_caption("AstBlast")

# window information
WIDTH, HEIGHT = 900, 500
WIN = pygame.display.set_mode((WIDTH, HEIGHT))

# colors
BLACK = (0, 0, 0)

def draw_window():
    WIN.fill(BLACK)
    pygame.display.update()

def main():
    run = True
    while run:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
        
        draw_window()
    
    pygame.quit()

if __name__ == "__main__":
    main()