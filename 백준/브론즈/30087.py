"""
작성일: 2025-07-14
메모리: 108384 KB
시간: 	92 ms
"""
seminar_dict = {"Algorithm": "204", "DataAnalysis": "207", 
                "ArtificialIntelligence": "302", "CyberSecurity": "B101",
                "Network": "303", "Startup": "501", "TestStrategy": "105"}
num = int(input())
for _ in range(num):
    seminar = input()
    room = seminar_dict.get(seminar)
    print(room)