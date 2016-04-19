UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 5]
[Description]
There are 6 java files: AES, keyExpansion, roundKey, mixColumn, shiftRows, subBytes.
AES.java: has 7 methods -
    encrypt: calls the subBytes, shiftRows, mixColumns, and addRoundKey methods to encrypt. One of two important methods.
    decrypt: calls the methods in encrypt in inverse order to decrypt the message. Second of important methods.
    to2D: Takes a 1D array and returns a 4x4 2D array. (Column-Major order)
    to1D: Takes a 2D array and returns a 1D array
    hexToByte: (128 bits) Takes a hex string and returns a byte array. Pads with zeroes if less than 32 Hex characters, truncates if more than.
    hexToByte2: (256 bits) Takes a hex string and returns a byte array.
    byteToHex: Takes a byte array and returns a hex string.
keyExpansion.java: Has 3 methods -
    rotate: Takes a byte[] and rotates it up one spot.
    getWord: Returns the word, in the column specified, as a byte[].
    expand: Creates the roundKeys needed.
roundKey.java: has 2 methods -
    add: XOR the state with the roundKey.
    get: Uses the keySchedule and round number to determine which roundKey is being used. Returns that roundKey.
mixColumn.java: I used Professor Young's implementation of mixColumn. I edited the methods to take in the state matrix.
shiftRows.java: Has 2 methods -
    shift: Shift row left by i bytes. i denotes the row in state.
    invShift: Inverse of shiftRows(), returns the matrix to original matrix. Shift row right by i bytes. i denotes the row in state.
subBytes.java: Has 3 methods -
    sub: Uses the state array values as an index into a lookup table, and replaces its value in state with the value stored in the table.
    invSub: Takes in the state array and uses its value as an index into the inverse lookup table, and replaces its value in state with the value stored in the table.
    subWord: Takes a byte[] and uses the substitution transformation on it.

[Finish]
I finished the entire assignment.

[Test Case 1]

[Command line]
java AES e keyFile test1
java AES d keyFile test1.enc

[Timing Information]
encrypt: 8 bytes/msec
decrypt: 16 bytes/msec

[Input Filenames]
keyFile test1

[Output Filenames]
test1.enc test1.enc.dec

[Test Case 2]

[Command line]
java AES e keyFile test2
java AES d keyFile test2.enc

[Timing Information]
encrypt: 24 bytes/msec
decrypt: 16 bytes/msec

[Input Filenames]
keyFile test2

[Output Filenames]
test2.enc test2.enc.dec

[Test Case 3]

[Command line]
java AES e keyFile test3
java AES d keyFile test3.enc

[Timing Information]
encrypt: 53 bytes/msec
decrypt: 80 bytes/msec

[Input Filenames]
keyFile test3

[Output Filenames]
test3.enc test3.enc.dec

[Test Case 4]

[Command line]
java AES e keyFile test4
java AES d keyFile test4.enc

[Timing Information]
encrypt: 21 bytes/msec
decrypt: 48 bytes/msec

[Input Filenames]
keyFile test4

[Output Filenames]
test4.enc test4.enc.dec
