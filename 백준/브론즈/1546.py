"""
작성일: 2025-07-16
메모리: 108384 KB
시간: 	120 ms
"""
import sys
n = int(sys.stdin.readline())
scores = list(map(int, sys.stdin.readline().strip().split()))
max_score = max(scores)
total_score = 0
for score in scores:
    total_score += score/max_score*100
print(total_score/n)