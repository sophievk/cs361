import java.util.*;
import java.io.*;

public class mangles{
    public static String determine(String input, String method){
        switch(method){
            case "lower":
                return input.toLowerCase();
            case "upper":
                return input.toUpperCase();
            case "capital":
                return capitalize(input);
            case "ncapital":
                return nCapitalize(input);
            case "reverse":
                return reverse(input);
            case "first":
                return delFirst(input);
            case "last":
                return delLast(input);
            case "duplicate":
                return duplicate(input);
            case "reflect":
                break;
            case "toggle":
                break;
            default:
                break;
        }
        return "";
    }

    /* Capitalizes the String input. Returns the capitalized String.
     */
    public static String capitalize(String input){
        String output = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        return output;
    }

    /* nCapitalize the String input. Returns the nCapitalized String.
     */
    public static String nCapitalize(String input){
        String output = input.substring(0, 1).toLowerCase() + input.substring(1).toUpperCase();
        return output;
    }

    /* Reverses the String input. Returns the reversed String.
     */
    public static String reverse(String input){
        String output = new StringBuilder(input).reverse().toString();
        return output;
    }

    /* Deletes the first Char from the String input. Returns the new String.
     */
    public static String delFirst(String input){
        String output = input.substring(1);
        // System.out.println(output);
        return output;
    }

    /* Deletes the last Char from the String input. Returns the new String.
     */
    public static String delLast(String input){
        String output = input.substring(0, input.length()-1);
        // System.out.println(output);
        return output;
    }

    /* Returns a String with the input duplicated.
     */
    public static String duplicate(String input){
        return input+input;
    }

    /* Writes to file a reflected input before or after.
     */
    public static void reflect(String input, BufferedWriter bw) throws IOException{
        String output1 = input + reverse(input);
        String output2 = reverse(input) + input;
        bw.write(output1+"\n");
        bw.write(output2+"\n");
    }

    /* Writes to file the toggleCase String input.
     */
    public static void toggleCase(String input, BufferedWriter bw) throws IOException{
        String output = "";
        char[] chars =  input.toCharArray();
        for(int i = 0; i < chars.length; i++){
            char c = chars[i];
            if(i%2 == 0){
                chars[i] = Character.toUpperCase(c);
            }
            else{
                chars[i] = Character.toLowerCase(c);
            }
        }
        output = new String(chars);
        bw.write(output+"\n");
        for(int i = 0; i < chars.length; i++){
            char c = chars[i];
            if(i%2 == 0){
                chars[i] = Character.toLowerCase(c);
            }
            else{
                chars[i] = Character.toUpperCase(c);
            }
        }
        output = new String(chars);
        bw.write(output+"\n");
    }
}

// prepend a character to the string, e.g., @string;
// append a character to the string, e.g., string9;
// **delete the first character from the string, e.g., tring;
// **delete the last character from the string, e.g., strin;
// **reverse the string, e.g., gnirts;
// **duplicate the string, e.g., stringstring;
// **reflect the string, e.g., stringgnirts or gnirtsstring;
// **uppercase the string, e.g., STRING;
// **lowercase the string, e.g., string;
// **capitalize the string, e.g., String;
// **ncapitalize the string, e.g., sTRING;
// **toggle case of the string, e.g., StRiNg or sTrInG;
