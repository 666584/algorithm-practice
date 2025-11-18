for i in range(10):
    dump = int(input())
    boxes = list(map(int, input().split()))
    result = 0
    for j in range(dump):
        max_box = max(boxes)
        max_i = boxes.index(max_box)
        min_box = min(boxes)
        min_i = boxes.index(min_box)
        if max_box - min_box == 1 or max_box == min_box:
            result = 1
            break
        else:
            boxes[min_i] = min_box + 1
            boxes[max_i] = max_box - 1
    result = max(boxes) - min(boxes)
    print(f"#{i+1} {result}")