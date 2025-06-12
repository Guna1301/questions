'''

Write a program that finds the longest substring that reads the same forwards 
and backwards.

Input: 
------
babad

Output: 
-------
bab or aba

'''
def sol():
    s = input()
    for i in range(len(s)):
        