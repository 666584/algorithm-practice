"""
bfs: 미완성
작성일: 2025-07-17
메모리:  KB
시간: 	 ms
"""
import sys
from collections import deque
m, n = map(int, sys.stdin.readline().strip().split())
box = []
for _ in range(n):
    box.append(sys.stdin.readline().strip().split())
graph = {}
for i in range(n):
    for j in range(m):
        closed = [(i, j-1), (i, j+1), (i-1, j), (i+1, j)]
        new_closed = []
        for x, y in closed:
            print(x, y)
            if not (x < 0 or y < 0 or x >= n or y >= m):
                new_closed.append((x,y))
        graph[(i,j)] = new_closed
print(graph)
print(box)
def bfs(graph):
    visited = set()
    first_node = (0,0)
    queue = deque([first_node])
    completed_list = []
    while queue:
        node = queue.popleft()
        visited.add(node)
        if box[node[0]][node[1]] == "1":
            completed_list.append(node)
        for closed_node in graph[node]:
            if closed_node not in visited:
                queue.append(closed_node)
                visited.add(closed_node)
    return completed_list
completed_list = bfs(graph)
if not completed_list:
    print(-1)
for completed_node in completed_list:
    for closed_node in graph[completed_list]:
        if graph[closed_node] == "0":
            graph[closed_node] = "1"
print(completed_list)