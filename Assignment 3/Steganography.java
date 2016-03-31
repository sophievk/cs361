import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.awt.Color;
import javax.imageio.ImageIO;


public class Steganography{

  // Trys to read the image and returns it as a BufferedImage
  public static BufferedImage read(String filename){
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File(filename));
    } catch (IOException e) {
    }
    return img;
  }

  // Trys to save the new image
  public static void save(BufferedImage image, String filename, String ext){
    try{
      ImageIO.write(image, ext, new File(filename));
    } catch (IOException ex){
      ex.printStackTrace();
    }
  }

  // Reads in the message and returns the entire message as a String of bits
  public static String toBits(String message) throws FileNotFoundException{
    Scanner in = new Scanner(new File(message));
    StringBuilder bits = new StringBuilder();

    while(in.hasNext()){ // Read until EOF
      String line = in.nextLine();
      ByteArrayInputStream str = new ByteArrayInputStream(line.getBytes());
      int b;

      while((b = str.read()) != -1){ // Read until the end of stream
        StringBuilder bstr = new StringBuilder(Integer.toBinaryString(b));

        // If the string does not have 8 bits, add 0s to the beginning
        if(bstr.length()<8){
          while(bstr.length() != 8){
            bstr.insert(0, 0);
          }
        }
        bits.append(bstr);
      }
      bits.append("00001010"); // Adds the bits for carriage return
    }
    return bits.toString();
  }

  // Takes an array of pixels, sets new RGB values and returns the new image.
  public static BufferedImage getImage(int[] pixels, int width, int height){
    BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int[] rgbs = new int[pixels.length/3]; /* pixels has the values as individual
                                              red, green, and blue colors, so one
                                              RGB is 3 pixels */
    int j = 0; // Keeps track of position in rgbs[]

    // Increment every 3 pixels
    for(int i = 0; i < pixels.length; i+=3){
      int blue = ((int) pixels[i] & 0xff);
      int green = ((int) pixels[i + 1] & 0xff);
      int red = ((int) pixels[i + 2] & 0xff);

      // Combine each red, green, blue value to make a single RGB value
      int rgb = (red << 16) | (green << 8) | blue;

      rgbs[j] = rgb;
      j++;
    }

    j = 0; // Reset to 0 because finish reading in RGB values

    // Goes through the entire image and sets each RGB value, k keeps track of posi
    for(int r = 0; r < height; r++){
      for(int c = 0; c < width; c++){
        newImg.setRGB(c, r, rgbs[j]);
        j++;
      }
    }
    return newImg;
  }

  /* Checks if the bit is 0 or 1 and determines how to set the RGB value.
     It returns the RGB value. */
  public static int checkRGB(int x, char c){
    if(c == '0'){ // 0 bit
      if(x % 2 != 0){ // If RGB value is not even, decrement value so it is even
        x--;
      }
    }
    else{ // 1 bit
      if(x % 2 == 0){ // If RGB value is not odd, increment value so it is odd
        x++;
      }
    }
    return x;
  }

  /* Takes a message and determines if the pixel needs to be changed to encode
     the message. It returns the new image */
  public static BufferedImage encode(BufferedImage image, String message){
    byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    int[] encoded = new int[pixels.length];

    for(int pixel = 0, i = 0, j = 0; pixel < pixels.length; pixel++){
      int p = ((int)pixels[pixel] & 0xff);

      if(i < message.length()){ // If the entire message has not been encoded, encode the bit
        char bit = message.charAt(i);

        p = checkRGB(p, bit);
        encoded[pixel] = p;
        i++;
      }
      else if(j < 8){ // If the end of the message is reached, put in a 0 byte
        encoded[pixel] = 0;
        j++;
      }
      else{ // Puts the rest of the pixels into the int[]
        encoded[pixel] = p;
      }
    }
    // Deals with messages that will not fit in the picture
    if(message.length() > pixels.length){
      for(int i = pixels.length-8; i < pixels.length; i++){
        encoded[i] = 0;
      }
      System.err.println("Message truncated.");
    }

    return getImage(encoded, image.getWidth(), image.getHeight());
  }

  public static void decode(BufferedImage image, String message) throws IOException{
    FileWriter fw = new FileWriter(message);
    StringBuilder bits = new StringBuilder();

    byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

    for(int pixel = 0; pixel < pixels.length; pixel++){
      int p = ((int)pixels[pixel] & 0xff);

      if(p % 2 == 0){ // 0 bit
        bits.append(0);
      }
      else{ // 1 bit
        bits.append(1);
      }

      if(bits.length() == 8){ // If 1 byte is found
        int val = Byte.parseByte(bits.toString(), 2);
        bits.delete(0, bits.length());
        if(val == 0){ // Check if it is a 0 byte
          break;
        }
        fw.write((char)val); // Write the character to file
      }
    }
    fw.close();
  }

  public static void main(String[] args) throws IOException{
        // Checks for arguments passed in
        if(0 < args.length){
          String filename = args[1];
          String message = args[2];
          String fileExt = filename.substring(filename.length()-3);

          // Read the image
          BufferedImage img = read(filename);

          int width = img.getWidth();
          int height = img.getHeight();
          int amountPixel = width*height;

          // This prints the file name; total number of pixels; and image height and width.
          System.out.println(filename +  " " + amountPixel + " " + height  + "  " +  width);

          // Encoding flag
          if(args[0].equals("-E")){
            String newFile = filename.substring(0, filename.length()-4) + "-steg." + fileExt;
            String bits = toBits(message);

            BufferedImage newImg = encode(img, bits);

            save(newImg, newFile, fileExt);
          }

          // Decoding flag
          if(args[0].equals("-D")){
            decode(img, message);
          }
        }
    }
}
