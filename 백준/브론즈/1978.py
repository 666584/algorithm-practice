"""
작성일: 2025-07-16
메모리: 109544 KB
시간: 	124 ms
"""
import sys
n = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().strip().split()))
count = 0

for number in numbers:
    valid = 1
    if number == 1:
        valid = 0

    else:
        for i in range(2, number):
            if number % i == 0:
                valid = 0
                break
    if valid == 1:
        count += 1

print(count)