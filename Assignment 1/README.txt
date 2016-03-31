UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 1]
There are 6 java files: 
In Subject.java, there are 5 helper methods that are used to set variables in the constructor and get variables in outside files. 
In Object.java,there are 6 helper methods that are used they same way as the methods in Subject.java. 
In InstructionObject.java, there are 8 helper methods and 3 different constructors. The methods are also used in the same way as the previous two java files. 
In ReferenceMonitor.java, I implemented 6 methods—isValid, executeWrite, executeRead, and checkInstruction–and the ObjectManager class–which also has 3 methods, createNewObject, validObj, getObj, read, and write. The ObjectManager keeps track of the objects and has a list of all the objects. The most important method is checkInstruction, which has two main steps: 1) it checks if the instruction is a write action then checks to see if the InstructionObject is a valid instruction. If it is valid, it will call executeWrite that checks to see if the subject has permission to write to the object, which will then call write in the ObjectManager to write to the object. 2) it checks if the instruction is a read action then checks to see if the InstructionObject is a valid instruction. If it is valid, it will call executeRead that checks to see if the subject has permission to read the object, which will then call read in the ObjectManager to read the object.
In SecureSystem.java, there are 6 methods, including the main method, and a List of subjects. createSubject creates a new subject and adds it to the list of subjects. printState prints out the state of the objects and subjects. validSub checks to see if the passed in subject is in the subject list. getSub will return the subject from the subject list. isInteger checks if the passed in string is an Integer. printState prints out the state of the system. In the main method, the subjects and objects are created. Then the file is parsed into InstructionObjects which get sent to the ReferenceMonitor.
To compile my program, you need to use "javac *.java". To run my program, you need to use "java SecureSystem instructionList".

[Finish]
I finished the all of the assignment.

[Test Cases]
[Input of test 1]
write Hal HObj 
read Hal 
write Lyle LObj 10
read Hal LObj 
write Lyle HObj 20
write Hal LObj 200
read Hal HObj
read Lyle LObj
read Lyle HObj
foo Lyle LObj
Hi Lyle, This is Hal
The missile launch code is 1234567

[Output of test 1]
Reading from file: instructionList.txt

Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


lyle writes value 10 to lobj
The current state is:
Lobj has value: 10
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


hal reads lobj
The current state is:
Lobj has value: 10
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 10


lyle writes value 20 to hobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 10


hal writes value 200 to lobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 10


hal reads hobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20


lyle reads lobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 10
Hal has recently read: 20


lyle reads hobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20


Bad Instruction
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20


Bad Instruction
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20


Bad Instruction
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20

[Input of test 2]
read Hal LObj 200
write Lyle HObj 20
write Hal HObj
read Hal 
write Lyle LObj 10
write Hal LObj 200
read 	Hal HObj
read Hal	HObj

[Output of test 2]
Reading from file: instructionList.txt

Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


lyle writes value 20 to hobj
The current state is:
Lobj has value: 0
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


lyle writes value 10 to lobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


hal writes value 200 to lobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


hal reads hobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20


hal reads hobj
The current state is:
Lobj has value: 10
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 20

[Input of test 3]
write Kyle LObj 10
read Hal LObj 
write Lyle HObj 20
write Hal LObj 200
read HObj Hal

[Output of test 3]
Reading from file: instructionList.txt

Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


hal reads lobj
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


lyle writes value 20 to hobj
The current state is:
Lobj has value: 0
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


hal writes value 200 to lobj
The current state is:
Lobj has value: 0
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0


Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 20
Lyle has recently read: 0
Hal has recently read: 0

[Input of test 4]
write Ryan LObj 10
read Hal RObj 
write Lyle HObj ten
write 	Hal    LObj 200
read	  Hal HObj
read Lyle LObj

[Output of test 4]
Reading from file: instructionList.txt

Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


Bad Instruction
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


hal writes value 200 to lobj
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


hal reads hobj
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0


lyle reads lobj
The current state is:
Lobj has value: 0
Hobj has value: 0
Lyle has recently read: 0
Hal has recently read: 0