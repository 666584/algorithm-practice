"""
작성일: 2025-08-13
메모리: 254172 KB
시간: 	1064 ms
"""
import sys
n = int(sys.stdin.readline())
timetable = set()
for _ in range(n):
    i, j = map(int, sys.stdin.readline().strip().split())
    timetable.add((i,j))

print(timetable)
def count(start_time, num):
    print("update timetable")
    print(timetable)
    valid = 0
    new_start_time = 0
    hour = float("inf")
    curr_i = float("inf")
    for i, j in timetable:
        print(i,j)
        if i >= start_time:
            valid = 1
            new_hour = j - start_time
            if new_hour <= hour:
                if new_hour == hour:
                    if i < curr_i:
                        curr_i = i
                        hour = new_hour
                        new_start_time = j
                        print(i, j)
                else:
                    hour = new_hour
                    new_start_time = j
                    curr_i = i
                    print(i, j)
    if valid == 1:
        num += 1
        print("new_start_time")
        print(new_start_time)
        print(curr_i, new_start_time)
        timetable.remove((curr_i, new_start_time))
        return count(new_start_time, num)
    else:
        return num
    
print(count(0, 0))
