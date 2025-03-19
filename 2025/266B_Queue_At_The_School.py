n, t = map(int, input().split()) 
s = str(input())

new_s_list = list(s) 
i = 0
j = 0
while j < t:
    while i < n - 1:
        if new_s_list[i] == 'B' and new_s_list[i + 1] == 'G':
            new_s_list[i] = 'G'
            new_s_list[i + 1] = 'B'
            i = i + 2
        else: 
            i = i + 1
    i = 0
    j = j + 1 
new_s = ''.join(new_s_list) 
print(new_s)