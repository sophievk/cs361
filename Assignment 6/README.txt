UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 6]

[Description]
There are three files: PasswordCrack.java, mangles.java, and jcrypt.java.
PasswordCrack.java has 3 methods:
addToFile() - adds first and last names to the dictionary.
check() - iterates thorough the Hashtable and tries to find a matching encrypted
mangle as the encrypted password.
main()
mangles.java has 12 methods:
determine() - Determines which method should be called and returns that method.
same() - takes the encrypted value and mangle and determines if they match.
crypt() is called to encrypt the mangle and salt. Returns mangle if the
same, the value otherwise.
capitalize() - Capitalizes the String input. Returns the capitalized String.
nCapitalize() - nCapitalize the String input. Returns the nCapitalized String.
reverse() - Reverses the String input. Returns the reversed String.
delFirst() - Deletes the first Char from the String input. Returns the new String.
delLast() - Deletes the last Char from the String input. Returns the new String.
duplicate() - Returns a String with the input duplicated.
reflect() - Creates the reflected String input. Determines if it is equal to the
value and returns the result from same().
toggleCase() - Creates the toggleCase String input. Determines if it is equal to the
value and returns the result from same().
prepend() - Prepends a character to the String input. Checks if it is equal
and returns the result from same().
append() - appends a character to the String input. Checks if it is equal
and returns the result from same().
jcrypt.java: I downloaded the class from the link provided in the assignment description.

[Finish]
We found XX/20 on passwd1 in time XX seconds. We found XX/20 on passwd2 in time XX seconds.

[Test Case 1]
There are two files: passwd1 and passwd2.
For each file, you need to write one sentence in README.txt to tell us "the number of cracked passwords",
"the list of uncracked ones", and "the list of cracked ones". If program works well, and you didn't write down
the number and list in README.txt, you will lose 3 points directly. Each files contain 20 cases. [1.5] If you
can crack >=15 cases; [1.0] If you can crack >=10 cases; [0.5] If you can crack>=5 cases.

[Input]

michael:atbWfKL4etk4U:500:500:Michael Ferris:/home/michael:/bin/bash
abigail:&i4KZ5wmac566:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bUx9LiAcW8As:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qt0.GlIrXuKs:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:feohQuHOnMKGE:504:504:Alexander Brown:/home/alexander:
james:{ztmy9azKzZgU:505:505:James Dover:/home/james:/bin/bash
benjamin:%xPBzF/TclHvg:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:khnVjlJN3Lyh2:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4DBHapAtnjGk:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7we09tBSVT76o:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3dIJJXzELzcRE:510:510:Evan Whitney:/home/evan:/bin/bash
jack:jsQGVbJ.IiEvE:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@EbBlXGLTue6:512:512:Victor Esperanza:/home/victor:
caleb:8joIBJaXJvTd2:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxsr/UAKmKnvo:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gwjT8yTnSCVQo:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KelgNcBOZdHmA:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5WW698tSZJE9I:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cI6tOT/9D2r6:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8jwuve9rQBo.:519:519:Paige Reiser:/home/paige:/bin/bash

[Output]

The password for user Maia Salizar is Salizar    	Time: 0 seconds 	Count: 1
The password for user Abigail Smith is liagiba    	Time: 0 seconds 	Count: 2
The password for user Michael Ferris is michael    	Time: 0 seconds 	Count: 3
The password for user Samantha Connelly is amazing  Time: 1 seconds 	Count: 4
The password for user Tyler Jones is eeffoc     	Time: 10 seconds 	Count: 5
The password for user Evan Whitney is Impact     	Time: 24 seconds 	Count: 6
The password for user Jack Gibson is sATCHEL    	Time: 41 seconds 	Count: 7
The password for user Alexander Brown is squadro    Time: 44 seconds 	Count: 8
The password for user Victor Esperanza is THIRTY    Time: 46 seconds 	Count: 9
The password for user James Dover is icious     	Time: 49 seconds 	Count: 10
The password for user Jennifer Elmer is doorrood   	Time: 58 seconds 	Count: 11
The password for user Connor Larson is enoggone   	Time: 61 seconds 	Count: 12
The password for user Nicole Rizzo is keyskeys   	Time: 64 seconds 	Count: 13
The password for user Caleb Patterson is teserP     Time: 112 seconds 	Count: 14
The password for user Dustin Hart is litpeR     	Time: 115 seconds 	Count: 15
The password for user Benjamin Ewing is abort6     	Time: 128 seconds 	Count: 16
The password for user Morgan Simmons is rdoctor    	Time: 283 seconds 	Count: 17
The password for user Rachel Saxon is obliqu3    	Time: 441 seconds 	Count: 18
The password for user Nathan Moore is sHREWDq    	Time: 508 seconds 	Count: 19


In total, I can crack 19/20 password in time 508 seconds.
I can not crack 1/20 password, the list is
paige


[Test Case 2]

[Input]

michael:atQhiiJLsT6cs:500:500:Michael Ferris:/home/michael:/bin/bash
abigail:&ileDTgJtzCRo:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bt0xiAwCf7nA:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qf.L9z1/tZkA:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:fe8PnYhq6WoOQ:504:504:Alexander Brown:/home/alexander:
james:{zQOjvJcHMs7w:505:505:James Dover:/home/james:/bin/bash
benjamin:%xqFrM5RVA6t6:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:kh/1uC5W6nDKc:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4EyEMhNzYLJU:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7wKTWsgNJj6ac:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3d1OgBYfvEtfg:510:510:Evan Whitney:/home/evan:/bin/bash
jack:js5ctN1leUABo:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@FxBZv.d0y/U:512:512:Victor Esperanza:/home/victor:
caleb:8jGWbU0xbKz06:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxr9OOqvZjbGs:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gw9oXmw1L08RM:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KenK1CTDGr/7k:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5Wb2Uqxhoyqfg:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cSaQELm/EBV.:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8U5jQaDVv/o2:519:519:Paige Reiser:/home/paige:/bin/bash

[Output]

The password for user Abigail Smith is Saxon      	Time: 0 seconds 	Count: 1
The password for user Connor Larson is nosral     	Time: 0 seconds 	Count: 2
The password for user Jack Gibson is ellows     	Time: 5 seconds 	Count: 3
The password for user Morgan Simmons is dIAMETER   	Time: 15 seconds 	Count: 4
The password for user Tyler Jones is eltneg     	Time: 22 seconds 	Count: 5
The password for user Nicole Rizzo is INDIGNITIES	Time: 26 seconds 	Count: 6
The password for user Michael Ferris is tremors    	Time: 50 seconds 	Count: 7
The password for user Benjamin Ewing is soozzoos   	Time: 93 seconds 	Count: 8
The password for user Caleb Patterson is zoossooz   Time: 93 seconds 	Count: 9
The password for user Alexander Brown is Lacque     Time: 135 seconds 	Count: 10
The password for user Evan Whitney is ^bribed    	Time: 341 seconds 	Count: 11
The password for user James Dover is enchant$   	Time: 585 seconds 	Count: 12
The password for user Dustin Hart is Swine3     	Time: 1330 seconds 	Count: 13
The password for user Nathan Moore is uPLIFTr    	Time: 1416 seconds 	Count: 14

In total, I can crack 14/20 password in time 1416 seconds.
I can not crack 6/20 password, the list is
samantha
jennifer
victor
rachel
maria
paige
