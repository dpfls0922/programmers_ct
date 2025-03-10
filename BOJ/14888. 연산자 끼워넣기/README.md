## 문제 설명
N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.

예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 예를 들어, 아래와 같은 식을 만들 수 있다.

- 1+2+3-4×5÷6
- 1÷2+3+4-5×6
- 1+2÷3×4-5+6
- 1÷2×3-4+5+6

식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

- 1+2+3-4×5÷6 = 1
- 1÷2+3+4-5×6 = 12
- 1+2÷3×4-5+6 = 5
- 1÷2×3-4+5+6 = 7

N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

## 입력
첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.

## 출력
첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.

둘째 줄에 그 최대 개수의 수들을 차례대로 출력한다. 이들 수 사이에는 빈칸을 하나씩 둔다.

### 예제

예제 입력 1 <br>
2<br>
5 6<br>
0 0 1 0

예제 출력 1 <br>
30<br>
30

예제 입력 2 <br>
3<br>
3 4 5<br>
1 0 1 0

예제 출력 2 <br>
35<br>
17

예제 입력 3 <br>
6<br>
1 2 3 4 5 6<br>
2 1 1 1

예제 출력 3 <br>
54<br>
-24

## 문제풀이
### dfs와 백트래킹
- 백트래킹은 모든 가능한 경우를 탐색하는 과정에서 현재 상태를 변경했다가, 원래 상태로 복원해야 한다.

- 탐색과 복원
  - 상태 변경 후 탐색: operators[3] -= 1
  - 탐색 후 상태 복원: operators[3] += 1
  - 이를 통해 모든 연산자 조합을 확인할 수 있다.

- elif를 사용하지 않는 이유
  - elif는 이전 조건이 만족되지 않을 때만 실행되므로, 하나의 연산만 실행되고 다음 연산이 차단된다. 이 문제에서는 모든 연산자를 확인해야 하므로 if를 사용해야 합니다.

### 동작 과정 시각화
``` python
n = 3
nums = [3, 4, 5]
operators = [1, 1, 1, 1]  # +, -, *, /
```

코드 실행 흐름 (재귀 호출 및 백트래킹)
|단계|current|연산자 상태|호출 경로|연산|
|---|---|---|---|---|
|1|3|[1, 1, 1, 1]|dfs(0, 3)|시작값|
|2|7|[0, 1, 1, 1]|dfs(1, 7)|3 + 4|
|3|2|[0, 0, 1, 1]|dfs(2, 2)|7 - 5|
|4|10|[0, 0, 0, 1]|dfs(3, 10)|2 * 5|
|5|2|[0, 0, 1, 1]|백트래킹|복구|
|6|7|[0, 1, 1, 1]|백트래킹|복구|