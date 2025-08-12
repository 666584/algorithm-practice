"""
EKO/ 이분 탐색
작성일: 2025-08-03
메모리: 207176 KB
시간: 740 ms
모든 합 >= m
"""
import sys
n, m = map(int, sys.stdin.readline().strip().split())
heights = list(map(int, sys.stdin.readline().strip().split()))

heights.sort()
print(heights)
left_curr = heights[0]
left_score = 0
for i in range(1,n):
    left_score += heights[i] - left_curr
print(left_score)
right_score = 0
mid_score = (left_score + right_score)/2



"""20 17 15 10
20 15 10 17
20 : 안됨. 
17 : 안됨. 
15 : 됨. 

4 42 40 26 46
46: 안됨
42: 안됨
40: 안됨
26: 됨.
4: 됨.
26-40 사이임."""