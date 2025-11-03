import sys
n = int(sys.stdin.readline())
bridges = []
for i in range(n):
    w, e = map(int, sys.stdin.readline().strip().split())
    bridges.append([w, e])
dp = []

for w, e in bridges:
    if w == e:
        print(1)
    if w == 1:
        print(e)
    



