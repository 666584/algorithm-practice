"""
작성일: 2025-07-16
메모리: 108384 KB
시간: 	116 ms
"""
import sys
n = int(sys.stdin.readline())
num_shirts = list(map(int, sys.stdin.readline().strip().split()))
t, p = map(int, sys.stdin.readline().strip().split())
count_shirt = 0

for shirt in num_shirts:
    if int(shirt) != 0:
        if int(shirt)%t != 0:
            count_shirt += int(int(shirt)/t) + 1
        else:
            count_shirt += int(int(shirt)/t)
print(count_shirt)

if p == 0:
    print(0, n)
else:
    print(int(n/p), n%p)