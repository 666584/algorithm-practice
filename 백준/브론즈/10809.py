"""
작성일: 2025-07-14
메모리: 108384 KB
시간: 116ms
"""
import sys
word = sys.stdin.readline().strip()
alphabet = 'abcdefghijklmnopqrstuvwxyz'
for char in alphabet:
    print(word.find(char), end=" ")
