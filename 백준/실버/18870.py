"""
작성일: 2025-08-06
메모리: 287408 KB
시간: 	1000 ms
"""
import sys
input = sys.stdin.readline
n = int(input())
numbers = list(map(int, input().strip().split()))
numbers_set = set(numbers)
numbers_set = sorted(numbers_set)

index_dict = {num: i for i, num in enumerate(numbers_set)}
"""
index_dict = {}
for i in range(len(numbers_set)):
    index_dict[numbers_set[i]] = i
"""
for number in numbers:
    print(index_dict[number], end= " ")