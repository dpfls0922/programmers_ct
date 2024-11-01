tetromino = [
    [(0,0), (0,1), (1,0), (1,1)], # ㅁ
    [(0,0), (0,1), (0,2), (0,3)], # ㅡ
    [(0,0), (1,0), (2,0), (3,0)], # ㅣ
    [(0,0), (0,1), (0,2), (1,0)], 
    [(1,0), (1,1), (1,2), (0,2)],
    [(0,0), (1,0), (1,1), (1,2)], # ㄴ
    [(0,0), (0,1), (0,2), (1,2)], # ㄱ
    [(0,0), (1,0), (2,0), (2,1)],
    [(2,0), (2,1), (1,1), (0,1)],
    [(0,0), (0,1), (1,0), (2,0)], 
    [(0,0), (0,1), (1,1), (2,1)],
    [(0,0), (0,1), (0,2), (1,1)], # ㅜ
    [(1,0), (1,1), (1,2), (0,1)], # ㅗ
    [(0,0), (1,0), (2,0), (1,1)], # ㅏ
    [(1,0), (0,1), (1,1), (2,1)], # ㅓ
    [(1,0), (2,0), (0,1), (1,1)],
    [(0,0), (1,0), (1,1), (2,1)],
    [(1,0), (0,1), (1,1), (0,2)],
    [(0,0), (0,1), (1,1), (1,2)]
]

def add(x, y, max_sum, board):
    for i in range(19):
        sum = 0
        for j in range(4):
            next_x = x + tetromino[i][j][0]
            next_y = y + tetromino[i][j][1]
            if 0 <= next_x < len(board) and 0 <= next_y < len(board[0]):
                sum += board[next_x][next_y]
            else:
                break
            max_sum = max(sum, max_sum)
    return max_sum

def solve():
    tc = 0
    for board in boards:
        tc += 1
        max_sum = 0
        n = len(board)
        for i in range(n):
            for j in range(n):
                max_sum = add(i, j, max_sum, board)
        print(f"{tc}. {max_sum}")

boards = []
while True:
    n = int(input())
    if n == 0:
        break
    board = [list(map(int, input().split())) for _ in range(n)]
    boards.append(board)

solve()