"""
dfs 순서
작성일: 2025-07-19
메모리: 148412 KB
시간: 428 ms
"""
import sys
from collections import defaultdict
n, m, r = map(int, sys.stdin.readline().strip().split())
graph = defaultdict(list)
for _ in range(m):
    u, v = map(int, sys.stdin.readline().strip().split())
    graph[u].append(v)
    graph[v].append(u)
for key in graph:
    graph[key].sort()

def dfs():
    visited = [False] * (n + 1)
    stack = [r]
    order_list = []
    while stack:
        node = stack.pop()
        if visited[node]:
            continue
        order_list.append(node)
        visited[node] = True
        for next in graph[node]:
            if not visited[next]:
                stack.append(next)
    return order_list
order_list = dfs()
results = [0]*(n+1)
for i in range(len(order_list)):
    results[order_list[i]] = i+1

for result in results[1:]:
    print(result)