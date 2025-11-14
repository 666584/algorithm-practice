T = 10

"""
한 빌딩에서 조망권이 좋은 세대 수:
양옆으로(2개) 숫자를 빼서 그중 가장 작은 수
"""
def get_view_room(buildings, t):
    results = 0
    for i in range(2, t-2):
        rooms = []
        pivot = buildings[i]
        surroundings = [-1, -2, 1, 2]
        for surrounding in surroundings:
            room = pivot - buildings[i + surrounding]
            if room >= 0:
                rooms.append(room)
        if rooms:
            result = min(rooms)
        else:
            result = 0
        results += result
    return results

for _ in range(T):
    t = int(input())
    buildings = list(map(int, input().strip().split()))
    print(buildings)
    result = get_view_room(buildings, t)
    print(result, "result")