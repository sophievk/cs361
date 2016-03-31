UTEID: svk297;
FIRSTNAME: Sophie;
LASTNAME: Khounlo;
CSACCOUNT: sophiek;
EMAIL: s.khounlo@utexas.edu;

[Program 3]
[Description]
There is only 1 java file.
Steganography.java: There are 8 methods: read, save, toBits, getImage, checkRGB, encode, decode, and main.
read() reads the image and returns it as a BufferedImage.
save() writes the new image as a new image file.
toBits() reads in the message file and returns the entire message as a String of bits.
getImage() takes in the array of pixels, set the rgb values in a new BufferedImage, and returns the new image.
checkRGB() takes in the bit to be stored and the rgb value. It checks the bit and determines if the rgb value needs to be changed. It returns the rgb value.
encode() takes in the message string of bits and the original image. It gets the array of pixels from the image and feeds it into checkRGB(). It checks if the message is larger than the picture. If it is an error message is printed. It then returns getImage().
decode() takes in the modified image and the filename the message should be written to. It gets the array of pixels from the modified image. Then goes through the array and determines if it is an odd or even number. If it is odd, a 1 is added to the StringBuilder; otherwise a 0 is added. Once the StringBuilder has 8 bits, it writes the character to the file and clears the StringBuilder.
main() determines which flag is inputted and calls the correct methods for each.
The most important methods are getImage and encode. Encode makes sure the message is encoded properly and the 0 byte is stored correctly. getImage makes sure that the pixels are read correctly into an rgb value. Then it sets each pixel is set to that rgb value.
My program can encode and decode png and bmp images.
To compile my program, you need to use "javac *.java". To run my program, you need to use "java Steganography -flag image_filename message_filename".

[Finish]
I finished all of the assignment.

[Questions & Answers]

[Question 1]
Comparing your original and modified images carefully, can you detect *any* difference visually (that is, in the appearance of the image)?

[Answer 1]
The only difference that I can tell is that the modified image is slightly brighter in color.

[Question 2]
Can you think of other ways you might hide the message in image files (or in other types of files)?

[Answer 2]
You can shift the bits of the RGB values to store all eight bits of a character in one pixel.

[Question 3]
Can you invent ways to increase the bandwidth of the channel?

[Answer 3]
You can store all eight bits of a single character in one RGB value. Therefore, this increases the bandwidth from 3 bits per pixel to 8 bits per pixel.

[Question 4]
Suppose you were tasked to build an "image firewall" that would block images containing hidden messages. Could you do it? How might you approach this problem?

[Answer 4]
Yes, but it may be difficult because there are many different ways that you could hide messages.

[Question 5]
Does this fit our definition of a covert channel? Explain your answer.

[Answer 5]
Yes. An image is not is an official channel to send messages through. The image must be manipulated to send the message.
