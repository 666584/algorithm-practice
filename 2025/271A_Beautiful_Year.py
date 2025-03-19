# 271A
# Beautiful Year
y = int(input())
next_y = y + 1
output = 0
while output == 0:
    for n in str(next_y):
        if str(next_y).count(n) != 1:
            next_y = next_y + 1
            continue
        else:
            if str(next_y).index(n) == 3:
                output = next_y
                print(output)