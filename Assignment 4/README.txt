UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 4]

[Description]
There are 3 java files: Encoder.java, Huffman.java, and Heap.java.

Encoder.java is the main file. It has 11 methods.
entropy() returns the entropy of the frequencies.
logBase2() returns the base 2 logarithm of a number.
text() writes to "textTest" a k-character text based on probabilities of the characters.
getProb() computes the probabilities and returns it in an array.
encode() encodes the "testText" using the codings provided by the Huffman code and writes it to "testText.enc1". Returns the average bits per symbol.
decode() decodes the encoded "testText" and writes it to "testText.dec1".
twoSymbol() computes a two-symbol alphabet and returns a String[].
twoFrequencies() calculates the new frequencies for the two-symbol alphabet.
twoEncode() encodes using the 2-symbol alphabet the "testText" using the codings provided by the Huffman code and writes it to "testText.enc2". Returns the average bits per symbol.
twoDecode() decodes the encoded "testText" and writes it to "testText.dec2".
main() calls all of the previous methods.

Huffman.java is the Huffman algorithm. I got the code from http://www.cs.armstrong.edu/liang/intro9e/html/HuffmanCode.html
In the code I, main(): removed, getCode(): changed to create an array of size 26, assignCode(): changed root.element from the value of the character to value of character - 97, getHuffmanTree(): changed to account for not using the characters value in the counting, getCharacterFrequency(): removed, printCode(): added, it prints a table with the character, its frequency, and its encoding. Modified code to take in a String as the stored element instead of a character.

Heap.java is a heap also defined by the same person. It was used in the Huffman algorithm I got it here: http://www.cs.armstrong.edu/liang/intro9e/html/Heap.html
I did not change this file.

[Finish]
I finished most of it. There are some bugs in both decoding methods where it will go out of bounds in the array. Also, there are bugs in the Huffman algorithm where it will not assign an encoding for some of the characters in the 2-symbol alphabet. It only happens when there are more than 4 characters. The single symbol alphabet is fine, even up to 26 characters. I have included outputs from just the single symbol alphabet.

[Test Case 1]

[Command line]
java Encoder frequencies 4

[Input]
10
3
5
9


[Output]
Character      Weight         Frequency      Code
a              0.370          10             0
b              0.111          3              100
c              0.185          5              101
d              0.333          9              11
Average bits: 2.25
Entropy: 1.8618084674058868
Percent difference: 9.440895%
Character      Weight         Frequency      Code
aa             0.137          100            101
ab             0.041          30             0000
ac             0.069          50             1101
ad             0.123          90             011
ba             0.041          30             11111
bb             0.012          9              000110
bc             0.021          15             00010
bd             0.037          27             11101
ca             0.069          50             1100
cb             0.021          15             000111
cc             0.034          25             11100
cd             0.062          45             1001
da             0.123          90             010
db             0.037          27             11110
dc             0.062          45             1000
dd             0.111          81             001
Average bits: 2.25
Entropy: 1.8618084674058868
Percent difference: 9.440895%

[Test Case 2]

[Command line]
java Encoder frequencies 7

[Input]
7
6
5
4
3
2
1


[Output]
Character      Weight         Frequency      Code
a              0.250          7              10
b              0.214          6              01
c              0.179          5              111
d              0.143          4              110
e              0.107          3              000
f              0.071          2              0011
g              0.036          1              0010
Average bits: 2.2857142857142856
Entropy: 2.6100053707958026
Percent difference: 6.623972%

[Test Case 3]

[Command line]
java Encoder frequencies 26

[Input]
26
25
24
23
22
21
20
19
18
17
16
15
14
13
12
11
10
9
8
7
6
5
4
3
2
1

[Output]
Character      Weight         Frequency      Code
a              0.074          26             1101
b              0.071          25             1100
c              0.068          24             1010
d              0.066          23             1001
e              0.063          22             1000
f              0.060          21             0111
g              0.057          20             0101
h              0.054          19             0100
i              0.051          18             0011
j              0.048          17             0001
k              0.046          16             0000
l              0.043          15             11111
m              0.040          14             11101
n              0.037          13             11100
o              0.034          12             10110
p              0.031          11             01101
q              0.028          10             01100
r              0.026          9              00100
s              0.023          8              111101
t              0.020          7              111100
u              0.017          6              101110
v              0.014          5              001011
w              0.011          4              001010
x              0.009          3              1011110
y              0.006          2              10111111
z              0.003          1              10111110
Average bits: 4.384615384615385
Entropy: 4.447380034318692
Percent difference: 0.710651%

[Test Case 4]

[Command line]
java Encoder frequencies 26

[Input]
100
25
20
23
22
6
20
19
18
17
28
40
14
13
12
11
10
9
8
7
6
5
4
3
2
1


[Output]
Character      Weight         Frequency      Code
a              0.226          100            01
b              0.056          25             1000
c              0.045          20             11101
d              0.052          23             0010
e              0.050          22             0001
f              0.014          6              001110
g              0.045          20             0000
h              0.043          19             11011
i              0.041          18             11010
j              0.038          17             11001
k              0.063          28             1011
l              0.090          40             1111
m              0.032          14             10101
n              0.029          13             10100
o              0.027          12             10010
p              0.025          11             00110
q              0.023          10             111001
r              0.020          9              110001
s              0.018          8              110000
t              0.016          7              100111
u              0.014          6              001111
v              0.011          5              1110001
w              0.009          4              1110000
x              0.007          3              1001100
y              0.005          2              10011011
z              0.002          1              10011010
Average bits: 4.423076923076923
Entropy: 4.1105445538547105
Percent difference: 3.662365%
