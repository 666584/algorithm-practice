"""
작성일: 2025-10-23
메모리: 108384 KB
시간: 	88 ms
"""
import sys
line = sys.stdin.readline
n, k = map(int, line().strip().split())
money_list = []
for i in range(n):
    money_list.append(int(line()))

def find_min(k):
    min_n = 0
    for i in range(n-1,-1,-1):
        money = money_list[i]
        if k == 0:
            return min_n
        if k >= money:
            a = k // money
            k = k - a * money
            min_n += a
    return min_n
print(find_min(k))
