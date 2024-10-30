x = int(input())

bars = [64]
while sum(bars) >= x:
    if sum(bars) == x:
        print(len(bars))
        break
    half_len = bars.pop(0) // 2
    if sum(bars) + half_len >= x:
        bars.append(half_len)
    else:
        bars.append(half_len)
        bars.append(half_len)
    bars.sort()