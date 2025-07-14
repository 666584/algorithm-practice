"""
작성일: 2025-07-14
메모리: 108384 KB
시간: 	120 ms
"""
import sys
c = sys.stdin.readline().strip()
big_char_list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
small_char_list = "abcdefghijklmnopqrstuvwxyz"
if c.isdigit():
    print(int(c) + 48)
elif c in big_char_list:
    print(big_char_list.find(c) + 65)
elif c in small_char_list:
    print(small_char_list.find(c) + 97)