def main():
    num = int(input())
    num_list = list(map(int, input().split()))
    num_dict = {}
    i = num
    while i != 0:
        if (num_list.count(i) != 0):
            num_dict[i] = num_list.count(i)
        i = i - 1
    point = 0
    print(num_dict)
    j = 1
    while j < num + 1:
        new_dict = num_dict.copy()
        print(new_dict)
        print("j:")
        print(j)
        if j in new_dict:
            z = j
            while len(new_dict) != 0:
                if z in new_dict:
                    print(z)
                    new_dict[z] = new_dict[z] - 1
                    if (new_dict[z] == 0):
                        new_dict.pop(z) 
                    point = point + z
                    if (z - 1 in new_dict):
                        new_dict.pop(z - 1)
                    if (z + 1 in new_dict):
                        new_dict.pop(z + 1)
                    print(new_dict)
                z = z + 1
            print("point: ")
            print(point)
            point = 0
        j = j + 1
    return None
main()