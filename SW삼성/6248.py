T = int(input())
for i in range(T):
    tree = []
    N, M, L = map(int, input().split())
    for j in range(M):
        node_num, node_value = map(int, input().split())
        tree.append((node_num, node_value))
    tree = sorted(tree, reverse=True)
    start_node = tree[-1][0] - 1
    if N % 2 == 0:
        tree.append((start_node, tree[0][1]))
        x = 1
        y = 2
        start_node -= 1
    else: 
        x = 0
        y = 1
    while start_node > 0:
        new_node_value = tree[x][1] + tree[y][1]
        tree.append((start_node, new_node_value))
        start_node -= 1
        x += 2
        y += 2
    for node in tree:
        if node[0] == L:
            print(f"#{i+1} {node[1]}")
         