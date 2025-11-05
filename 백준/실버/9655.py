"""
DP
시간: 100ms
"""
import sys
n = int(sys.stdin.readline())
dp = [1, 3]
"""dp[1] = (2, 4, 6)
dp[2] = (3, 5, 7, 9)
dp[3] = (4, 6, 8, 10, 12)
dp[4] = (5, 7, 9, 11, 13, 15)"""

def find_won(n):
    i = 0
    while True:
        if n in dp:
            return i
        for j in range(len(dp)-1):
            dp[j] = dp[j] + 1              
        dp.append(dp[j + 1] + 3)
        dp[j + 1] = dp[j + 1] + 1
        i += 1

won = find_won(n)
if won%2 == 0:
    print("SK")
else:
    print("CY")