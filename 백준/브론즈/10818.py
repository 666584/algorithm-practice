"""
작성일: 2025-07-14
메모리: 218540 KB
시간: 	404 ms
"""
import sys
n = int(sys.stdin.readline())
num_list = list(map(int, sys.stdin.readline().split()))
print(min(num_list), max(num_list))