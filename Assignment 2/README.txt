UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 2]
[Description]
There are 7 java files:
SecureSystem.java did not change. I only commented out the main function.
In Subject.java, I added a StringBuilder to keep track of bits being sent through the covert channel. A run() method was also added. It adds the bits sent through the covert channel to the string, then checks if all 8-bits are there. If there are 8-bits, the byte is returned and the string is cleared. This is the only place where the bits are stored. It is only accessible to that particular subject.
Object.java did not change.
In InstructionObject.java, I added another constructor, and toString() method to print out the instruction.
SecurityLevel.java did not change.
In ReferenceMonitor.java, I updated checkInstruction() to take in create, destroy, and run instructions. I added 7 methods: executeCreate, executeDestroy, executeRun, create, destroy, canRead, and canWrite. The checkInstruction method is still the most important method, however, it has been updated to also check for create, destroy, and run instructions.
CovertChannel.java only contains the main method. In the main method, the subjects are created. The file is then read in line by line. Depending on the bit that is read, the InstructionObjects are created, which is then sent to the ReferenceMonitor.
To compile my program, you need to use "javac *.java". To run my program, you need to use "java CovertChannel filename" or "java CovertChannel v filename".

[Machine Information]
I am using a MacBook Pro with a 2.4 GHz processor.

[Source Description]
I got the first two test files from the Project Gutenburg website.
Pride and Prejudice: http://www.gutenberg.org/1/3/4/1342/
Metamorphosis: http://www.gutenberg.net/5/2/0/5200/

The last two test files I created myself. testfile2 is just the first five lines from the README.

[Finish]
I finished the entire assignment.

[Results Summary]
[No.]	[DocumentName]			[Size]			[Bandwidth]
1 		Pride and Prejudice		684,765 bytes	3051 bits/ms
2 		Metamorphosis			119,227 bytes	1811 bits/ms
3 		testfile1.txt			82 bytes		48 bits/ms
4 		testfile2				145 bytes		63 bits/ms


