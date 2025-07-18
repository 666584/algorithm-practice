import sys
n, d = map(int, sys.stdin.readline().strip().split())
numbers = list(map(int, sys.stdin.readline().strip().split()))
numbers.sort()

def min_count():
	count = 0
	min_count = float("inf")
	for i in range(n-1):
			j = n - 1
			while j > i:
				if numbers[j] - numbers[i] <= d:
					count = n - (j - i) - 1
					min_count = min(count, min_count)
					if min_count == 0:
						return min_count
					break
				j -= 1
	return min_count
print(min_count()) 