# 문제
# 보안 시스템은 열쇠가 아닌 암호로 동작하는 시스템이다.
# 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다.
# 또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다. 즉, ab는 가능성 있는 암호지만 bac는 그렇지 않다.
# 새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다.
# C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.
# 단, 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.

# 입력 예시
# 4 6
# a t c i s w

from itertools import combinations

L, C = map(int, input().split())
password = sorted(input().split())
candidates = list(map(''.join, combinations(password, 4)))

vowels = ['a', 'e', 'i', 'o', 'u']
for candidate in candidates:
    cnt_vowel = 0
    for char in candidate:
        if char in vowels:
            cnt_vowel += 1
    if cnt_vowel >= 1 and L - cnt_vowel >= 2:
        print(candidate)

# 참고) ''.join(my_tuple)을 사용하면 튜플을 문자열로 바꿀 수 있다