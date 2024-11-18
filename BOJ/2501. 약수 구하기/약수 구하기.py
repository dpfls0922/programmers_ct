from math import sqrt

n, k = map(int, input().split())

factor = set()
for i in range(1, n + 1):
    if n % i == 0:
        factor.add(n // i)

factor = sorted(factor)
if len(factor) < k:
    print(0)
else:
    print(factor[k - 1])