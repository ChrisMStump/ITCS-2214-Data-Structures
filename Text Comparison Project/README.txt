PROJECT TITLE: Text Comparison Project
PURPOSE OF PROJECT: To test the efficiency of different data structures.

There are four versions for this assignment. They are sorted arrays, unsorted arrays,
hash tables and binary trees. Each of these data structures where coded by hand
without the use of libraries.

In each of the versions, the program takes in two text files as input.
In this project we used long texts from books, so it would take some time
to complete.

There are four versions of this project: Binary Tree, Hash Tables, Sorted Array and
Unsorted Array.

The programs keep a count of each word that is in the text. Depending on the amount of
words that are the same the program reports a number between 0 and 1. If the program
returns 0, then the texts are completely different and if 1, then they are the same.

The program returns the time that it took to calculate whether or not they are
similar. It also returns the average time to compute the value as well as the upper
and lower confidence based on the result.

In this project it was found that Hash Tables were the top data structure to implement
for this problem. This is because the program does not need to iterate through many
values before it gets to the result. It can basicallty just use a locker number to find
the values.
