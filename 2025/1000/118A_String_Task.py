word = input(str())
vowel_list = ['a','e','i','o','u', 'y']
word = word.lower()
new_word = ''
for char in word:
    if char not in vowel_list:
        new_word = new_word + '.' + char
print(new_word)