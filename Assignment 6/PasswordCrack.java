import java.util.*;
import java.io.*;

public class PasswordCrack{
    private static String[] mangle1 = {
        "lower", "upper", "reverse", "capital", "ncapital", "first", "last"
    };
    private static String[] mangle2 = {
        "duplicate", "reflect", "toggle"
    };
    private static List<String[]> mangle3 = (
        Arrays.asList(new String[] {"prepend"}, new String[] {"append"},
        new String[] {"last", "append"}, new String[] {"ncapital", "append"},
        new String[] {"first", "append"}, new String[] {"capital", "append"})
    );
    private static List<String[]> mangle4 = new ArrayList(
        Arrays.asList(new String[] {"capital", "reverse"}, new String[] {"ncapital", "reverse"},
        new String[] {"last", "reverse"}, new String[] {"first", "reverse"},
        new String[] {"upper", "reverse"}, new String[] {"upper", "last"},
        new String[] {"upper", "first"}, new String[] {"ncapital", "last"},
        new String[] {"first", "ncapital"}, new String[] {"first", "capital"},
        new String[] {"capital", "last"}, new String[] {"reverse", "capital"},
        new String[] {"reverse", "ncapital"},
        new String[] {"capital", "last", "reverse"}, new String[] {"capital", "first", "reverse"},
        new String[] {"ncapital", "last", "reverse"}, new String[] {"ncapital", "first", "reverse"})

    );

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
        int count = 0;

        while(!list.isEmpty()){
            final long startTime = System.currentTimeMillis();
            for(String word : dictionary){
                for(String method : mangle1){
                    Iterator<String> iter = keys.iterator();
                    while(iter.hasNext()){
                        String name = iter.next();
                        String value = list.get(name);
                        String m = mangles.determine(word, value, method);
                        String same = mangles.same(value, m);
                        if(!same.equals(value)){
                            final long endTime = System.currentTimeMillis();
                            long time = (endTime-startTime)/1000;
                            count++;
                            System.out.printf("The password for user %s is %-11s\tTime: %d seconds \tCount: %d\n", name, m, time, count);
                            iter.remove();
                        }
                    }
                }
            }
            for(String word : dictionary){
                for(String method : mangle2){
                    Iterator<String> iter = keys.iterator();
                    while(iter.hasNext()){
                        String name = iter.next();
                        String value = list.get(name);
                        String m = mangles.determine(word, value, method);
                        String same = mangles.same(value, m);
                        if(!same.equals(value)){
                            final long endTime = System.currentTimeMillis();
                            long time = (endTime-startTime)/1000;
                            count++;
                            System.out.printf("The password for user %s is %-11s\tTime: %d seconds \tCount: %d\n", name, m, time, count);
                            iter.remove();
                        }
                    }
                }
            }
            for(String word : dictionary){
                for(String[] l : mangle4){
                    Iterator<String> iter = keys.iterator();
                    while(iter.hasNext()){
                        String name = iter.next();
                        String value = list.get(name);
                        String m = word;
                        for(String method : l){
                            m = mangles.determine(m, value, method);
                        }
                        String same = mangles.same(value, m);
                        if(!same.equals(value)){
                            final long endTime = System.currentTimeMillis();
                            long time = (endTime-startTime)/1000;
                            count++;
                            System.out.printf("The password for user %s is %-11s\tTime: %d seconds \tCount: %d\n", name, m, time, count);
                            iter.remove();
                        }
                    }
                }
            }
            for(String word : dictionary){
                for(String[] l : mangle3){
                    Iterator<String> iter = keys.iterator();
                    while(iter.hasNext()){
                        String name = iter.next();
                        String value = list.get(name);
                        String m = word;
                        for(String method : l){
                            m = mangles.determine(m, value, method);
                        }
                        String same = mangles.same(value, m);
                        if(!same.equals(value)){
                            final long endTime = System.currentTimeMillis();
                            long time = (endTime-startTime)/1000;
                            count++;
                            System.out.printf("The password for user %s is %-11s\tTime: %d seconds \tCount: %d\n", name, m, time, count);
                            iter.remove();
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
