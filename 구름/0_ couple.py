import sys
n = int(sys.stdin.readline())
people = set(map(int, sys.stdin.readline().strip().split()))
sum = 0

for person in people:
	sum += person

print(sum)