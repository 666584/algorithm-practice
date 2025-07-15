"""
작성일: 2025-07-15
메모리: 117336 KB
시간: 	128 ms
"""
import sys
from collections import deque

n = int(sys.stdin.readline())
queue = deque(range(1, n+1))

while len(queue) != 1:
    queue.popleft()
    queue.rotate(-1)

print(int(queue[0]))