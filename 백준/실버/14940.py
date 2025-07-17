"""
bfs 각 포인트마다 최단거리 찾기
작성일: 2025-07-17
메모리: 129248 KB
시간: 248 ms
"""
import sys
from collections import deque
x_list = [0, -1, 0, 1]
y_list = [1, 0, -1, 0]

def bfs(graph, n, m, goal): 
    queue = deque([goal])
    gx, gy = goal
    distances = [[-1]*m for _ in range(n)]
    distances[gx][gy] = 0

    while queue:
        dx, dy = queue.popleft()
        for i in range(4):
            x = dx + x_list[i]
            y = dy + y_list[i]        
            if (x >= 0 and y >= 0 and x < n 
                and y < m and distances[x][y] == -1):
                if graph[x][y] == "1":
                    distances[x][y] = distances[dx][dy] + 1
                    queue.append((x, y))
    return distances


n, m = map(int, sys.stdin.readline().strip().split())
graph = []
goal = set()
for i in range(n):
    row = sys.stdin.readline().split()
    graph.append(row)
    if "2" in row:
        goal = (i, row.index("2"))

distances = bfs(graph, n, m, goal)
for i in range(n):
    for j in range(m):
        if graph[i][j] == '0':
            print("0", end=" ")
        else:
            print(distances[i][j], end=" ")
    print()