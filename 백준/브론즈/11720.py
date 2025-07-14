"""
작성일: 2025-07-14
메모리: 108384 KB
시간: 	92 ms
"""
import sys
_ = int(sys.stdin.readline())
numbers = sys.stdin.readline().strip()
result = 0
for number in numbers:
    result += int(number)
print(result)
