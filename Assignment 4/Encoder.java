import java.util.*;
import java.io.*;

public class Encoder{

    /* Returns the entropy of the frequencies */
    public static double entropy(int[] f, int count){
        double entropy = 0; /* entropy is h = -(sum of all i pi log2pi) */
        for(int i : f){
            if(i == 0){
                break;
            }
            double p = (double)i/count; /* probability */
            double x = (p)*logBase2(p);
            entropy += x;
        }
        return -(entropy);
    }

    /* Returns the base 2 logarithm of a number */
    public static double logBase2(double num) {
        return Math.log(num) / Math.log(2);
    }

    /* Writes to "testText" a k-character text based on probabilities of the characters */
    public static void text(int[] frequencies, int k, int count) throws IOException{
        FileWriter fw = new FileWriter("testText");
        Random rand = new Random();

        for(int i = 0; i < k; i++){
            int value = rand.nextInt(count);
            int f = 0;

            for(int j = 0; j < frequencies.length-1; j++){
                if(value >= f && value < (f+frequencies[j])){
                    char ch = (char)('a'+j);
                    fw.write(ch);
                    break;
                }
                f += frequencies[j];
            }
        }
        fw.close();
    }

    /* Computes the probabilities and returns it in an array */
    public static double[] getProb(int[] frequencies, int count){
        double[] p = new double[frequencies.length];

        for(int i = 0; i < frequencies.length; i++){
            p[i] = ((double)frequencies[i]/count);
        }

        return p;
    }

    /* Encodes the "testText" using the codings provided by the Huffman code
     * and writes it to "testText.enc1". Returns the average bits per symbol.
     */
    public static double encode(String[] codes) throws IOException{
        Scanner in = new Scanner(new FileReader("testText"));
        String text = in.nextLine();

        FileWriter fw = new FileWriter("testText.enc1");
        double avg = 0.0;

        for(int i = 0; i < text.length(); i++){
            int ch = ((int)text.charAt(i)-97);
            String code = codes[ch];
            avg += code.length();
            fw.write(code);
        }
        avg /= text.length();
        fw.close();

        return avg;
    }

    /* Decodes the encoded "testText" and writes it to "testText.dec1" */
    public static void decode(String[] codes) throws IOException{
        Scanner in = new Scanner(new FileReader("testText.enc1"));
        String text = in.nextLine();

        FileWriter fw = new FileWriter("testText.dec1");
        int i = 0;

        while(text.length() > 0){
            String code = codes[i];
            String comp = text.substring(0, code.length());

            if(comp.compareTo(code) == 0){
                char ch = (char)('a'+i);
                fw.write(ch);
                text = text.substring(code.length());
                i = 0;
            }
            else{
                i++;
            }
        }
        fw.close();
    }

    /* Computes a two-symbol alphabet and returns a String[] */
    public static String[] twoSymbol(int k){
        String[] two = new String[(int)Math.pow(k,2)];
        int t = 0; // Keep track of where in array

        for(int i = 0; i < k; i++){
            StringBuilder str = new StringBuilder();
            char ch = (char)('a'+i);
            str.append(ch);

            for(int j = 0; j < k; j++){
                ch = (char)('a'+j);
                str.append(ch);

                two[t++] = str.toString();

                str.delete(1, str.length());
            }
        }
        return two;
    }

    /* Calculates the new frequencies for the two-symbol alphabet */
    public static int[] twoFrequencies(int[] frequencies, String[] two){
        int[] newf = new int[two.length];
        int i = 0;

        for(String s : two){
            int a = (int)s.charAt(0)-97;
            int b = (int)s.charAt(1)-97;

            int f1 = frequencies[a];
            int f2 = frequencies[b];

            int freq = f1*f2;

            newf[i++] = freq;
        }

        return newf;
    }

    public static void main(String[] args) throws IOException{
        Huffman h = new Huffman();
        Huffman.Tree tree;

        if(0 < args.length){
            String filename = args[0];
            int characters = Integer.parseInt(args[1]);

            Scanner in = new Scanner(new File(filename));
            int[] frequencies = new int[26];
            int i = 0; // keep track of where in the array
            int count = 0;

            while(in.hasNext()){ // reads the file and adds the frequencies to an array
                int line = Integer.parseInt(in.nextLine()); // frequency
                count += line;
                frequencies[i++] = line;
            }

            double[] probs = getProb(frequencies, count);
            tree = h.getHuffmanTree(frequencies); // create a Huffman tree
            String[] codes = h.getCode(tree.root); // get encodings; 0 is a, 1 is b, etc.

            h.printCode(codes, frequencies, probs);

            text(frequencies, characters, count); // creates the random text
            double avgbit = encode(codes); // average bits
            decode(codes);

            double e = entropy(frequencies, count); // entropy
            System.out.println("Average bits: "+avgbit);
            System.out.println("Entropy: "+e);
            double avg = (avgbit+e)/2;
            double pctdiff = Math.abs(avg-e)/avg*100;
            System.out.printf("Percent difference: %f%% \n", pctdiff);

            String[] two = twoSymbol(characters);
            for(String s : two){
                System.out.println(s);
            }
        }
    }
}
