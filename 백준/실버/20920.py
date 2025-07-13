"""
작성일: 2025-07-13
메모리: 165216 KB
시간: 404 ms
"""
from collections import Counter
n, m = map(int, input().split())
word_list = []

words = [input() for _ in range(n)]

filtered_words = [word for word in words if len(word) >= m]

word_counts = Counter(filtered_words)

# 중요 부분
sorted_words = sorted(
    word_counts.items(),
    key=lambda x: (-x[1], -len(x[0]), x[0])
)

for word, _ in sorted_words:
    print(word)