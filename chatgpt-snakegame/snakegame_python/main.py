import pygame
import sys
import random
import time

# 화면 크기 설정
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# 색상 설정
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GREEN = (0, 255, 0)
RED = (255, 0, 0)

# 초기화
pygame.init()
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Snake Game")
clock = pygame.time.Clock()

snake_size = 20
snake_speed = 15
snake_pos = [100, 50]

snake_body = [
    [100, 50],
    [90, 50],
    [80, 50],
]

food_pos = [random.randrange(1, (SCREEN_WIDTH // snake_size)) * snake_size,
            random.randrange(1, (SCREEN_HEIGHT // snake_size)) * snake_size]
food_spawn = True

direction = "RIGHT"
change_to = direction

score = 0


def game_over():
    font = pygame.font.Font(None, 75)
    text = font.render("Game Over", True, RED)
    screen.blit(text, [SCREEN_WIDTH // 2 - 130, SCREEN_HEIGHT // 2 - 40])
    pygame.display.flip()
    time.sleep(2)
    pygame.quit()
    sys.exit()

def euclidean_distance(pos1, pos2):
    return ((pos1[0] - pos2[0]) ** 2 + (pos1[1] - pos2[1]) ** 2) ** 0.5

def display_score_and_title():
    title_font = pygame.font.Font(None, 36)
    title = title_font.render("Snake Game", True, WHITE)
    title_pos = (10, 10)
    screen.blit(title, title_pos)

    score_font = pygame.font.Font(None, 24)
    score_text = score_font.render(f"Score: {score}", True, WHITE)
    score_pos = (SCREEN_WIDTH - 100, 10)
    screen.blit(score_text, score_pos)

while True:
    for event in pygame.event.get():
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP:
                change_to = "UP"
            if event.key == pygame.K_DOWN:
                change_to = "DOWN"
            if event.key == pygame.K_LEFT:
                change_to = "LEFT"
            if event.key == pygame.K_RIGHT:
                change_to = "RIGHT"

    if change_to == "UP" and direction != "DOWN":
        direction = "UP"
    if change_to == "DOWN" and direction != "UP":
        direction = "DOWN"
    if change_to == "LEFT" and direction != "RIGHT":
        direction = "LEFT"
    if change_to == "RIGHT" and direction != "LEFT":
        direction = "RIGHT"

    if direction == "UP":
        snake_pos[1] -= snake_size
    if direction == "DOWN":
        snake_pos[1] += snake_size
    if direction == "LEFT":
        snake_pos[0] -= snake_size
    if direction == "RIGHT":
        snake_pos[0] += snake_size

    snake_body.insert(0, list(snake_pos))
    print("food_pos:", food_pos)
    print("snake_pos:", snake_pos)
    if euclidean_distance(snake_pos, food_pos) < snake_size:
        print("bingo")
        score += 10
        food_spawn = False
    else:
        snake_body.pop()

    if not food_spawn:
        food_pos = [random.randrange(1, (SCREEN_WIDTH // snake_size)) * snake_size,
                    random.randrange(1, (SCREEN_HEIGHT // snake_size)) * snake_size]
    food_spawn = True

    screen.fill(BLACK)
    for pos in snake_body:
        pygame.draw.rect(screen, GREEN, pygame.Rect(pos[0], pos[1], snake_size, snake_size))
    pygame.draw.rect(screen, RED, pygame.Rect(food_pos[0], food_pos[1], snake_size, snake_size))

    display_score_and_title()

    if snake_pos[0] < 0 or snake_pos[0] > SCREEN_WIDTH-snake_size or snake_pos[1] < 0 or snake_pos[1] > SCREEN_HEIGHT-snake_size:
        game_over()

    for block in snake_body[1:]:
        if snake_pos == block:
            game_over()

    pygame.display.flip()
    clock.tick(snake_speed)
