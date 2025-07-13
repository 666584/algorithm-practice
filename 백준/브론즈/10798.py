"""
가로 세로 15(최소 1, 최대 15)*5의 문자, 숫자열을 세로로 읽는다. 

작성일: 2025-07-13
메모리: 32544 KB
시간: 48ms
"""
def read_vertically():
    sentence_list = []
    count_list = []
    for _ in range(0,5):
        word = input()
        sentence_list.append(word)
        count_word = len(word)
        count_list.append(count_word)
    sentence = ''
    for i in range(0,15):
        for j in range(0,5): 
            if i < count_list[j]:   
                sentence = sentence + sentence_list[j][i]
    print(sentence)
"""
def read_vertically():
    rows = [input() for _ in range(5)]
    result = ""

    max_length = max(len(row) for row in rows)

    for i in range(max_length):
        for row in rows:
            if i < len(row):
                result += row[i]

    print(result)
"""
read_vertically()