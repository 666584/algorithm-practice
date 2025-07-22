"""
dp
"""
import sys
num = int(sys.stdin.readline())
houses = []
for _ in range(num):
    houses.append(list(map(int, sys.stdin.readline().strip().split())))
print(houses)
total_cost = 0
rgb_list = [0,1,2]
def find_cost(prev_rgb, curr_house):
    cost = float("inf")
    for rgb in rgb_list:
        if rgb != prev_rgb:
            curr_cost = curr_house[rgb]
            cost = min(cost, curr_cost)
    return cost

print(find_cost())
        