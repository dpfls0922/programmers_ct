# 문제: m 이상 n 이하의 소수를 모두 출력하는 프로그램 작성하기

from math import sqrt

m, n = map(int, input().split())

is_prime = [True] * (m + n)
for i in range(2, int(sqrt(n)) + 1):
    if is_prime[i]:
        j = 2
        while i * j <= n:
            is_prime[i * j] = False
            j += 1

for i in range(m, n + 1):
    if is_prime[i]:
        print(i)