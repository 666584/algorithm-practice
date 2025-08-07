"""
작성일: 2025-07-18
메모리: 254172 KB
시간: 	1064 ms
"""
import sys
n, r, c = map(int, sys.stdin.readline().strip().split())
sizes = 1
for _ in range(n):
    sizes *= 2
print(sizes)

def count(start_c, start_r, size, start_point):
    if size == 1:
        print("end")
        print(start_point)
        return 0
    section = []
    total_number = size * size
    print("total_number")
    print(total_number)
    size = int(size/2)
    print(size)
    if r < size - start_r:
        section.append(0)
        section.append(1)
    else:
        section.append(2)
        section.append(3)
    n_section = 0
    if c < size - start_c:
        if 0 in section:
            n_section = 0
        if 2 in section:
            n_section = 2
    else:
        if 1 in section:
            n_section = 1
        if 3 in section:
            n_section = 3
    print(n_section)
    start_point += int(total_number/4)*n_section
    print(start_point)
    if n_section == 1:
        start_c += size
    if n_section == 2:
        start_r += size
    if n_section == 3:
        start_r += size
        start_c += size
    print(start_c, start_r)
    return count(start_r, start_c, size, start_point)

print(count(0,0, sizes, 0))
