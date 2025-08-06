"""
dfs
작성일: 2025-08-06
메모리: 169868 KB
시간: 620 ms
"""
import sys
from collections import defaultdict
input = sys.stdin.readline
n, m = map(int, input().strip().split())
graph = defaultdict(list)
for _ in range(m):
    a, b = map(int, input().strip().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs():
    keys = list(graph.keys())
    visited = []
    count = 0
    while keys:
        start = keys[0]
        visited.append(keys[0])
        stack = [start]
        keys.remove(start)
        count += 1
        while stack:
            node = stack.pop()
            for value in graph[node]:
                if value not in visited:
                    stack.append(value)
                    visited.append(value)
                    keys.remove(value)
    print((n - len(visited))+count)
dfs()