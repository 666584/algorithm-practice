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
print(heights)
heights.sort(reverse=True)
for i in range(1, n):
    curr = heights[i]-1