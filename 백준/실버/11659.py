"""
구간 합 구하기 4/ 누적 합
작성일: 2025-07-24
메모리: 121976 KB
시간: 164ms
"""
import sys
n, m = map(int, sys.stdin.readline().split())
numbers = list(map(int, sys.stdin.readline().strip().split()))
p = [0]*(n+1)

for i in range(1, n+1):
    p[i] = p[i-1] + numbers[i-1]

for _ in range(m):
    i, j = map(int, sys.stdin.readline().split())
    print(p[j] - p[i-1])