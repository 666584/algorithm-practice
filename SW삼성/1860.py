"""
N명, M초, K개 붕어빵
N개의 도착 시간 초

2 2 1
3 2 
2초 1개
3초 0개 
4초 1개
"""
T = int(input())
for i in range(T):
	N, M, K = map(int, input().split())
	arrival_times = list(map(int, input().split()))
	arrival_times = sorted(arrival_times)
	is_possible = True
	last_time = 0
	for j in range(len(arrival_times)):
		curr_time = arrival_times[j]
		bread_num = (curr_time // M) * K - (j + 1)
		if bread_num < 0:
			is_possible = False
			break
	if is_possible:
		print(f"#{i+1} Possible")
	else:
		print(f"#{i+1} Impossible")
        