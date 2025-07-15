"""
작성일: 2025-07-15
메모리: 108384 KB
시간: 	120 ms
"""
import sys
n1, n2 = map(int, sys.stdin.readline().split())
print(">" if n1 > n2 else "<" if n1 < n2 else "==")