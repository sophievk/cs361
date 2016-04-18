import java.util.*;
import java.io.*;

public class AES{
    public static byte[][] state;

    /* Takes a 1D array and returns a 4x4 2D array. (Column-Major order)
     */
    public static byte[][] to2D(byte[] array){
        byte[][] b = new byte[4][4];
        for(int i = 0; i < array.length; i++){
            b[i%4][i/4] = (byte)array[i];
        }
        return b;
    }

    /* Takes a 2D array and returns a 1D array. */
    public static byte[] to1D(byte[][] array){
        byte[] b = new byte[16];
        for(int i = 0; i < 16; i++){
            b[i] = array[i%4][i/4];
        }
        return b;
    }

    /* Takes a hex string and returns a byte array.
     * Pads with zeroes if less than 32 Hex characters, truncates if more than.
     */
    public static byte[] hexToByte(String s){
        byte[] key = new byte[16];
        while(s.length() < 32){
            s += "0";
        }
        if(s.length() > 32){
            s = s.substring(0, 32);
        }
        for(int i = 0; i < 32; i+=2){
            key[i/2] = (byte)((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i+1), 16));
        }
        return key;
    }

    /* Takes a byte array and returns a hex string.
     */
    public static String byteToHex(byte[] bytes){
        StringBuilder str = new StringBuilder();
        for(byte b : bytes){
            int i = b & 0xFF;
            String hex = Integer.toHexString(i);
            if(hex.length() == 1){
                str.append("0");
            }
            str.append(hex);

        }
        return str.toString().toUpperCase();
    }

    public static void main(String[] args) throws IOException{
        if(0 < args.length){
            String option = args[0];
            String keyFile = args[1]; // key file (1 line of 64 hex chars)
            String file = args[2]; // file with lines of plaintext (32 hex chars/line)

            Scanner keyScan = new Scanner(new File(keyFile));
            String k = keyScan.nextLine();
            byte[][] key = to2D(hexToByte(k)); // cipherKey
            keyScan.close();

            Scanner fileScan = new Scanner(new File(file));

            byte[][] keySchedule = keyExpansion.expand(key); //schedule with roundKeys

            if(option.equals("e")){
                FileWriter fw = new FileWriter(file+".enc");
                while(fileScan.hasNext()){
                    String line = fileScan.nextLine();
                    /* Read in initial state. */
                    state = to2D(hexToByte(line));
                    // for(int i = 0; i < 4; i++){
                    //     for(int j = 0; j < 4; j++){
                    //         System.out.print(state[i][j]+" ");
                    //     }
                    //     System.out.println();
                    // }
                    // System.out.println();
                    // byte[] s = to1D(state);
                    // for(byte b : s){
                    //     System.out.print(b+" ");
                    // }
                    // System.out.println();

                    /* Initial round. */
                    byte[][] rKey = roundKey.get(keySchedule, 0);
                    roundKey.add(rKey, state);

                    /* Main rounds. */
                    for(int r = 1; r < 14; r++){
                        rKey = roundKey.get(keySchedule, r);
                        subBytes.sub(state);
                        shiftRows.shift(state);
                        for(int c = 0; c < 4; c++){
                            mixColumn.mixColumn2(c, state);
                        }
                        roundKey.add(rKey, state);
                    }

                    /* Final round */
                    rKey = roundKey.get(keySchedule, 14);
                    subBytes.sub(state);
                    shiftRows.shift(state);
                    roundKey.add(rKey, state);

                    /* Write state to file. */
                    String encrypted = byteToHex(to1D(state));

                }
                fw.close();
            }
            else if(option.equals("d")){
                // FileWriter fw = new FileWriter(file+".dec");
                // fw.close();
            }
        }
    }
}
