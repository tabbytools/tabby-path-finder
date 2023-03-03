package tabby.calculator;

import tabby.util.PositionHelper;
import tabby.util.Transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class BackwardCalculator implements Calculator {

    /**
     * @param callSite
     * @param polluted
     * @return
     */
    @Override
    public int[] v2(int[][] callSite, Set<Integer> polluted) {
        Set<Integer> newPolluted = new HashSet<>();

        for(int p : polluted){
            int pos = p + 1;
            if(pos < callSite.length && pos >= 0){
                int[] call = callSite[pos];
                if(PositionHelper.isNotPollutedPosition(call)) return null;
                newPolluted.addAll(Arrays.stream(call).boxed().collect(Collectors.toSet()));
            }else if(p == PositionHelper.SOURCE){
                newPolluted.add(PositionHelper.SOURCE);
            } else{
                return null;
            }
        }

        newPolluted.remove(PositionHelper.NOT_POLLUTED_POSITION);
        return newPolluted.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * polluted
     * @param callSite
     * @param polluted
     * @return
     */
    @Override
    public int[][] v3(int[][] callSite, int[][] polluted) {
        Set<int[]> ret = new HashSet<>();
        int len = polluted.length;
        for(int i=0;i<len;i++){
            Set<Integer> newPolluted = new HashSet<>();
            int[] pos = polluted[i];
            for(int p:pos){
                int index = p + 1;
                if(index < callSite.length && index >= 0){
                    int[] call = callSite[index];
                    if(PositionHelper.isNotPollutedPosition(call)) continue;
                    newPolluted.addAll(Transformer.intArrayToSet(call));
                }else if(p == PositionHelper.SOURCE){
                    newPolluted.add(PositionHelper.SOURCE);
                }
            }
            if(newPolluted.isEmpty()) return null;
            newPolluted.remove(PositionHelper.NOT_POLLUTED_POSITION);
            ret.add(Transformer.setToIntArray(newPolluted));
        }

        return ret.toArray(new int[0][]);
    }
}
