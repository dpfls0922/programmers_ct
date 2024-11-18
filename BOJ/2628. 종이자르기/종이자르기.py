w, h = map(int, input().split())
count = int(input())
cutting = [list(map(int, input().split())) for i in range(count)]

max_area = 0
width = [0, w]
height = [0, h]
for cut in cutting:
    direction, number = cut[0], cut[1]
    if direction == 1:
        width.append(number)
    elif direction == 0:
        height.append(number)

diff_width = []
width.sort(reverse=True)
for i in range(len(width) - 1):
    diff_width.append(width[i] - width[i + 1])

diff_height = []
height.sort(reverse=True)
for i in range(len(height) - 1):
    diff_height.append(height[i] - height[i + 1])

print(max(diff_width) * max(diff_height))