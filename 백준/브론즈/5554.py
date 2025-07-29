"""
작성일: 2025-07-29
메모리: 108384 KB
시간: 84 ms
"""
import sys
total = 0
for _ in range(4):
    time = int(sys.stdin.readline())
    total += time
print(total//60)
print(total%60)