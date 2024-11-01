## 문제
# 창문을 닦아 최대 이익을 얻을 수 있는 경우를 구하기
# costX와 costY는 오른쪽과 아래로 이동하는 데 드는 비용이고, costs 배열에는 각 칸을 닦을 때 얻을 수 있는 이익이 저장됨

def max_profit(costs, costX, costY):
    rows, cols = len(costs), len(costs[0])
    earn = [[0] * cols for _ in range(rows)]
    earn[0][0] = costs[0][0]

    for j in range(1, cols):
        earn[0][j] = earn[0][j - 1] + costs[0][j] - costX

    for i in range(1, rows):
        earn[i][0] = earn[i - 1][0] + costs[i][0] - costY

    # DP 테이블 채우기
    for i in range(1, rows):
        for j in range(1, cols):
            # 오른쪽으로 이동하는 경우와 아래로 이동하는 경우 중 최대 이익 선택
            earn[i][j] = max(
                earn[i][j - 1] + costs[i][j] - costX,
                earn[i - 1][j] + costs[i][j] - costY
            )

    return earn[-1][-1]

costs = [
    [5, 3, 2],
    [1, 4, 1],
    [1, 5, 3]
]
costX = 1
costY = 2

print(max_profit(costs, costX, costY))