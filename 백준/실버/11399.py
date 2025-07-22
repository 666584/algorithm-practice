"""
ATM/구현
작성일: 2025-07-22
메모리: 108384 KB
시간: 96 ms
"""
import sys
num = int(sys.stdin.readline())
p = list(map(int, sys.stdin.readline().strip().split()))
p.sort()
total_count = 0
curr_count = 0
for i in range(num):
    curr_count += p[i]
    total_count += curr_count
print(total_count)