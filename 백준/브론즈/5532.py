"""
작성일: 2025-07-29
메모리: 108384 KB
시간: 88 ms
"""
import sys
input = sys.stdin.readline
l = int(input())
a = int(input())
b = int(input())
c = int(input())
d = int(input())
time = 0
if a % c != 0:
    time = (a // c) + 1
else: 
    time = a // c
if b % d != 0: 
    time = max(time, (b // d) + 1)
else:
    time = max(time, b // d)

print(l - time)