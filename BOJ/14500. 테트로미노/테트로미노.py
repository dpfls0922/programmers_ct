from collections import deque

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

tetromino = [
    [(0, 0), (0, 1), (1, 0), (1, 1)],
    [(0, 0), (0, 1), (0, 2), (0, 3)],
    [(0, 0), (1, 0), (2, 0), (3, 0)],
    [(0,0), (0,1), (0,2), (1,0)], 
    [(1,0), (1,1), (1,2), (0,2)],
    [(0,0), (1,0), (1,1), (1,2)],
    [(0,0), (0,1), (0,2), (1,2)],
    [(0,0), (1,0), (2,0), (2,1)],
    [(2,0), (2,1), (1,1), (0,1)],
    [(0,0), (0,1), (1,0), (2,0)], 
    [(0,0), (0,1), (1,1), (2,1)],
    [(0,0), (0,1), (0,2), (1,1)],
    [(1,0), (1,1), (1,2), (0,1)],
    [(0,0), (1,0), (2,0), (1,1)],
    [(1,0), (0,1), (1,1), (2,1)],
    [(1,0), (2,0), (0,1), (1,1)],
    [(0,0), (1,0), (1,1), (2,1)],
    [(1,0), (0,1), (1,1), (0,2)],
    [(0,0), (0,1), (1,1), (1,2)]
]

def add(x, y):
    global max_sum
    for i in range(19):
        sum = 0
        for j in range(4):
            try:
                next_x = x + tetromino[i][j][0]
                next_y = y + tetromino[i][j][1]
                sum += board[next_x][next_y]
            except IndexError:
                continue
        max_sum = max(sum, max_sum)

def solve():
    for i in range(n):
        for j in range(m):
            add(i, j)

max_sum = 0
solve()
print(max_sum)