from itertools import combinations

heights = [int(input()) for _ in range(9)]
heights.sort()

for combi in combinations(heights, 7):
    if sum(combi) == 100:
        for num in combi:
            print(num)
        break
