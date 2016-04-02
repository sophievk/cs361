UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 4]

[Description]
There are 3 java files: Encoder.java, Huffman.java, and Heap.java.
Encoder.java is the main file. It has X methods.
entropy() returns the entropy of the frequencies.
logBase2() returns the base 2 logarithm of a number.
text() writes to "textTest" a k-character text based on probabilities of the characters.
Huffman.java is the Huffman algorithm. I got the code from http://www.cs.armstrong.edu/liang/intro9e/html/HuffmanCode.html
In the code I, main(): removed, getCode(): changed to create an array of size 26, assignCode(): changed root.element from the value of the character to value of character - 97, getHuffmanTree(): changed to account for not using the characters value in the counting, getCharacterFrequency(): removed, printCode(): added, it prints a table with the character, its frequency, and its encoding.
Heap.java is a heap also defined by the same person. It was used in the Huffman algorithm I got it here: http://www.cs.armstrong.edu/liang/intro9e/html/Heap.html

[Finish]

[Test Case 1]  // k has to be at least 4 and at most 26

[Command line]

[Input]

[Output]

[Test Case 2]

[Command line]

[Input]

[Output]

[Test Case 3]

[Command line]

[Input]

[Output]

[Test Case 4]

[Command line]

[Input]

[Output]
