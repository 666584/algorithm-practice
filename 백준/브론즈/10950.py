"""
작성일: 2025-07-15
메모리: 108384 KB
시간: 	92 ms
"""
import sys
n = int(sys.stdin.readline())
for _ in range(n):
    n1, n2 = map(int, sys.stdin.readline().split())
    print(int(n1 + n2))