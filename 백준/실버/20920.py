"""
작성일: 2025-07-13
메모리: 165216 KB
시간: 404 ms
"""
from collections import Counter
n, m = map(int, input().split())
word_list = []
for _ in range(n):
    word_list.append(input())
c = Counter(word_list)
c = c.most_common()
new_dict = {}
for word in c:
    if len(word[0]) >= m:
        new_dict[word[0]] = word[1]

count = 0
sort_dict = {} 
for word in new_dict:
    value = new_dict[word]
    if count != value:  
        sort_dict[value]= [word]
        count = value
    else: 
        sort_dict[value].append(word)

for word in sort_dict:
    if len(sort_dict[word]) == 1:
        print(sort_dict[word][0])
    else: 
        sort_dict[word].sort()
        sort_dict[word].sort(key=len, reverse = True)
        for new_word in sort_dict[word]:
            print(new_word)