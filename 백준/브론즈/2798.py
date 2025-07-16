"""
작성일: 2025-07-16
메모리: 109544 KB
시간: 	92 ms
"""
import sys
n, m = map(int, sys.stdin.readline().split())
cards = list(map(int, sys.stdin.readline().strip().split()))
# m 보다 크지 않는 3가지 카드의 최대 합
max_sum = 0
for i in range(n-2):
    for j in range(i+1, n-1):
        for z in range (j+1, n):
            sum = cards[i]+cards[j]+cards[z]
            if sum <= m:
                max_sum = max(sum, max_sum)
print(max_sum)

