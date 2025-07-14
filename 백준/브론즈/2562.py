"""
작성일: 2025-07-14
메모리: 108384 KB
시간: 	116 ms
"""
import sys
num_list = []
for _ in range(9):
    num_list.append(int(sys.stdin.readline()))
max_num = max(num_list)
print(int(max_num))
print(num_list.index(max_num)+1)