"""
작성일: 2025-08-13
메모리: 125612 KB
시간: 	324 ms
"""
import sys

n = int(sys.stdin.readline())
timetable = [tuple(map(int, sys.stdin.readline().split())) for _ in range(n)]

# 종료 시간 기준으로 정렬
timetable.sort(key=lambda x: (x[1], x[0]))
print(timetable)

count = 0
end_time = 0

for start, end in timetable:
    if start >= end_time:
        count += 1
        end_time = end

print(count)