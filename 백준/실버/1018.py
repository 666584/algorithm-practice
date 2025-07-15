"""
작성일: 2025-07-15
메모리: 110960 KB
시간: 120 ms
"""
import sys
n, m = map(int, sys.stdin.readline().split())
color_list = []
for _ in range(n):
    color_list.append(sys.stdin.readline().strip())

def find_min_num_color():
    min_num_color = float('inf') 
    for i in range(n - 7):
        for j in range(m - 7):
            w_num_color = find_num_color(i, j, 'W')
            b_num_color = find_num_color(i, j, 'B')
            new = min(w_num_color, b_num_color)
            if min_num_color > new:
                min_num_color = new
    print(min_num_color)

def find_num_color(r, c, first_color):
    expected = ['W', 'B'] if first_color == 'W' else ['B', 'W']
    count = 0
    for i in range(8):
        for j in range(8):
            if color_list[r+i][c+j] != expected[(i+j)%2]:
                count += 1
    return count

find_min_num_color()