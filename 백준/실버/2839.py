"""
작성일: 2025-10-23
메모리: 108384 KB
시간: 96 ms
"""
import sys
n = int(sys.stdin.readline())

def get_min_num(n):
    a = n // 5 

    while a > -1:
        r = n - a * 5
        if r % 3 == 0:
            return int(a + (r/3))
        else:
            a -= 1
    return -1
    
print(get_min_num(n))        
