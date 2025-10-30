def solution(s):
    f_char = s[0]
    if f_char != "(":
        return False
    opened = 1
    for c in s[1:]:
        if opened < 0:
            return False
        if c == ")": 
            opened -= 1
        elif c == "(":
            opened += 1
        else:
            return False
    if opened != 0:
        return False           
    return True