import java.util.*;

public class roundKey{
    /* XOR the state with the roundKey. */
    public static void add(byte[][] roundKey, byte[][] state){
        for(int c = 0; c < 4; c++){
            for(int r = 0; r < 4; r++){
                state[r][c] = (byte)(state[r][c] ^ roundKey[r][c]);
            }
        }
    }

    /* Uses the keySchedule and round number to determine which roundKey
     * is being used. Returns that roundKey.
     */
    public static byte[][] get(byte[][] keySchedule, int round){
        byte[][] roundKey = new byte[4][4];
        int colStart = round*4;
        for(int c = 0; c < 4; c++){
            for(int r = 0; r < 4; r++){
                roundKey[r][c] = keySchedule[r][colStart];
            }
            colStart++;
        }
        return roundKey;
    }
}
