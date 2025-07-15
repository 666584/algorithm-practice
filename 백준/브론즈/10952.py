"""
작성일: 2025-07-15
메모리: 108384 KB
시간: 	92 ms
"""
import sys
while True:
    n1, n2 = map(int, sys.stdin.readline().split())
    if n1 == 0 and n2 == 0:
        break
    print(int(n1 + n2))