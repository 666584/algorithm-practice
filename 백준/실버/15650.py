"""
작성일: 2025-08-14
메모리: 287408 KB
시간: 	1000 ms
"""
import sys
input = sys.stdin.readline
n, m = map(int, input().strip().split())
for i in range(1, n+1):
    for j in range(m):
        print(i+j, end=" ")    
    print()
    i += 1