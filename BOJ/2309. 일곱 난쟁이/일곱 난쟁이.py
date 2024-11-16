heights = [int(input()) for _ in range(9)]
heights.sort()

found = False
for i in range(len(heights) - 1):
    for j in range(i + 1, len(heights)):
        if sum(heights) - heights[i] - heights[j] == 100:
            result = [heights[_] for _ in range(len(heights)) if _ != i and _ != j]
            found = True
            break
    if found:
        break

for i in range(len(result)):
    print(result[i])