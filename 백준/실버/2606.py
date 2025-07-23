"""
바이러스/ 그래프(bfs)
작성일: 2025-07-23
메모리: 110068 KB
시간: 108 ms
"""
import sys
from collections import defaultdict
from collections import deque
input = sys.stdin.readline
n_computer = int(input())
n_line = int(input())
graph = defaultdict(list)
for _ in range(n_line):
    a, b = map(int, input().strip().split())
    graph[a].append(b)
    graph[b].append(a)

def bfs():
    queue = deque([1])
    visited = set([1])
    while queue:
        node = queue.popleft()
        for key in graph[node]:
            if key not in visited:
                queue.append(key)
                visited.add(key)
    return len(visited) - 1

print(bfs())