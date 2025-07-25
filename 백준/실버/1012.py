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
cabbages = set()
visited = set()
total_visited_num = 0

def make_graph(k):
    for _ in range(k):
        x, y = map(int, input().strip().split())
        cabbages.add((x, y))

def bfs(start):
    queue = deque([start])
    cabbages.remove(start)
    visited.add(start)
    while queue:
        x, y = queue.popleft()
        for i, j in directions:
            nx = x + i
            ny = y + j
            if nx >= 0 and ny >= 0 and nx < m and ny < n:
                if (nx, ny) not in visited:
                    if (nx, ny) in cabbages:
                        queue.append((nx, ny))
                        visited.add((nx,ny))
                        cabbages.remove((nx, ny))
    return len(visited)

for _ in range(t):
    cabbages.clear()
    visited.clear()
    visited_num = 0
    count = 0
    m, n, k = map(int, input().strip().split())
    make_graph(k)
    while visited_num != k:
        visited_num = bfs(list(cabbages)[0])
        count += 1
    print(count)