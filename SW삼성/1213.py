for _ in range(10):
    tc = int(input())
    goal = ""
    word = ""
    goal = str(input())
    word = str(input())
    count = 0
    for i in range(len(word)):
        search = word[i:i+len(goal)]
        if search == goal:
            count += 1
    print(f"#{tc} {count}")