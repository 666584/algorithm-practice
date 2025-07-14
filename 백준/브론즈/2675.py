"""
작성일: 2025-07-14
메모리: 108384 KB
시간: 	112 ms
"""
import sys
n_line = int(sys.stdin.readline())
for _ in range(n_line):
    n, word = sys.stdin.readline().strip().split()
    result = ''
    for char in word:
        for _ in range(int(n)):
            result += char
    print(result)