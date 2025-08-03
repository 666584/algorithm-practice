"""
색종이/ 분할 정복, 재귀
작성일: 2025-08-03
메모리: 111648 KB
시간: 116 ms
"""
import sys
n = int(sys.stdin.readline())
table = []
for i in range(n):
    table.append(list(map(int, sys.stdin.readline().strip().split())))
result = []
def get_count(n, start_points):   
    if not start_points:
        return 1
    if n < 1:
        return 1 
    for x, y in start_points:
        valid = 1
        num = table[x][y]
        for i in range(n):
            for j in range(n):
                if num != table[x + i][y + j]:
                    valid = 0
                    break
        if valid == 1:
            result.append(table[x + i][y + j])
        else: 
            new_n = int(n/2)
            new_start_points = [(x, y), (x, y+new_n), (x+new_n, y), (x+new_n, y+new_n)]
            get_count(int(n/2), new_start_points)

num = table[0][0]
valid = 1

for i in range(n):
    for j in range(n):
        if num != table[i][j]:
            valid = 0
            break

if valid == 1:
    if num == 0:
        print(1)
        print(0)
    else:
        print(0)
        print(1)
else:
    new_n = int(n/2)
    start_points = [(0,0),(0,new_n),(new_n,0),(new_n,new_n)]
    get_count(new_n, start_points)
    print(result.count(0))
    print(result.count(1))