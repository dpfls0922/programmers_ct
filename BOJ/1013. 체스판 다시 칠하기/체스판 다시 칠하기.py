n, m = map(int, input().split())

board = [input() for _ in range(n)]

def paint(board, i, j, color):
    count = 0
    for l in range(i, i + 8):
        for m in range(j, j + 8):
            if l % 2 == 0:
                if m % 2 == 0:
                    if board[l][m] != color:
                        count += 1
                else:
                    if board[l][m] == color:
                        count += 1
            else:
                if m % 2 == 0:
                    if board[l][m] == color:
                        count += 1
                else:
                    if board[l][m] != color:
                        count += 1
    return count

min_count = float('inf')
for i in range(n - 7):
    for j in range(m - 7):
        min_count = min(min_count, paint(board, i, j, 'B'), paint(board, i, j, 'W'))

print(min_count)