import java.util.*;
import java.io.*;

public class PasswordCrack{
    private static String[] mangle = {"lower", "reverse", "upper", "capital", "ncapital", "first", "last", "duplicate", "reflect", "toggle"};
    private static LinkedList<String> dictionary;

    /* Adds first and last names to the dictionary.
     */
    public static void addToFile(String name, String dictionary) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dictionary), true));
        String[] firstAndLast = name.split(" ");
        for(int i = 0; i < firstAndLast.length; i++){
            String item = firstAndLast[i];
            dictionary.push(item.toLowerCase());
            // bw.write(item.toLowerCase()+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void check(Hashtable<String, String> list, String dictionary) throws IOException{
        Scanner wordScan = new Scanner(new File(dictionary));
        Set<String> keys = list.keySet();

        while(wordScan.hasNext()){
            String word = wordScan.nextLine();
            // System.out.println("word: "+word);
            for(String method : mangle){
                // System.out.println("method: "+method);
                String m = mangles.determine(word, method);
                for(String key : keys){
                    String name = key;
                    String value = list.get(key);
                    String salt = value.substring(0, 2);
                    String password = value.substring(2);
                    String encrypt = jcrypt.crypt(salt, m);
                    // System.out.println("list: "+s+" Encrypt: "+encrypt);
                    if(encrypt.equals(value)){
                        System.out.println("The password for user "+ name + " is "+m);
                        list.remove(word);
                        break;
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

            Hashtable<String, String> passwords = new Hashtable();

            while(passScan.hasNext()){
                String[] line = passScan.nextLine().split(":");
                String encrypted = line[1];
                String name = line[4];

                passwords.put(name, encrypted);
                addToFile(name, wordsFile);
            }
            passScan.close();

            check(passwords, wordsFile);
        }
    }
}
