import java.util.*;
import java.io.*;

public class AES{

    public static void subBytes(){ //encryption

    }

    public static void invSubBytes(){ //decryption

    }

    /* Shift row left by i bytes. i denotes the row in state.
     */
    public static void shiftRows(byte[][] input){
        int rownum;

        for(int r = 0; r < 4; r++){
            rownum = r;
            while(rownum-- != 0){
                byte temp = input[r][0];
                for(int c = 0; c < 3; c++){
                    input[r][c] = input[r][c+1];
                }
                input[r][3] = temp;
            }
        }
    }

    /* Inverse of shiftRows(), returns the matrix to original matrix.
     * Shift row right by i bytes. i denotes the row in state.
     */
    public static void invShiftRows(byte[][] input){
        int rownum;

        for(int r = 0; r < 4; r++){
            rownum = r;
            while(rownum-- != 0){
                byte temp = input[r][3];
                for(int c = 3; c > 0; c--){
                    input[r][c] = input[r][c-1];
                }
                input[r][0] = temp;
            }
        }
    }

    /* Takes a hex string and returns a byte array.
     * Pads with zeroes if less than 32 Hex characters, truncates if more than.
     */
    public static byte[] hexToByte(String s){
        while(s.length() < 32){
            s += "0";
        }
        if(s.length() > 32){
            s = s.substring(0, 32);
        }
        byte[] key = new byte[16];
        for(int i = 0; i < 32; i+=2){
            String temp = s.substring(i, i+2);
            key[i/2] = (byte) Integer.parseInt(temp, 16);
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
            byte[] key = hexToByte(k);
            keyScan.close();

            // Scanner fileScan = new Scanner(new File(file));

            byte twoD[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 0 } };
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(twoD[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            shiftRows(twoD);
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(twoD[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            invShiftRows(twoD);
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(twoD[i][j]+" ");
                }
                System.out.println();
            }

            if(option.equals("e")){
                // while(fileScan.hasNext()){
                //     String line = fileScan.nextLine();
                //     byte[] input = hexToByte(line);
                //     // FileWriter fw = new FileWriter(file+".enc");
                //     // fw.close();
                // }
            }
            else if(option.equals("d")){
                // FileWriter fw = new FileWriter(file+".dec");
                // fw.close();
            }
        }
    }
}
