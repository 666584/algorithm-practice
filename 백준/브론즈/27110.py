"""
간단 구현
작성일: 2025-07-25
메모리: 108384 KB
시간: 	92 ms
"""
import sys
input = sys.stdin.readline
n = int(input())
total = 0
chickens = list(map(int, input().strip().split()))
for chicken in chickens:
    if chicken > n:
        total += n
    else:
        total += chicken
print(total) 