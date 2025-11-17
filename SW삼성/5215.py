T = int(input())

# max 가 아닌 하나의 방법을 찾는 함수.
def find_max_score(sorted_burgers, burgers, visited, result, remain, prev_result):
    for t in sorted_burgers:
        c = burgers[t]
        print(c)
        if c not in visited:
            if remain - c < 0:
                return find_max_score(sorted_burgers, burgers, visited, remain, prev_result)
            elif remain - c == 0:
                visited.append(c)
                if result >= prev_result:
                    return result + t
            else: 
                visited.append(c) 
                result += t
                remain -= c
                return find_max_score(sorted_burgers, burgers, visited, result, remain)

for i in range(T):
    N, L = map(int, input().strip().split())
    burgers = {}
    for j in range(N):
        # t -> taste, k -> 칼로리
        t, k = map(int, input().strip().split())
        burgers[t] = k
    sorted_burgers = sorted(burgers, reverse=True)
    print(burgers)
    visited = []
    result = 0
    remain = L
    prev_result = 0
    print(find_max_score(sorted_burgers, burgers, visited, result, remain, prev_result))
    