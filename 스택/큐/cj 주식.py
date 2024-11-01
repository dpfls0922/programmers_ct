## 문제
# 주식 가격의 추이가 주어졌을 때, 연속적으로 증가하는 기간의 최댓값과 연속적으로 감소한 기간의 최솟값을 리스트 형식으로 리턴하라

def solution(prices):
    max_increase = 1
    max_decrease = 1
    current_increase = 1
    current_decrease = 1

    for i in range(1, len(prices)):
        if prices[i] > prices[i - 1]:
            current_increase += 1
            current_decrease = 1
            max_increase = max(max_increase, current_increase)
        elif prices[i] < prices[i - 1]:
            current_decrease += 1
            current_increase = 1
            max_decrease = max(max_decrease, current_decrease)
        else:
            current_increase = 1
            current_decrease = 1

    return [max_increase, max_decrease]

print(solution([2, 1, 2, 3, 4, 4, 5, 4, 3]))
print(solution([5, 5, 5, 5, 5, 5]))
print(solution([1, 2, 3, 4, 5, 6]))
