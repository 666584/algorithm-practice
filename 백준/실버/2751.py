"""
작성일: 2025-07-15
메모리: 207176 KB
시간: 740 ms
"""
import sys
n = int(sys.stdin.readline())
a = set()
for _ in range(n):
    a.add(int(sys.stdin.readline().strip()))
new_a = sorted(a)
for num in new_a:
    print(num)