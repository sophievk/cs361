import java.util.*;
import java.io.*;

public class AES{
    public static byte[][] state;

    public static void encrypt(byte[][] keySchedule){
        /* Initial round. */
        byte[][] rKey = roundKey.get(keySchedule, 0);
        roundKey.add(rKey, state);

        System.out.println("After addRoundKey(0):");
        System.out.println(byteToHex(to1D(state)));

        /* Main rounds. */
        for(int r = 1; r < 14; r++){
            rKey = roundKey.get(keySchedule, r);
            subBytes.sub(state);
            System.out.println("After subBytes:");
            System.out.println(byteToHex(to1D(state)));
            shiftRows.shift(state);
            System.out.println("After shiftRows:");
            System.out.println(byteToHex(to1D(state)));
            for(int c = 0; c < 4; c++){
                mixColumn.mixColumn2(c, state);
            }
            System.out.println("After mixColumns:");
            System.out.println(byteToHex(to1D(state)));
            roundKey.add(rKey, state);
            System.out.printf("After addRoundKey(%d):\n", r);
            System.out.println(byteToHex(to1D(state)));
        }

        /* Final round */
        rKey = roundKey.get(keySchedule, 14);
        subBytes.sub(state);
        shiftRows.shift(state);
        roundKey.add(rKey, state);
    }

    public static void decrypt(byte[][] keySchedule){
        /* Initial round. */
        byte[][] rKey = roundKey.get(keySchedule, 14);
        roundKey.add(rKey, state);
        shiftRows.invShift(state);
        subBytes.invSub(state);

        /* Main rounds. */
        for(int r = 13; r > 0; r--){
            rKey = roundKey.get(keySchedule, r);
            roundKey.add(rKey, state);
            for(int c = 0; c < 4; c++){
                mixColumn.invMixColumn2(c, state);
            }
            shiftRows.invShift(state);
            subBytes.invSub(state);


        }

        /* Final round */
        rKey = roundKey.get(keySchedule, 0);
        roundKey.add(rKey, state);
    }

    /* Takes a 1D array and returns a 4x4 2D array. (Column-Major order)
     */
    public static byte[][] to2D(byte[] array){
        byte[][] b = new byte[4][array.length/4];
        for(int i = 0; i < array.length; i++){
            b[i%4][i/4] = (byte)array[i];
        }
        return b;
    }

    /* Takes a 2D array and returns a 1D array. */
    public static byte[] to1D(byte[][] array){
        byte[] b = new byte[array.length*array[0].length];
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

    /* Takes a hex string and returns a byte array.
     */
    public static byte[] hexToByte2(String s){
        byte[] key = new byte[32];
        while(s.length() < 64){
            s += "0";
        }
        if(s.length() > 64){
            s = s.substring(0, 64);
        }
        for(int i = 0; i < 64; i+=2){
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
            byte[][] key = to2D(hexToByte2(k)); // cipherKey
            keyScan.close();

            Scanner fileScan = new Scanner(new File(file));

            byte[][] keySchedule = keyExpansion.expand(key); //schedule with roundKeys

            if(option.equals("e")){
                FileWriter fw = new FileWriter(file+".enc");
                while(fileScan.hasNext()){
                    String line = fileScan.nextLine();

                    System.out.println("State");
                    /* Read in initial state. */
                    state = to2D(hexToByte(line));

                    System.out.println("The Plaintext is:");
                    for(int r = 0; r < state.length; r++){
                        for(int c = 0; c < state[r].length; c++){
                            System.out.print(state[r][c]+" ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                    System.out.println("The cipherKey is:");
                    for(int r = 0; r < key.length; r++){
                        for(int c = 0; c < key[r].length; c++){
                            System.out.print(key[r][c]+" ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                    System.out.println("The expanded key is:");
                    for(int r = 0; r < keySchedule.length; r++){
                        for(int c = 0; c < keySchedule[r].length; c++){
                            System.out.print(keySchedule[r][c]+" ");
                        }
                        System.out.println();
                    }

                    encrypt(keySchedule);

                    /* Write state to file. */
                    String encrypted = byteToHex(to1D(state));
                    fw.write(encrypted+"\n");
                }
                fw.close();
            }
            else if(option.equals("d")){
                FileWriter fw = new FileWriter(file+".dec");
                while(fileScan.hasNext()){
                    String line = fileScan.nextLine();

                    /* Read in initial state. */
                    state = to2D(hexToByte(line));

                    decrypt(keySchedule);

                    /* Write state to file. */
                    String decrypted = byteToHex(to1D(state));
                    fw.write(decrypted+"\n");
                }
                fw.close();
            }
        }
    }
}
