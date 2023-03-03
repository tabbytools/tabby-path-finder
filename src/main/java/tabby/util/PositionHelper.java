package tabby.util;

import java.util.Set;


public class PositionHelper {

    public static int THIS = -1;
    public static int SOURCE = -2;
    public static int NOT_POLLUTED_POSITION = -3;
    public static int ANY = -4;

    public static boolean isNotPollutedPosition(Object pos){
        if(pos instanceof int[]){
            int[] val = (int[]) pos;
            return val.length == 1 && NOT_POLLUTED_POSITION == val[0];
        }

        return NOT_POLLUTED_POSITION == (int) pos;
    }

    public static boolean isThisPolluted(int[][] polluted){
        for(int[] pos:polluted){
            Set<Integer> set = Transformer.intArrayToSet(pos);
            if(set.contains(PositionHelper.THIS)) return true;
        }
        return false;
    }
}
