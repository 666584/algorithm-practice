"""
DP
"""
import sys

dp = [[0] * 14]
dp = [x for x in range(15)]

dp[1]
dp[1] + dp[2]
dp[2] + dp[3]

def get_no_residents(k, n):
    new_dp = dp.copy()    
    for i in range(k):
        for j in range(1,15):
            new_dp[j] = new_dp[j-1] + new_dp[j]        
    return new_dp[n]

t = int(sys.stdin.readline())
for i in range(t):
    k = int(sys.stdin.readline())
    n = int(sys.stdin.readline())
    print(get_no_residents(k, n))