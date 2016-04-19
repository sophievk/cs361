import java.util.*;

public class keyExpansion{
    /* Rcon table for keyExpansion. */
    private final static int[][] Rcon = {
        {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36, 0x6C, 0xD8,
            0xAB, 0x4D, 0x9A, 0x2F, 0x5E, 0xBC, 0x63, 0xC6, 0x97, 0x35, 0x6A,
            0xD4, 0xB3, 0x7D, 0xFA, 0xED, 0xC5, 0x91},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}
    };

    /* Takes a byte[] and rotates it up one spot.
     */
    public static void rotate(byte[] array){
        System.out.println();
        byte b = array[0];
        for(int i = 0; i < 3; i++){
            array[i] = array[i+1];
        }
        array[3] = b;
    }

    /* Returns the word in the column specified as a byte[].
    */
    public static byte[] getWord(byte[][] keySchedule, int col){
        byte[] word = new byte[4];
        for(int r = 0; r < 4; r++){
            word[r] = keySchedule[r][col];
        }
        return word;
    }

    /* Creates the roundKeys needed. */
    public static byte[][] expand(byte[][] key){
        byte[][] keySchedule = new byte[4][60];
        byte[] w1 = new byte[4];
        byte[] w4 = new byte[4];
        int rCol = 0;

        /* Initial roundKey added. */
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 8; j++){
                keySchedule[i][j] = key[i][j];
            }
        }

        /* Remaining roundKeys created. */
        for(int col = 8; col < 60; col ++){
            if(col%4 == 0){
                w1 = getWord(keySchedule, col-1);
                w4 = getWord(keySchedule, col-4);
                rotate(w1);
                subBytes.subWord(w1);

                for(int i = 0; i < 4; i++){
                    keySchedule[i][col] = (byte)(w4[i] ^ w1[i] ^ Rcon[i][rCol]);
                }
                rCol++;
            }
            else{
                w1 = getWord(keySchedule, col-1);
                w4 = getWord(keySchedule, col-4);
                for(int i = 0; i < 4; i++){
                    keySchedule[i][col] = (byte)(w1[i] ^ w4[i]);
                }
            }
        }
        return keySchedule;
    }
}
