"""
Adding 1s, 2s, and 3s: dp
작성일: 2025-07-29
메모리: 108384 KB
시간: 88 ms
"""
import sys
input = sys.stdin.readline
t = int(input())
dp = [0]*12
dp[1] = 1
dp[2] = 2
dp[3] = 4
dp[4] = 7
i = 1
for j in range(5, 11):
    dp[j] = dp[j -1]*2 - dp[j-4]
for _ in range(t):
    n = int(input())
    print(dp[n])