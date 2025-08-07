"""
작성일: 2025-08-07
메모리: 108384 KB
시간: 	96 ms
"""
import sys
n, r, c = map(int, sys.stdin.readline().strip().split())
sizes = 1
for _ in range(n):
    sizes *= 2

def count(start_r, start_c, size, start_point):
    if size == 1:
        return start_point
    section = []
    total_number = size * size
    size = int(size/2)
    if r < size + start_r:
        section.append(0)
        section.append(1)
    else:
        section.append(2)
        section.append(3)
    n_section = 0
    if c < size + start_c:
        if 0 in section:
            n_section = 0
        if 2 in section:
            n_section = 2
    else:
        if 1 in section:
            n_section = 1
        if 3 in section:
            n_section = 3
    start_point += int(total_number/4)*n_section
    if n_section == 1:
        start_c += size
    if n_section == 2:
        start_r += size
    if n_section == 3:
        start_r += size
        start_c += size
    return count(start_r, start_c, size, start_point)

print(count(0,0, sizes, 0))
