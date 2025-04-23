def main():
    num = int(input())
    responses = list(map(int, input().split()))
    for response in responses:
        if(response == 1):
            print("HARD")
            return None
    print("EASY")
main()