"""
2×n 타일링
작성일: 2025-08-02
메모리: 108384 KB
시간:  96 ms

dp[3] = 3
dp[4] = 5
dp[5] = 8
dp[6] = 13
dp[7] = 21 
"""
import sys
n = int(sys.stdin.readline())
dp = [0]*1001
dp[1] = 1
dp[2] = 2

for i in range(3, 1001):
    dp[i] = dp[i-1]+dp[i-2]
print(dp[n]%10007)