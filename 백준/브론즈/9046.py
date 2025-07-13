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
    
    for _ in range(0, num_message):
        message = input()
        # 공백 제거
        counter = Counter(message.replace(" ", ""))
        fre_char = counter.most_common()

        if len(fre_char) > 1 and fre_char[0][1] == fre_char[1][1]:
            print("?")
        else:
            print(fre_char[0][0])
most_fre_char()