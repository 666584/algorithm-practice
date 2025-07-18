"""
bfs: 이미 익은 토마토는 goal로 setting한다.
각 노드의 깊이를 저장하고 max 깊이를 시간으로 한다.
아직 안익은 토마토가 있으면 -1을 출력한다.
작성일: 2025-07-18
메모리: 254172 KB
시간: 	1064 ms
"""
import sys
from collections import deque
m, n = map(int, sys.stdin.readline().strip().split())
box = []
for _ in range(n):
    box.append(sys.stdin.readline().strip().split())
directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
def find_goal(box):
    goal = []
    for i in range(n):
        for j in range(m):
            if box[i][j] == "1":
                goal.append((i,j))
    return goal
def bfs(goal_list): 
    queue = deque(goal_list)
    visited = set()
    depth = [[-1]*m for _ in range(n)]
    max_depth = 0
    for gx, gy in goal_list:
        depth[gx][gy] = 0
        visited.add((gx,gy))
    while queue:
        node = queue.popleft()
        x, y = node
        for dx, dy in directions:
            nx = x + dx
            ny = y + dy
            if (nx,ny) not in visited:
                if nx >= 0 and ny >= 0:
                    if nx < n and ny < m:
                        if box[nx][ny] == "0" and box[x][y] == "1":
                            box[nx][ny] = "1" 
                            depth[nx][ny] = depth[x][y] + 1                   
                            max_depth = max(max_depth, depth[nx][ny])
                            queue.append((nx,ny))             
    return max_depth

goal_list = find_goal(box)
if len(goal_list) == m*n:
    print(0)
else: 
    depth_list = bfs(goal_list)
    fail = 0
    for tomatos in box:
        if "0" in tomatos:
            fail = 1
            break
    if not fail:
        print(depth_list)
    else:
        print(-1)