"""
작성일: 2025-07-16
메모리: 109544 KB
시간: 	132 ms
"""
import sys
a, b = map(int, sys.stdin.readline().split())
x = 1
for i in range(1, min(a,b)+1):
    if a % i == 0 and b % i == 0:
        new_x = i
        x = max(x, i)
print(x)
j = 1
while True:
    if (b * j) % a == 0:
        print(b * j)
        break
    elif (a * j) % b == 0:
        print(a * j)
        break
    j += 1