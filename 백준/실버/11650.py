"""
작성일: 2025-07-15
메모리: 121516 KB
시간: 368 ms
"""
import sys
n = int(sys.stdin.readline())
n_set = set()
for _ in range(n):
    x, y = map(int, sys.stdin.readline().split())
    n_set.add((x, y))
new_set = sorted(n_set)

for x, y in new_set:
    print(x, y)