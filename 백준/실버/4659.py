"""
최소 하나의 모음이 포함되어야 함.
3개 연속된 자음 혹은 모음이 있으면 안됨.
"ee", "oo" 외에 2개 연속의 같은 letter가 있으면 안됨.
작성일: 2025-07-13
메모리: 32412 KB
시간: 40ms
"""
vowel_list = ['a', 'e', 'i', 'o', 'u']

def is_vowel(ch):
    return ch in vowel_list

def vaildate_password(password: str) -> bool:
    if not any(vowel in password for vowel in vowel_list):
        return False

    cur_char = password[0]
    count = 1
    
    for i in range(1, len(password)):
        next_char = password[i]

        if cur_char == next_char and cur_char not in ('e', 'o'):
            return False
        
        if is_vowel(cur_char) == is_vowel(next_char):
            count += 1
            if count == 3:
                return False
        else:
            count = 1
        cur_char = next_char
    
    return True

def result():
    while True:
        password = input()
        if password == "end":
            break 
        if vaildate_password(password):
            print(f"<{password}> is acceptable.")
        else:
            print(f"<{password}> is not acceptable.")
result()