T = 10

"""
한 빌딩에서 조망권이 좋은 세대 수:
현재 빌딩 - 주변 빌딩중 가장 세대수가 많은 빌딩
이 빌딩들의 총합
"""
def get_view_room(buildings, t):
    results = 0
    for i in range(2, t-2):
        pivot = buildings[i]
        surroundings = [buildings[i-1], buildings[i-2], buildings[i+1], buildings[i+2]]
        min_surr = max(surroundings)
        num_views = pivot - min_surr
        if num_views < 0:
            num_views = 0
        results += num_views
    return results

results = []
for _ in range(T):
    t = int(input())
    buildings = list(map(int, input().strip().split()))
    result = get_view_room(buildings, t)
    results.append(result)

i = 0
for result in results:
    print(f"#{i + 1} {result}")
    i += 1