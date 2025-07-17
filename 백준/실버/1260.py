"""
dfs, bfs 만드는 방법
작성일: 2025-07-17
메모리:  KB
시간:  ms
"""
import sys
from collections import defaultdict, deque
n, m, v = map(int, sys.stdin.readline().strip().split())
graph = defaultdict(list)
for _ in range(m):
    x, y = map(int, sys.stdin.readline().strip().split())
    graph[x].append(y)
    graph[y].append(x)

def dfs():
    for key in graph:
        graph[key].sort(reverse=True)
    visited = set()
    stack = [v]
    while stack:
        node = stack.pop()
        if node not in visited:
            print(node, end=" ")
        visited.add(node)
        for closed_node in graph[node]:
            if closed_node not in visited:
                stack.append(closed_node)

dfs()
print("")
def bfs():
    for key in graph:
        graph[key].sort(reverse=False)
    visited = set()
    queue = deque([v])
    visited.add(v)
    while queue:
        node = queue.popleft()
        print(node, end=" ")
        for closed_node in graph[node]:
            if closed_node not in visited:
                visited.add(closed_node)
                queue.append(closed_node)
bfs()
