from math import sqrt

m = int(input())
n = int(input())

isPrime = [True] * (n + 1)
isPrime[0] = isPrime[1] = False

for i in range(2, int(sqrt(n)+ 1)):
    if isPrime[i]:
        for muliple in range(i * i, n + 1, i):
            isPrime[muliple] = False

primes = [num for num, prime in enumerate(isPrime) if prime]


answer = []
for prime in primes:
    if prime >= m:
        answer.append(prime)
if len(answer) == 0:
    print(-1)
else:
    print(sum(answer))
    print(answer[0])