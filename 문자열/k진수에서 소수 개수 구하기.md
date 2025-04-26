## 문제 설명
양의 정수 n이 주어집니다. 이 숫자를 k진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.

- 0P0처럼 소수 양쪽에 0이 있는 경우
- P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
- 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
- P처럼 소수 양쪽에 아무것도 없는 경우
- 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
    - 예를 들어, 101은 P가 될 수 없습니다.

예를 들어, 437674을 3진수로 바꾸면 211020101011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. (211, 2, 11을 k진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.) 211은 P0 형태에서 찾을 수 있으며, 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.

정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록 solution 함수를 완성해 주세요.


## 제한 사항
- 1 ≤ n ≤ 1,000,000
- 3 ≤ k ≤ 10

## 입출력 예
|n|k|return|
|------|---|---|
|437674|3|3|
|110011|10|2|
	
### 입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

110011을 10진수로 바꾸면 110011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 11, 11 2개입니다. 이와 같이, 중복되는 소수를 발견하더라도 모두 따로 세어야 합니다.

## solution.java
``` java
class Solution {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();

        String converted = Integer.toString(n, k); // k진법으로 변환

        // while (n > 0) {
        //     sb.append(n % k);
        //     n = n / k;
        // }
        // // 나머지들을 뒤집어야 함
        // System.out.println(sb.reverse().toString());
        
        String[] parts = converted.split("0");
        
        int count = 0;
        for (String part : parts) {
            if (part.isEmpty()) continue;
            long num = Long.parseLong(part);
            if (isPrime(num)) count++;
        }
        return count;
    }
    
    boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
```

## 주의할 점
- part.isEmpty()를 처리하기
    -  split("0")을 사용할 때 입력 문자열에 `연속된 0 또는 양 끝에 0이 있을 경우`, `빈 문자열("")`이 포함되기 때문이다.


예제로 이해해보기
```java
String converted = "0110";
String[] parts = converted.split("0");
```

이렇게 하면 parts는 다음과 같이 나온다.
``` java
["", "11", ""]
```

"0110" → 0으로 나누면
앞쪽 "0" → 앞에 "" (빈 문자열)
가운데 "11"
마지막 "0" → 뒤에 "" (빈 문자열)

그래서 `isEmpty()` 체크가 필요하다.