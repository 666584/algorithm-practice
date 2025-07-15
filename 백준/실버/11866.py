"""
작성일: 2025-07-15
메모리: 110624 KB
시간: 	124 ms
"""
import sys
n, k = map(int, sys.stdin.readline().split())
queue = list(range(1,n+1))
deleted_list = []
for _ in range(n-1):
    if len(queue) >= k:
        deleted_list.append(queue[k-1])
        queue = queue[k:] + queue[:k-1]
    else:
        i_delete = k - 1
        while i_delete >= len(queue):
            i_delete -= len(queue)
        deleted_list.append(queue[i_delete])
        queue = queue[i_delete+1:] + queue[:i_delete]
result = "<" 
for num in deleted_list:
    result = result + str(num) + ", "
result = result + str(queue[0]) + ">"
print(result)