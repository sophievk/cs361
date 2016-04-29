import java.util.*;
import java.io.*;

public class PasswordCrack{
    private static String[] mangle = {
        "lower", "reverse", "upper", "capital", "ncapital", "first", "last",
        "duplicate", "reflect", "toggle", "prepend", "append"
    };
    private static LinkedList<String> dictionary = new LinkedList<String>();

    /* Adds first and last names to the dictionary.
     */
    public static void addToFile(String name){
        String[] firstAndLast = name.split(" ");
        for(int i = 0; i < firstAndLast.length; i++){
            String item = firstAndLast[i];
            dictionary.push(item.toLowerCase());
        }
    }

    /* Iterates thorough the Hashtable and tries to find a matching encrypted
     * mangle as the encrypted password.
     */
    public static void crack(Hashtable<String, String> list){
        // Scanner wordScan = new Scanner(new File(dictionary));
        Set<String> keys = list.keySet();

        while(!list.isEmpty()){
            for(String word : dictionary){
                // String word = wordScan.nextLine();
                // System.out.println("word: "+word);
                for(String method : mangle){
                    // System.out.println("method: "+method);
                    // String m = mangles.determine(word, method);
                    for(String key : keys){
                        String name = key;
                        String value = list.get(key);
                        // String salt = value.substring(0, 2);
                        // String encrypt = jcrypt.crypt(salt, m);
                        String m = mangles.determine(word, value, method);
                        if(!m.equals(value)){
                            System.out.println("The password for user "+ name + " is "+m);
                            list.remove(name);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        String wordsFile = args[0];
        String passFile = args[1];

        if(0 < args.length){
            Scanner passScan = new Scanner(new File(passFile));
            Scanner wordScan = new Scanner(new File(wordsFile));

            while(wordScan.hasNext()){
                String line = wordScan.nextLine();
                dictionary.add(line);
            }
            wordScan.close();

            Hashtable<String, String> passwords = new Hashtable();

            while(passScan.hasNext()){
                String[] line = passScan.nextLine().split(":");
                String encrypted = line[1];
                String name = line[4];

                passwords.put(name, encrypted);
                addToFile(name);
            }
            passScan.close();

            crack(passwords);
        }
    }
}
