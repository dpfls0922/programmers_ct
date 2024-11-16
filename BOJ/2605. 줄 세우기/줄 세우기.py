n = int(input())
nums = list(map(int, input().split()))

order = []
for i in range(n):
    order.insert(nums[i], i + 1)

order.reverse()
print(order)