T = int(input())
for i in range(T):
    goal = list(input().strip())
    length = len(goal)
    origin = ['0'] * length
    count = 0
    for j in range(length):
        if goal == origin:
            break
        elif goal[j] != origin[j]:
            for z in range(j, length):
                origin[z] = goal[j]
            count += 1
    print(f"#{i+1} {count}")