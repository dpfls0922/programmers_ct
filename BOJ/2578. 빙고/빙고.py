board = [list(map(int, input().split())) for _ in range(5)]
answer = [list(map(int, input().split())) for _ in range(5)]
check = [[False] * 5 for _ in range(5)]

def check_garo(check):
    count = 0
    for garo in check:
        if all(garo):
            count += 1
    return count

def check_sero(check):
    count = 0
    for i in range(5):
        if all(check[j][i] for j in range(5)):
            count += 1
    return count

def check_diag(check):
    count = 0
    if all(check[i][i] for i in range(5)):
        count += 1
    if all(check[i][4 - i] for i in range(5)):
        count += 1
    return count


def check_bingo(check):
    return check_garo(check) + check_sero(check) + check_diag(check)

count = 0
for i in range(5):
    for j in range(5):
        count += 1
        num = answer[i][j]
        for n in range(5):
            for m in range(5):
                if board[n][m] == num:
                    check[n][m] = True
        if check_bingo(check) >= 3:
            print(count)
            exit()