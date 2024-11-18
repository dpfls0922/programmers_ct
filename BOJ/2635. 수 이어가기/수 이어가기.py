n = int(input())

answer = [n]
for i in range(n + 1):
    result = [n, i]

    def continue_num(result):
        value = result[-2] - result[-1]
        if value >= 0:
            result.append(value)
            return continue_num(result)
        else:
            return result

    result = continue_num(result)
    if len(answer) < len(result):
        answer = result

print(len(answer))
print(*answer)