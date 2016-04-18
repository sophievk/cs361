import java.util.*;

public class shiftRows{
    /* Shift row left by i bytes. i denotes the row in state.
     */
    public static void shift(byte[][] state){
        for(int r = 0; r < 4; r++){
            int rownum = r;
            while(rownum-- != 0){
                byte temp = state[r][0];
                for(int c = 0; c < 3; c++){
                    state[r][c] = state[r][c+1];
                }
                state[r][3] = temp;
            }
        }
    }

    /* Inverse of shiftRows(), returns the matrix to original matrix.
     * Shift row right by i bytes. i denotes the row in state.
     */
    public static void invShift(byte[][] state){
        for(int r = 0; r < 4; r++){
            int rownum = r;
            while(rownum-- != 0){
                byte temp = state[r][3];
                for(int c = 3; c > 0; c--){
                    state[r][c] = state[r][c-1];
                }
                state[r][0] = temp;
            }
        }
    }
}
