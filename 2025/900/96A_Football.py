def main():
    a = input(str())
    i = 1
    curr_stat =  a[0]
    curr_num = 1
    while i < len(a):
        if (curr_num == 7):
            print("YES")
            return None
        if (a[i] == curr_stat):
            curr_num = curr_num + 1
        else:
            curr_stat = a[i]
            curr_num = 1
        i = i + 1
    if curr_num == 7:
        print("YES")
    else:
        print("NO")
    return None

main()