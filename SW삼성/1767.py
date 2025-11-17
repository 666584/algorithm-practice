# DFS 구현
"""
길이 없으면 연결하지 않아도 됨.
가장 많은 코어를 연결시켜야 함.
여러 방법이 있을 경우 가장 최단 길이를 찾음.

하나의 길마다 다 검색을 해보고 길이가 가장 짧은 것을 결과로 낸다. 

core 1 의 길 1-> core 2의 길 1 -> core 3의 길 1 -> core 4의 길 1 -> 12
core 1 의 길 1 -> core 2의 길 1 -> core 3의 길 1 -> core 4의 길 2 -> 11

"""
n = int(input())
matrix = []
from collections import defaultdict

def find_all_cores(matrix, n):
    cores = []
    for i in range(n):
        for j in range(n):
            if matrix[i][j] == 1:
                cores.append((i,j))
    return cores

def dfs(matrix, cores):
    return 1

def get_path_with_distance(direction, i, j, matrix, n):
    distance = 0
    is_path = True
    path = []
    distances = []

    # top
    if direction == 1:
        for a in range(0, i):
            if matrix[a][j] == 1:                    
                is_path = False
                break
            else:
                path.append((a,j))
                distance += 1
        if is_path:
            distances.append(distance)
    
    # bottom
    if direction == 2:
        for a in range(i + 1, n):
            if matrix[a][j] == 1:                    
                is_path = False
                break
            else:
                path.append((a,j))
                distance += 1
        if is_path:
            distances.append(distance)
    
    # left
    if direction == 3:
        for a in range(0, j):
            if matrix[i][a] == 1:                    
                is_path = False
                break
            else:
                path.append((i,a))
                distance += 1
        if is_path:
            distances.append(distance)
    
    # right
    if direction == 4:
        for a in range(j + 1, n):
            if matrix[i][a] == 1:                    
                is_path = False
                break
            else:
                path.append((i,a))
                distance += 1
        if is_path:
            distances.append(distance)
    return path, distances

def get_all_path_with_distance(cores, matrix, n):
    path_dis = defaultdict(list)
    for core in cores:
        i, j = core
        if i != 0 and j != 0:
            for z in range(1, 5):
                path = []
                distances = []
                path, distances = get_path_with_distance(z, i, j, matrix, n)
                if path and distances:
                    path_dis[core].append([path, distances])
    return path_dis
     
def find_least_distance_path(path_dis, n):
    for value in path_dis:
        print(value)             

for i in range(n):
    row = list(map(int, input().strip().split()))
    matrix.append(row)

cores = find_all_cores(matrix, n)
print(cores)
path_dis = get_all_path_with_distance(cores, matrix, n)
print(path_dis)
find_least_distance_path(path_dis, n)

