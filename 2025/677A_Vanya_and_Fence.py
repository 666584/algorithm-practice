# Codeforces Round 355
# Vanya and Fence
n, h = map(int, input().split()) 
a = list(map(int, input().split()))
i = 0
w = 0
6
while i < n:
    if a[i] <= h:
        w += 1
    else: 
        w += 2
    i = i + 1

print(w)