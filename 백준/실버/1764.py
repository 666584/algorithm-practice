"""
작성일: 2025-07-13
메모리: 44628 KB
시간: 2564 ms
"""
num_heard, num_saw = map(int, input().split()) 
list_heard = []
list_saw = []
list_not_famous = []
for _ in range(num_heard):
    list_heard.append(input())
for _ in range(num_saw):
    list_saw.append(input())
list_heard, list_saw = set(list_heard), set(list_saw)
# set 의 &를 사용하면 common element를 얻을 수 있다.
list_not_famous = list_heard & list_saw
sorted_list = sorted(list_not_famous)
print(len(sorted_list))
for name in sorted_list:
    print(name)
