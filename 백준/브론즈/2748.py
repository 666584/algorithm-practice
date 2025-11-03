"""
DP
Fn = Fn-1 + Fn-2 (n>=2)
피보나치 수
시간: 88 ms
list 생성하는 법
"""

import sys

dp = [0]* 91
dp[0] = 0
dp[1] = 1

for i in range(2, 91):
    dp[i] = dp[i-1] + dp[i-2]

n = int(sys.stdin.readline())
print(dp[n])


