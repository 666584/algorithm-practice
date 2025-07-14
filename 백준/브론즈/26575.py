"""
n : number of lines
d: number of dogs
f: amount of food per dog in pound
p: price of food per pound
작성일: 2025-07-14
메모리: 108384 KB
시간: 	88 ms
"""

n_line = int(input())
for _ in range(n_line):
    d, f, p = map(float, input().split())
    total = d * f * p
    print(f"${total:.2f}") 