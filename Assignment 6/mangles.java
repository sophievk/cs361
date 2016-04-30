import java.util.*;
import java.io.*;

public class mangles{
    private static char[] chars = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '@', '#', '$',
        '%', '^', '&', '*', '(', ')', '?', '<', '>', ',', '.', '~', '`'
    };

    /* Determines which method should be called and returns that method.
     */
    public static String determine(String word, String value, String method){
        switch(method){
            case "lower":
                return word.toLowerCase();
            case "upper":
                return word.toUpperCase();
            case "capital":
                return capitalize(word);
            case "ncapital":
                return nCapitalize(word);
            case "reverse":
                return reverse(word);
            case "first":
                return delFirst(word);
            case "last":
                return delLast(word);
            case "duplicate":
                return duplicate(word);
            case "reflect":
                return reflect(word, value);
            case "toggle":
                return toggleCase(word, value);
            case "prepend":
                return prepend(word, value);
            case "append":
                return append(word, value);
            default:
                break;
        }
        return value;
    }

    /* Takes the encrypted value and mangle and determines if they match.
     * crypt() is called to encrypt the mangle and salt. Returns mangle if the
     * same, the value otherwise.
     */
    public static String same(String value, String mangle){
        String salt = value.substring(0, 2);
        String encrypt = jcrypt.crypt(salt, mangle);
        if(encrypt.equals(value)){
            return mangle;
        }
        return value;
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
        return output;
    }

    /* Deletes the last Char from the String input. Returns the new String.
     */
    public static String delLast(String input){
        String output = input.substring(0, input.length()-1);
        return output;
    }

    /* Returns a String with the input duplicated.
     */
    public static String duplicate(String input){
        return input+input;
    }

    /* Creates the reflected String input. Determines if it is equal to the
     * value and returns the result from same().
     */
    public static String reflect(String input, String value){
        String output1 = input + reverse(input);

        if(!same(value, output1).equals(value)){
            return same(value, output1);
        }

        String output2 = reverse(input) + input;

        return same(value, output2);
    }

    /* Creates the toggleCase String input. Determines if it is equal to the
     * value and returns the result from same().
     */
    public static String toggleCase(String input, String value){
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

        if(!same(value, output).equals(value)){
            return same(value, output);
        }

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

        return value;
    }

    /* Prepends a character to the String input. Checks if it is equal
     * and returns the result from same().
     */
    public static String prepend(String input, String value){
        for(char c : chars){
            String output = Character.toString(c)+input;
            if(!same(value, output).equals(value)){
                return same(value, output);
            }
        }
        return value;
    }

    /* Appends a character to the String input. Checks if it is equal
     * and returns the result from same().
     */
    public static String append(String input, String value){
        for(char c : chars){
            String output = input+Character.toString(c);
            if(!same(value, output).equals(value)){
                return same(value, output);
            }
        }
        return value;
    }
}

// **prepend a character to the string, e.g., @string;
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
