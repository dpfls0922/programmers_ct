n = int(input())

nums = list(map(int, input().split()))
operators = list(map(int, input().split()))

max_value = float('-inf')
min_value = float('inf')
def dfs(i, current):
    global max_value, min_value

    if i == n - 1:
        max_value = max(max_value, current)
        min_value = min(min_value, current)
        return

    if operators[0] != 0:
        operators[0] -= 1
        dfs(i + 1, current + nums[i + 1])
        operators[0] += 1
    if operators[1] != 0:
        operators[1] -= 1
        dfs(i + 1, current - nums[i + 1])
        operators[1] += 1
    if operators[2] != 0:
        operators[2] -= 1
        dfs(i + 1, current * nums[i + 1])
        operators[2] += 1
    if operators[3] != 0:
        operators[3] -= 1
        dfs(i + 1, int(current / nums[i + 1]))
        operators[3] += 1

dfs(0, nums[0])
print(max_value)
print(min_value)