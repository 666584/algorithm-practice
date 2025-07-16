"""
작성일: 2025-07-16
메모리: 108384 KB
시간: 	112 ms
"""
import sys

while True:
    numbers = sys.stdin.readline().strip()
    valid = 1
    if int(numbers) == 0:
        break
    for i in range(int(len(numbers)/2 + 1)):
        if numbers[i] != numbers[-1-i]:
            valid = 0
            break
    print("yes" if valid == 1 else "no")