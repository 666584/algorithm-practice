"""
EKO/ 이분 탐색
작성일: 2025-08-12
메모리: 258372 KB
시간: 432 ms
모든 합 >= m
"""
import sys
n, m = map(int, sys.stdin.readline().strip().split())
heights = list(map(int, sys.stdin.readline().strip().split()))

heights.sort(reverse=True)
first_left = heights[0]
first_right = 0

def binary_search(left, right):
    mid = int((left+right) / 2)
    mid_score = 0
    if left - right == 1:
        left_score = 0
        for height in heights:
            if height <= left:
                break
            left_score += height - left
        if left_score >= m:
            return left
        return right
    elif left == right:
        return left
    for height in heights:
        if height <= mid:
            break
        mid_score += height - mid

    if m <= mid_score:
        right = mid
        return binary_search(left, right)
    elif m > mid_score:
        left = mid - 1
        return binary_search(left, right)

print(binary_search(first_left, first_right))