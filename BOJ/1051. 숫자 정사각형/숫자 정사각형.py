n, m = map(int, input().split())

board = [list(map(int, input())) for _ in range(n)]

size = min(n, m)
i = 0
j = 0

answer = []
while size:
    if board[i][j] == board[i][j + size - 1] == board[i + size - 1][j] == board[i + size - 1][j + size - 1]:
        answer.append(size * size)
    j += 1

    if j + size - 1 >= m:
        i += 1
        j = 0
    
    if i + size - 1 >= n:
        size -= 1
        i = 0
        j = 0

print(max(answer))