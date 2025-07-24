"""
Adding 1s, 2s, and 3s: dp
작성일: 2025-07-23
메모리: 108384 KB
시간: 80 ms
"""
import sys
input = sys.stdin.readline
t = int(input())
dp = [0]*12
dp[1] = 1



for _ in range(t):
    print