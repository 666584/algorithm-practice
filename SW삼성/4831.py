T = int(input())
"""
0-N
N: 종점
K: 한번 충전으로 이동할 수 있는 거리
0+k -> no charger then find charger
found charger: start from there and find charger until the bus reaches the end

"""
def find_near_charger(charge_stops, curr_stop, N):
    charge_stops = sorted(charge_stops, reverse=True)
    for charger in charge_stops:
        if charger > curr_stop and charger - curr_stop <= K:
            return charger
    return 0

for i in range(T):
    K, N, M = map(int, input().split())
    charge_stops = list(map(int, input().split()))
    curr_stop = 0
    charge_count = 0
    while curr_stop < N:
        if curr_stop + K >= N:
            break
        if curr_stop + K in charge_stops:
            if curr_stop + K < N:
                charge_count += 1   
            curr_stop += K	
        else: 
            charge_stop = find_near_charger(charge_stops, curr_stop, N)
            if charge_stop == 0:
                charge_count = 0
                break
            else: 
                charge_count += 1
                curr_stop = charge_stop
    print(f"#{i+1} {charge_count}")
            
            
        