"""
유기농 배추 / bfs
작성일: 2025-07-25
메모리: 114380 KB
시간: 148 ms
"""
import sys
from collections import deque
input = sys.stdin.readline
directions = ([1,0],[0,1],[0,-1],[-1,0])
t = int(input())

def bfs(start, cabbages):
    queue = deque([start])
    cabbages.remove(start)
    while queue:
        x, y = queue.popleft()
        for i, j in directions:
            nx = x + i
            ny = y + j
            if nx >= 0 and ny >= 0 and nx < m and ny < n:
                if (nx, ny) in cabbages:
                    queue.append((nx, ny))
                    cabbages.remove((nx, ny))

for _ in range(t):
    count = 0
    m, n, k = map(int, input().strip().split())
    cabbages = set(tuple(map(int, input().split())) for _ in range(k))

    while cabbages:
        bfs(list(cabbages)[0], cabbages)
        count += 1  
    print(count)