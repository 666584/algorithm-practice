"""
' '은 제외한다.
가장 frequent 한 character 가 여러가지면 "?"를 출력한다.

작성일: 2025-07-13
메모리: 34908 KB
시간: 64ms
"""
from collections import Counter

def most_fre_char():
    num_message = int(input())
    
    for x in range(0, num_message):
        message = input()
        c = Counter(message)
        fre_dict = dict(c.most_common())
        if ' ' in fre_dict:
            fre_dict.pop(' ')
        if len(fre_dict) != 1:
            if list(fre_dict.values())[0] == list(fre_dict.values())[1]:
                print("?")
            else:
                print(list(fre_dict.keys())[0])
        else:
            print(list(fre_dict.keys())[0])
most_fre_char()