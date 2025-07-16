"""
작성일: 2025-07-16
메모리: 108384 KB
시간: 	116 ms
"""
import sys
n, k = map(int, sys.stdin.readline().split())
# 이항계수: (n k) = n!/ k!(n-k)!
def factorial(n):
    a = 1
    for i in range(1,n+1):
        a *= i
    return a
a = factorial(n)
b = factorial(k)
c = factorial(n-k)
print(int(a/(b*c)))
