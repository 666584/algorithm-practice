"""
bfs
작성일: 2025-08-06
메모리: 124768 KB
시간: 168 ms

n = start point
k = goal
walking: x-1 or x+1
Teleporting: 2*x
"""
import sys
from collections import deque
n, k = map(int, sys.stdin.readline().strip().split())
def bfs(start, goal):
    queue = deque([(start, 0)])
    visited = set([start])
    while queue:
        number, time = queue.popleft()
        if number == goal:
            return time
        moves = [number - 1, number + 1, number * 2]
        for move in moves:
            if move >= 0 and move <= 100000 and move not in visited:
                queue.append((move, time+1))
                visited.add(move)   
print(bfs(n, k))
        