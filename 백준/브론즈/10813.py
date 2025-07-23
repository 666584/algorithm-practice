"""
공 바꾸기/ 구현
작성일: 2025-07-22
메모리: 108384 KB
시간: 	92 ms
"""
import sys
input = sys.stdin.readline
n, m = map(int, input().strip().split())
basket = list(i for i in range(1,n+1))
print(basket)
for _ in range(m):
    i, j = map(int, input().strip().split())
    new_i = basket[j-1]
    new_j = basket[i-1]
    basket[i-1] = new_i
    basket[j-1] = new_j

for ball in basket:
    print(ball, end=" ")