# solved
T = int(input())

def dfs(idx, taste_sum, cal_sum, burgers):
        # 칼로리 초과면 이 경로는 무효
        if cal_sum > L:
            return 0

        # 모든 햄버거를 다 봤으면, 지금까지의 맛 합이 하나의 후보
        if idx == N:
            return taste_sum

        t, k = burgers[idx]

        # 1) idx 번째 햄버거를 먹는 경우
        take = dfs(idx + 1, taste_sum + t, cal_sum + k, burgers)

        # 2) idx 번째 햄버거를 안 먹는 경우
        skip = dfs(idx + 1, taste_sum, cal_sum, burgers)

        # 둘 중 더 큰 맛 점수를 반환
        return max(take, skip)

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
"""
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
"""
for _ in range(T):
    N, L = map(int, input().split())   # 햄버거 개수, 칼로리 제한
    burgers = []  # (taste, cal) 리스트로 저장

    for _ in range(N):
        t, k = map(int, input().split())  # 맛(t), 칼로리(k)
        burgers.append((t, k))   
    print(dfs(0, 0, 0, burgers), "here")