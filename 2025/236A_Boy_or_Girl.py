# 236A
# Boy or Girl
a = list(input())
i = 0
n = 0
a.sort()

while i < len(a):
    if(a.count(a[i]) == 1):
        i = i + 1
    else :
        i = i + a.count(a[i])
    n = n + 1

if(n % 2 == 0):
    print("CHAT WITH HER!")
else: 
    print("IGNORE HIM!")