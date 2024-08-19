## 문제 설명
JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열입니다. 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됩니다. (첫 번째 입출력 예 참고)
문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.

## 제한 사항
- s는 길이 1 이상 200 이하인 문자열입니다.
- s는 알파벳과 숫자, 공백문자(" ")로 이루어져 있습니다.
    - 숫자는 단어의 첫 문자로만 나옵니다.
    - 숫자로만 이루어진 단어는 없습니다.
    - 공백문자가 연속해서 나올 수 있습니다.

## 입출력 예
|s|return|
|------|---|
|"3people unFollowed me"|"3people Unfollowed Me"|
|"for the last week"|"For The Last Week"|

## solution.py
``` python
def solution(s):
    words = s.split(' ')
    
    for i, word in enumerate(words):
        if word:
            words[i] = word[0].upper() + word[1:].lower()
    
    return ' '.join(words)
```
``` python
def solution(s):
    words = s.split(' ')
    
    for i in range(len(words)):
        if words[i]:
            words[i] = words[i].capitalize()
    
    return ' '.join(words)
```

## 문제 풀이
### s.split(' ') vs s.split() 비교
- s.split(' ')
    - 이 메서드는 정확히 한 칸의 공백 문자 ' '를 구분자로 사용하여 문자열을 분리한다.
    - 공백 문자가 연속으로 나올 경우, 빈 문자열 요소가 리스트에 포함된다.
    - 예를 들어, "hello world"를 s.split(' ')으로 분리하면, ['hello', '', '', 'world']가 된다.
- s.split()
    - 이 메서드는 모든 연속된 공백 문자를 구분자로 사용하여 문자열을 분리한다.
    - 연속된 공백 문자를 하나의 구분자로 취급하여 빈 문자열 요소가 리스트에 포함되지 않는다.
    - 예를 들어, "hello world"를 s.split()으로 분리하면, ['hello', 'world']가 된다.

``` python
s1 = "hello   world"
s2 = "  the   quick brown fox  "

# Using s.split(' ')
split1 = s1.split(' ')
split2 = s2.split(' ')

print(split1)  # ['hello', '', '', 'world']
print(split2)  # ['', '', 'the', '', '', 'quick', 'brown', 'fox', '', '']

# Using s.split()
split1 = s1.split()
split2 = s2.split()

print(split1)  # ['hello', 'world']
print(split2)  # ['the', 'quick', 'brown', 'fox']
```