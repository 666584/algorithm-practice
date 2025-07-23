"""
1로 만들기/ dp
작성일: 2025-07-23
메모리: 118552 KB
시간: 112 ms
"""
import sys
input = sys.stdin.readline
n = int(input())
dp = [0]*(n+1)
# dp는 정수 i 를 1로 만드는 데 필요한 최소 연산 횟수
dp[1] = 0
for i in range(2, n+1):
    dp[i] = dp[i - 1] + 1
    if i % 2 == 0:
        dp[i] = min(dp[i], dp[i // 2] + 1)
    if i % 3 == 0:
        dp[i] = min(dp[i], dp[i // 3] + 1)
print(dp[n])