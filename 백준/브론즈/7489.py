"""
작성일: 2025-07-14
메모리: 109540 KB
시간: 	88 ms
"""

num_line = int(input())
for _ in range(num_line):
    num = int(input())
    result = 1
    for i in range(1, num + 1):
        result *= i
    result_str = str(result)
    j = len(result_str) - 1
    while j >= 0:
        if result_str[j] != '0':
            print(result_str[j])
            break
        j -= 1