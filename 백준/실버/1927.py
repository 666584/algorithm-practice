"""
작성일: 2025-08-02
메모리: 114200 KB
시간: 172 ms
"""
import sys
import heapq

n = int(sys.stdin.readline())
heap = []

for _ in range(n):
    new = int(sys.stdin.readline())
    if new == 0:
        if heap:
            min_value = heapq.heappop(heap)
            print(min_value)
        else:
            print(0)
    else:
        heapq.heappush(heap, new)