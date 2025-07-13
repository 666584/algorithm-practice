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

def vaildate_password(password):
    vaild = 0      
    for vowel in vowel_list:
        if vowel in password:                
            vaild = 1
            break
    if vaild == 0:
        return 0
    else:
        i = 1
        cur_char = password[0]
        count = 1
        
        while i < len(password):  
            if (cur_char == password[i] and
                cur_char != 'o' and cur_char != 'e'):
                return 0
            elif count == 3:
                return 0
            elif is_vowel(cur_char) == is_vowel(password[i]):
                count += 1
                cur_char = password[i]
            else:
                cur_char = password[i] 
                count = 1
            i += 1   
    if count == 3:
        return 0
    else:
        return vaild

def result():
    password = ""
    while password != "end":
        password = input()
        if password == "end":
            return None 
        result = vaildate_password(password)
        if result == 1:
            print("<"+password+">"+" is acceptable.")
        elif result == 0:
            print("<"+password+">"+" is not acceptable.")
result()