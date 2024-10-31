from collections import deque

# 오른쪽, 아래, 위, 왼쪽
directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
current_direction = 0
def turn_right():
    global current_direction
    current_direction = (current_direction + 1) % 4

def turn_left():
    global current_direction
    current_direction = (current_direction - 1) % 4

def move_snake(head_position):
    dx, dy = directions[current_direction]
    nx = head_position[0] + dx
    ny = head_position[1] + dy
    return (nx, ny)

n = int(input())
k = int(input())

board = [[0] * n for _ in range(n)]

for i in range(k):
    x, y = map(int, input().split())
    board[x - 1][y - 1] = 1

l = int(input())
movement = {}
for i in range(l):
    sec, dir = input().split() 
    movement[int(sec)] = dir

snake = deque()
snake.append((0, 0))
head_position = (0, 0)
count = 0

while True:
    count += 1
    head_position = move_snake(head_position)
    if (head_position[0] < 0 or head_position[0] > n - 1) or (head_position[1] < 0 or head_position[1] > n - 1):
        break
    if head_position in snake:
        break

    if board[head_position[0]][head_position[1]] == 1:
        board[head_position[0]][head_position[1]] = 0
        snake.append(head_position)
    else:
        snake.append(head_position)
        snake.popleft()

    if count in movement.keys():
        if movement[count] == 'D':
            turn_right()
        elif movement[count] == 'L':
            turn_left()

print(count)