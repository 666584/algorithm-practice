def is_p(word):
    length = len(word)
    repeat = length // 2
    for a in range(repeat):
        if word[a] != word[-a-1]:
            return False
    return True
for _ in range(10):
    tc  = int(input())
    matrix = []
    for j in range(100):
        matrix.append(list(s for s in input()))
    max_score = 0
    # 가로
    for i in range(100):
        for m in range(100):
            for j in range(2, 101):
                word = []
                word = matrix[i][m:j]
                if is_p(word):
                    length = len(word)
                    if length >= max_score:
                        max_score = length
    # 세로
    for x in range(100):
        for v in range(100):
            for y in range(2, 101):
                word = []
                word = [matrix[c][x] for c in range(v,y)]
                if is_p(word):
                    length = len(word)
                    if length >= max_score:
                        max_score = length
    print(f"#{tc} {max_score}")
    #print(is_p("CCCCABAACCAABACCCC"))
    #print(is_p('CC'))