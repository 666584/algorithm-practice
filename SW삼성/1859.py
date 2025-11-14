# 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
# 아래 표준 입출력 예제 필요시 참고하세요.

# 표준 입력 예제
'''
a = int(input())                        정수형 변수 1개 입력 받는 예제
b, c = map(int, input().split())        정수형 변수 2개 입력 받는 예제 
d = float(input())                      실수형 변수 1개 입력 받는 예제
e, f, g = map(float, input().split())   실수형 변수 3개 입력 받는 예제
h = input()                             문자열 변수 1개 입력 받는 예제
'''

# 표준 출력 예제
'''
a, b = 6, 3
c, d, e = 1.0, 2.5, 3.4
f = "ABC"
print(a)                                정수형 변수 1개 출력하는 예제
print(b, end = " ")                     줄바꿈 하지 않고 정수형 변수와 공백을 출력하는 예제
print(c, d, e)                          실수형 변수 3개 출력하는 예제
print(f)                                문자열 1개 출력하는 예제
'''




'''
아래의 구문은 input.txt 를 read only 형식으로 연 후,
앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
아래 구문을 이용하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 구문을 사용하셔도 좋습니다.
아래 구문을 사용하기 위해서는 import sys가 필요합니다.
단, 채점을 위해 코드를 제출하실 때에는 반드시 아래 구문을 지우거나 주석 처리 하셔야 합니다.
'''
#import sys
#sys.stdin = open("input.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.

# 2 1 2 1 10 : 이 케이스의 경우에는 마지막에 파는 것이 좋다.
# 1 6 1 5 1 : 6,5일때 파는 것이 좋다.
"""
가장 큰 값 전까지 사다가 가장 큰 값에서 팔고 
그 다음으로 가장 큰 값에 파는 것을 반복한다.
"""
def find_best_cost(start, days, costs, result):
    if start == days:
        return result
    
    i = days - 1
    max_cost = 0
    max_cost_i = 0
    while i >= start:
        if costs[i] >= max_cost:
            max_cost = costs[i]
            max_cost_i = i
        i -= 1
    buy = 0
    buy_i = 0
    for i in range(start, max_cost_i):
        print(i, "i")
        buy += costs[i]
        buy_i += 1
    profit = max_cost * buy_i - buy
    result += profit
    return find_best_cost(max_cost_i + 1, days, costs, result)

results = []
for test_case in range(1, T + 1):
    days = int(input())
    costs = list(map(int, input().strip().split()))
    result = 0
    r = find_best_cost(0, days, costs, result)
    results.append(r)

for i in range(T):
    print(f"#{i + 1} {results[i]}")